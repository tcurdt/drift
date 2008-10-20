/*
 * Copyright 2008 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vafer.drift.protocol.impl;

import java.io.UnsupportedEncodingException;
import java.util.Stack;

import org.vafer.drift.DriftException;
import org.vafer.drift.protocol.Protocol;
import org.vafer.drift.protocol.ProtocolException;
import org.vafer.drift.protocol.fields.TAttribute;
import org.vafer.drift.protocol.fields.TList;
import org.vafer.drift.protocol.fields.TMap;
import org.vafer.drift.protocol.fields.TSet;
import org.vafer.drift.protocol.fields.TObject;
import org.vafer.drift.protocol.fields.TType;
import org.vafer.drift.transport.Transport;
import org.vafer.drift.utils.Base64Utils;
import org.vafer.drift.utils.TByteArrayOutputStream;

public class JSONProtocol implements Protocol {

	private static final byte[] MAGIC = { 0x7d, 0x7a };
	private static final byte[] VERSION = { 0x00, 0x01 };
	
	private final Transport trans;

	public JSONProtocol(Transport pTrans) {
		trans = pTrans;
	}

	private static final byte[] COMMA = new byte[] {','};
	private static final byte[] COLON = new byte[] {':'};
	private static final byte[] LBRACE = new byte[] {'{'};
	private static final byte[] RBRACE = new byte[] {'}'};
	private static final byte[] LBRACKET = new byte[] {'['};
	private static final byte[] RBRACKET = new byte[] {']'};
	private static final byte[] QUOTE = new byte[] {'"'};
	private static final byte[] BACKSLASH = new byte[] {'\\'};
	private static final byte[] ZERO = new byte[] {'0'};

	private static final byte[] ESCSEQ = new byte[] {'\\','u','0','0'};

	//private static final long  VERSION = 1;

	private static final byte[] JSON_CHAR_TABLE = {
		/*  0   1   2   3   4   5   6   7   8   9   A   B   C   D   E   F */
		0,  0,  0,  0,  0,  0,  0,  0,'b','t','n',  0,'f','r',  0,  0, // 0
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, // 1
		1,  1,'"',  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, // 2
	};

	private static final String ESCAPE_CHARS = "\"\\bfnrt";

	private static final byte[] ESCAPE_CHAR_VALS = {
		'"', '\\', '\b', '\f', '\n', '\r', '\t',
	};

	private static final int  DEF_STRING_SIZE = 16;

	private static final byte[] NAME_BOOL = new byte[] {'t', 'f'};
	private static final byte[] NAME_BYTE = new byte[] {'i','8'};
	private static final byte[] NAME_I16 = new byte[] {'i','1','6'};
	private static final byte[] NAME_I32 = new byte[] {'i','3','2'};
	private static final byte[] NAME_I64 = new byte[] {'i','6','4'};
	private static final byte[] NAME_DOUBLE = new byte[] {'d','b','l'};
	private static final byte[] NAME_STRUCT = new byte[] {'r','e','c'};
	private static final byte[] NAME_STRING = new byte[] {'s','t','r'};
	private static final byte[] NAME_MAP = new byte[] {'m','a','p'};
	private static final byte[] NAME_LIST = new byte[] {'l','s','t'};
	private static final byte[] NAME_SET = new byte[] {'s','e','t'};

	private static final byte[] getTypeNameForTypeID(byte typeID)
	throws DriftException {
		switch (typeID) {
		case TType.BOOL:
			return NAME_BOOL;
		case TType.BYTE:
			return NAME_BYTE;
		case TType.I16:
			return NAME_I16;
		case TType.I32:
			return NAME_I32;
		case TType.I64:
			return NAME_I64;
		case TType.DOUBLE:
			return NAME_DOUBLE;
		case TType.STRING:
			return NAME_STRING;
		case TType.OBJECT:
			return NAME_STRUCT;
		case TType.MAP:
			return NAME_MAP;
		case TType.SET:
			return NAME_SET;
		case TType.LIST:
			return NAME_LIST;
		default:
			throw new ProtocolException(ProtocolException.NOT_IMPLEMENTED,
			"Unrecognized type");
		}
	}

	private static final byte getTypeIDForTypeName(byte[] name)
	throws DriftException {
		byte result = TType.STOP;
		if (name.length > 1) {
			switch (name[0]) {
			case 'd':
				result = TType.DOUBLE;
				break;
			case 'i':
				switch (name[1]) {
				case '8':
					result = TType.BYTE;
					break;
				case '1':
					result = TType.I16;
					break;
				case '3':
					result = TType.I32;
					break;
				case '6':
					result = TType.I64;
					break;
				}
				break;
			case 'l':
				result = TType.LIST;
				break;
			case 'm':
				result = TType.MAP;
				break;
			case 'r':
				result = TType.OBJECT;
				break;
			case 's':
				if (name[1] == 't') {
					result = TType.STRING;
				}
				else if (name[1] == 'e') {
					result = TType.SET;
				}
				break;
			case 't':
				result = TType.BOOL;
				break;
			}
		}
		if (result == TType.STOP) {
			throw new ProtocolException(ProtocolException.NOT_IMPLEMENTED,
			"Unrecognized type");
		}
		return result;
	}

	// Base class for tracking JSON contexts that may require inserting/reading
	// additional JSON syntax characters
	// This base context does nothing.
	protected class JSONBaseContext {
		protected void write() throws DriftException {}

		protected void read() throws DriftException {}

		protected boolean escapeNum() { return false; }
	}

	// Context for JSON lists. Will insert/read commas before each item except
	// for the first one
	protected class JSONListContext extends JSONBaseContext {
		private boolean first_ = true;

		@Override
		protected void write() throws DriftException {
			if (first_) {
				first_ = false;
			} else {
				trans.write(COMMA);
			}
		}

		@Override
		protected void read() throws DriftException {
			if (first_) {
				first_ = false;
			} else {
				readJSONSyntaxChar(COMMA);
			}
		}
	}

	// Context for JSON records. Will insert/read colons before the value portion
	// of each record pair, and commas before each key except the first. In
	// addition, will indicate that numbers in the key position need to be
	// escaped in quotes (since JSON keys must be strings).
	protected class JSONPairContext extends JSONBaseContext {
		private boolean first_ = true;
		private boolean colon_ = true;

		@Override
		protected void write() throws DriftException {
			if (first_) {
				first_ = false;
				colon_ = true;
			} else {
				trans.write(colon_ ? COLON : COMMA);
				colon_ = !colon_;
			}
		}

		@Override
		protected void read() throws DriftException {
			if (first_) {
				first_ = false;
				colon_ = true;
			} else {
				readJSONSyntaxChar(colon_ ? COLON : COMMA);
				colon_ = !colon_;
			}
		}

		@Override
		protected boolean escapeNum() {
			return colon_;
		}
	}

	// Holds up to one byte from the transport
	protected class LookaheadReader {

		private boolean hasData_;
		private byte[] data_ = new byte[1];

		// Return and consume the next byte to be read, either taking it from the
		// data buffer if present or getting it from the transport otherwise.
		protected byte read() throws DriftException {
			if (hasData_) {
				hasData_ = false;
			}
			else {
				trans.readAll(data_, 0, 1);
			}
			return data_[0];
		}

		// Return the next byte to be read without consuming, filling the data
		// buffer if it has not been filled already.
		protected byte peek() throws DriftException {
			if (!hasData_) {
				trans.readAll(data_, 0, 1);
			}
			hasData_ = true;
			return data_[0];
		}
	}

	// Stack of nested contexts that we may be in
	private Stack<JSONBaseContext> contextStack_ = new Stack<JSONBaseContext>();

	// Current context that we are in
	private JSONBaseContext context_ = new JSONBaseContext();

	// Reader that manages a 1-byte buffer
	private LookaheadReader reader_ = new LookaheadReader();

	// Push a new JSON context onto the stack.
	private void pushContext(JSONBaseContext c) {
		contextStack_.push(context_);
		context_ = c;
	}

	// Pop the last JSON context off the stack
	private void popContext() {
		context_ = contextStack_.pop();
	}


	// Temporary buffer used by several methods
	private byte[] tmpbuf_ = new byte[4];

	// Read a byte that must match b[0]; otherwise an excpetion is thrown.
	// Marked protected to avoid synthetic accessor in JSONListContext.read
	// and JSONPairContext.read
	protected void readJSONSyntaxChar(byte[] b) throws DriftException {
		byte ch = reader_.read();
		if (ch != b[0]) {
			throw new ProtocolException(ProtocolException.INVALID_DATA,
					"Unexpected character:" + (char)ch);
		}
	}

	// Convert a byte containing a hex char ('0'-'9' or 'a'-'f') into its
	// corresponding hex value
	private static final byte hexVal(byte ch) throws DriftException {
		if ((ch >= '0') && (ch <= '9')) {
			return (byte)((char)ch - '0');
		}
		else if ((ch >= 'a') && (ch <= 'f')) {
			return (byte)((char)ch - 'a');
		}
		else {
			throw new ProtocolException(ProtocolException.INVALID_DATA,
			"Expected hex character");
		}
	}

	// Convert a byte containing a hex value to its corresponding hex character
	private static final byte hexChar(byte val) {
		val &= 0x0F;
		if (val < 10) {
			return (byte)((char)val + '0');
		}
		else {
			return (byte)((char)val + 'a');
		}
	}

	// Write the bytes in array buf as a JSON characters, escaping as needed
	private void writeJSONString(byte[] b) throws DriftException {
		context_.write();
		trans.write(QUOTE);
		int len = b.length;
		for (int i = 0; i < len; i++) {
			if ((b[i] & 0x00FF) >= 0x30) {
				if (b[i] == BACKSLASH[0]) {
					trans.write(BACKSLASH);
					trans.write(BACKSLASH);
				}
				else {
					trans.write(b, i, 1);
				}
			}
			else {
				tmpbuf_[0] = JSON_CHAR_TABLE[b[i]];
				if (tmpbuf_[0] == 1) {
					trans.write(b, i, 1);
				}
				else if (tmpbuf_[0] > 1) {
					trans.write(BACKSLASH);
					trans.write(tmpbuf_, 0, 1);
				}
				else {
					trans.write(ESCSEQ);
					tmpbuf_[0] = hexChar((byte)(b[i] >> 4));
					tmpbuf_[1] = hexChar(b[i]);
					trans.write(tmpbuf_, 0, 2);
				}
			}
		}
		trans.write(QUOTE);
	}

	// Write out number as a JSON value. If the context dictates so, it will be
	// wrapped in quotes to output as a JSON string.
	private void writeJSONInteger(long num) throws DriftException {
		context_.write();
		String str = Long.toString(num);
		boolean escapeNum = context_.escapeNum();
		if (escapeNum) {
			trans.write(QUOTE);
		}
		try {
			byte[] buf = str.getBytes("UTF-8");
			trans.write(buf);
		} catch (UnsupportedEncodingException uex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
		if (escapeNum) {
			trans.write(QUOTE);
		}
	}

	// Write out a double as a JSON value. If it is NaN or infinity or if the
	// context dictates escaping, write out as JSON string.
	private void writeJSONDouble(double num) throws DriftException {
		context_.write();
		String str = Double.toString(num);
		boolean special = false;
		switch (str.charAt(0)) {
		case 'N': // NaN
		case 'I': // Infinity
			special = true;
			break;
		case '-':
			if (str.charAt(1) == 'I') { // -Infinity
				special = true;
			}
			break;
		}

		boolean escapeNum = special || context_.escapeNum();
		if (escapeNum) {
			trans.write(QUOTE);
		}
		try {
			byte[] b = str.getBytes("UTF-8");
			trans.write(b, 0, b.length);
		} catch (UnsupportedEncodingException uex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
		if (escapeNum) {
			trans.write(QUOTE);
		}
	}

	// Write out contents of byte array b as a JSON string with base-64 encoded
	// data
	private void writeJSONBase64(byte[] b) throws DriftException {
		context_.write();
		trans.write(QUOTE);
		int len = b.length;
		int off = 0;
		while (len >= 3) {
			// Encode 3 bytes at a time
			Base64Utils.encode(b, off, 3, tmpbuf_, 0);
			trans.write(tmpbuf_, 0, 4);
			off += 3;
			len -= 3;
		}
		if (len > 0) {
			// Encode remainder
			Base64Utils.encode(b, off, len, tmpbuf_, 0);
			trans.write(tmpbuf_, 0, len + 1);
		}
		trans.write(QUOTE);
	}

	private void writeJSONObjectStart() throws DriftException {
		context_.write();
		trans.write(LBRACE);
		pushContext(new JSONPairContext());
	}

	private void writeJSONObjectEnd() throws DriftException {
		popContext();
		trans.write(RBRACE);
	}

	private void writeJSONArrayStart() throws DriftException {
		context_.write();
		trans.write(LBRACKET);
		pushContext(new JSONListContext());
	}

	private void writeJSONArrayEnd() throws DriftException {
		popContext();
		trans.write(RBRACKET);
	}
	/*
  @Override
  public void writeMessageBegin(TMessage message) throws TException {
    writeJSONArrayStart();
    writeJSONInteger(VERSION);
    try {
      byte[] b = message.name.getBytes("UTF-8");
      writeJSONString(b);
    } catch (UnsupportedEncodingException uex) {
      throw new TException("JVM DOES NOT SUPPORT UTF-8");
    }
    writeJSONInteger(message.type);
    writeJSONInteger(message.seqid);
  }

  @Override
  public void writeMessageEnd() throws TException {
    writeJSONArrayEnd();
  }
	 */

	public void writeObjectBegin(TObject struct) throws DriftException {
		writeJSONObjectStart();
	}

	public void writeObjectEnd() throws DriftException {
		writeJSONObjectEnd();
	}

	public void writeAttributeBegin(TAttribute field) throws DriftException {
		writeJSONInteger(field.id);
		writeJSONObjectStart();
		writeJSONString(getTypeNameForTypeID(field.type));
	}

	public void writeAttributeEnd() throws DriftException {
		writeJSONObjectEnd();
	}

	public void writeAttributeStop() {}

	public void writeMapBegin(TMap map) throws DriftException {
		writeJSONArrayStart();
		writeJSONString(getTypeNameForTypeID(map.keyType));
		writeJSONString(getTypeNameForTypeID(map.valueType));
		writeJSONInteger(map.size);
		writeJSONObjectStart();
	}

	public void writeMapEnd() throws DriftException {
		writeJSONObjectEnd();
		writeJSONArrayEnd();
	}

	public void writeListBegin(TList list) throws DriftException {
		writeJSONArrayStart();
		writeJSONString(getTypeNameForTypeID(list.elemType));
		writeJSONInteger(list.size);
	}

	public void writeListEnd() throws DriftException {
		writeJSONArrayEnd();
	}

	public void writeSetBegin(TSet set) throws DriftException {
		writeJSONArrayStart();
		writeJSONString(getTypeNameForTypeID(set.elemType));
		writeJSONInteger(set.size);
	}

	public void writeSetEnd() throws DriftException {
		writeJSONArrayEnd();
	}

	public void writeBool(boolean b) throws DriftException {
		writeJSONInteger(b ? (long)1 : (long)0);
	}

	public void writeByte(byte b) throws DriftException {
		writeJSONInteger((long)b);
	}

	public void writeI16(short i16) throws DriftException {
		writeJSONInteger((long)i16);
	}

	public void writeI32(int i32) throws DriftException {
		writeJSONInteger((long)i32);
	}

	public void writeI64(long i64) throws DriftException {
		writeJSONInteger(i64);
	}

	public void writeDouble(double dub) throws DriftException {
		writeJSONDouble(dub);
	}

	public void writeString(String str) throws DriftException {
		try {
			byte[] b = str.getBytes("UTF-8");
			writeJSONString(b);
		} catch (UnsupportedEncodingException uex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
	}

	public void writeBinary(byte[] bin) throws DriftException {
		writeJSONBase64(bin);
	}

	/**
	 * Reading methods.
	 */

	// Read in a JSON string, unescaping as appropriate.. Skip reading from the
	// context if skipContext is true.
	private TByteArrayOutputStream readJSONString(boolean skipContext)
	throws DriftException {
		TByteArrayOutputStream arr = new TByteArrayOutputStream(DEF_STRING_SIZE);
		if (!skipContext) {
			context_.read();
		}
		readJSONSyntaxChar(QUOTE);
		while (true) {
			byte ch = reader_.read();
			if (ch == QUOTE[0]) {
				break;
			}
			if (ch == ESCSEQ[0]) {
				ch = reader_.read();
				if (ch == ESCSEQ[1]) {
					readJSONSyntaxChar(ZERO);
					readJSONSyntaxChar(ZERO);
					trans.readAll(tmpbuf_, 0, 2);
					ch = (byte)((hexVal((byte)tmpbuf_[0]) << 4) + hexVal(tmpbuf_[1]));
				}
				else {
					int off = ESCAPE_CHARS.indexOf(ch);
					if (off == -1) {
						throw new ProtocolException(ProtocolException.INVALID_DATA,
						"Expected control char");
					}
					ch = ESCAPE_CHAR_VALS[off];
				}
			}
			arr.write(ch);
		}
		return arr;
	}

	// Return true if the given byte could be a valid part of a JSON number.
	private boolean isJSONNumeric(byte b) {
		switch (b) {
		case '+':
		case '-':
		case '.':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case 'E':
		case 'e':
			return true;
		}
		return false;
	}

	// Read in a sequence of characters that are all valid in JSON numbers. Does
	// not do a complete regex check to validate that this is actually a number.
	private String readJSONNumericChars() throws DriftException {
		StringBuilder strbld = new StringBuilder();
		while (true) {
			byte ch = reader_.peek();
			if (!isJSONNumeric(ch)) {
				break;
			}
			strbld.append((char)reader_.read());
		}
		return strbld.toString();
	}

	// Read in a JSON number. If the context dictates, read in enclosing quotes.
	private long readJSONInteger() throws DriftException {
		context_.read();
		if (context_.escapeNum()) {
			readJSONSyntaxChar(QUOTE);
		}
		String str = readJSONNumericChars();
		if (context_.escapeNum()) {
			readJSONSyntaxChar(QUOTE);
		}
		try {
			return Long.valueOf(str);
		}
		catch (NumberFormatException ex) {
			throw new ProtocolException(ProtocolException.INVALID_DATA,
			"Bad data encounted in numeric data");
		}
	}

	// Read in a JSON double value. Throw if the value is not wrapped in quotes
	// when expected or if wrapped in quotes when not expected.
	private double readJSONDouble() throws DriftException {
		context_.read();
		if (reader_.peek() == QUOTE[0]) {
			TByteArrayOutputStream arr = readJSONString(true);
			try {
				double dub = Double.valueOf(arr.toString("UTF-8"));
				if (!context_.escapeNum() && !Double.isNaN(dub) &&
						!Double.isInfinite(dub)) {
					// Throw exception -- we should not be in a string in this case
					throw new ProtocolException(ProtocolException.INVALID_DATA,
					"Numeric data unexpectedly quoted");
				}
				return dub;
			}
			catch (UnsupportedEncodingException ex) {
				throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
			}
		}
		else {
			if (context_.escapeNum()) {
				// This will throw - we should have had a quote if escapeNum == true
				readJSONSyntaxChar(QUOTE);
			}
			try {
				return Double.valueOf(readJSONNumericChars());
			}
			catch (NumberFormatException ex) {
				throw new ProtocolException(ProtocolException.INVALID_DATA,
				"Bad data encounted in numeric data");
			}
		}
	}

	// Read in a JSON string containing base-64 encoded data and decode it.
	private byte[] readJSONBase64() throws DriftException {
		TByteArrayOutputStream arr = readJSONString(false);
		byte[] b = arr.get();
		int len = arr.len();
		int off = 0;
		int size = 0;
		while (len >= 4) {
			// Decode 4 bytes at a time
			Base64Utils.decode(b, off, 4, b, size); // NB: decoded in place
			off += 4;
			len -= 4;
			size += 3;
		}
		// Don't decode if we hit the end or got a single leftover byte (invalid
		// base64 but legal for skip of regular string type)
		if (len > 1) {
			// Decode remainder
			Base64Utils.decode(b, off, len, b, size); // NB: decoded in place
			size += len - 1;
		}
		// Sadly we must copy the byte[] (any way around this?)
		byte [] result = new byte[size];
		System.arraycopy(b, 0, result, 0, size);
		return result;
	}

	private void readJSONObjectStart() throws DriftException {
		context_.read();
		readJSONSyntaxChar(LBRACE);
		pushContext(new JSONPairContext());
	}

	private void readJSONObjectEnd() throws DriftException {
		readJSONSyntaxChar(RBRACE);
		popContext();
	}

	private void readJSONArrayStart() throws DriftException {
		context_.read();
		readJSONSyntaxChar(LBRACKET);
		pushContext(new JSONListContext());
	}

	private void readJSONArrayEnd() throws DriftException {
		readJSONSyntaxChar(RBRACKET);
		popContext();
	}

	/*
  @Override
  public TMessage readMessageBegin() throws TException {
    TMessage message = new TMessage();
    readJSONArrayStart();
    if (readJSONInteger() != VERSION) {
      throw new TProtocolException(TProtocolException.BAD_VERSION,
                                   "Message contained bad version.");
    }
    try {
      message.name = readJSONString(false).toString("UTF-8");
    }
    catch (UnsupportedEncodingException ex) {
      throw new TException("JVM DOES NOT SUPPORT UTF-8");
    }
    message.type = (byte) readJSONInteger();
    message.seqid = (int) readJSONInteger();
    return message;
  }

  @Override
  public void readMessageEnd() throws TException {
    readJSONArrayEnd();
  }
	 */
	public TObject readObjectBegin() throws DriftException {
		readJSONObjectStart();
		return new TObject();
	}

	public void readObjectEnd() throws DriftException {
		readJSONObjectEnd();
	}

	public TAttribute readAttributeBegin() throws DriftException {
		TAttribute field = new TAttribute();
		byte ch = reader_.peek();
		if (ch == RBRACE[0]) {
			field.type = TType.STOP;
		}
		else {
			field.id = (short) readJSONInteger();
			readJSONObjectStart();
			field.type = getTypeIDForTypeName(readJSONString(false).get());
		}
		return field;
	}

	public void readAttributeEnd() throws DriftException {
		readJSONObjectEnd();
	}

	public TMap readMapBegin() throws DriftException {
		TMap map = new TMap();
		readJSONArrayStart();
		map.keyType = getTypeIDForTypeName(readJSONString(false).get());
		map.valueType = getTypeIDForTypeName(readJSONString(false).get());
		map.size = (int)readJSONInteger();
		readJSONObjectStart();
		return map;
	}

	public void readMapEnd() throws DriftException {
		readJSONObjectEnd();
		readJSONArrayEnd();
	}

	public TList readListBegin() throws DriftException {
		TList list = new TList();
		readJSONArrayStart();
		list.elemType = getTypeIDForTypeName(readJSONString(false).get());
		list.size = (int)readJSONInteger();
		return list;
	}

	public void readListEnd() throws DriftException {
		readJSONArrayEnd();
	}

	public TSet readSetBegin() throws DriftException {
		TSet set = new TSet();
		readJSONArrayStart();
		set.elemType = getTypeIDForTypeName(readJSONString(false).get());
		set.size = (int)readJSONInteger();
		return set;
	}

	public void readSetEnd() throws DriftException {
		readJSONArrayEnd();
	}

	public boolean readBool() throws DriftException {
		return (readJSONInteger() == 0 ? false : true);
	}

	public byte readByte() throws DriftException {
		return (byte) readJSONInteger();
	}

	public short readI16() throws DriftException {
		return (short) readJSONInteger();
	}

	public int readI32() throws DriftException {
		return (int) readJSONInteger();
	}

	public long readI64() throws DriftException {
		return (long) readJSONInteger();
	}

	public double readDouble() throws DriftException {
		return readJSONDouble();
	}

	public String readString() throws DriftException {
		try {
			return readJSONString(false).toString("UTF-8");
		}
		catch (UnsupportedEncodingException ex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
	}

	public byte[] readBinary() throws DriftException {
		return readJSONBase64();
	}

	public static boolean isSignatureMatching( byte[] pSignature ) {

		if (pSignature.length < 4) {
			return false;
		}
		
		if (pSignature[0] != MAGIC[0] || pSignature[1] != MAGIC[1]) {
			return false;
		}

		if (pSignature[2] != VERSION[0] || pSignature[3] != VERSION[1]) {
			return false;
		}

		return true;
	}

	public void writeSignature() throws DriftException {
		trans.write(MAGIC);
		trans.write(VERSION);		
	}

	private byte[] signature = new byte[MAGIC.length + VERSION.length];	
	public void skipSignature() throws DriftException {
		trans.readAll(signature, 0, signature.length);
	}


}
