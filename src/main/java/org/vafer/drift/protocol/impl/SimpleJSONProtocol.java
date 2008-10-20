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
import org.vafer.drift.protocol.fields.TAttribute;
import org.vafer.drift.protocol.fields.TList;
import org.vafer.drift.protocol.fields.TMap;
import org.vafer.drift.protocol.fields.TSet;
import org.vafer.drift.protocol.fields.TObject;
import org.vafer.drift.transport.Transport;

/**
 * This protocol is write-only and produces a simple output format
 * suitable for parsing by scripting languages.  It should not be
 * confused with the full-featured JSONProtocol.
 */
public class SimpleJSONProtocol implements Protocol {

	private final Transport trans_;

	public SimpleJSONProtocol(Transport trans) {
		trans_ = trans;
	}

	public static final byte[] COMMA = new byte[] {','};
	public static final byte[] COLON = new byte[] {':'};
	public static final byte[] LBRACE = new byte[] {'{'};
	public static final byte[] RBRACE = new byte[] {'}'};
	public static final byte[] LBRACKET = new byte[] {'['};
	public static final byte[] RBRACKET = new byte[] {']'};
	public static final char QUOTE = '"';

	protected class Context {
		protected void write() throws DriftException {}
	}

	protected class ListContext extends Context {
		protected boolean first_ = true;

		protected void write() throws DriftException {
			if (first_) {
				first_ = false;
			} else {
				trans_.write(COMMA);
			}
		}
	}

	protected class StructContext extends Context {
		protected boolean first_ = true;
		protected boolean colon_ = true;

		protected void write() throws DriftException {
			if (first_) {
				first_ = false;
				colon_ = true;
			} else {
				trans_.write(colon_ ? COLON : COMMA);
				colon_ = !colon_;
			}
		}
	}

	protected final Context BASE_CONTEXT = new Context();

	/**
	 * Stack of nested contexts that we may be in.
	 */
	protected Stack<Context> writeContextStack_ = new Stack<Context>();

	/**
	 * Current context that we are in
	 */
	protected Context writeContext_ = BASE_CONTEXT;

	/**
	 * Push a new write context onto the stack.
	 */
	protected void pushWriteContext(Context c) {
		writeContextStack_.push(writeContext_);
		writeContext_ = c;
	}

	/**
	 * Pop the last write context off the stack
	 */
	protected void popWriteContext() {
		writeContext_ = writeContextStack_.pop();
	}


	/*
  public void writeMessageBegin(TMessage message) throws TException {
    trans_.write(LBRACKET);
    pushWriteContext(new ListContext());
    writeString(message.name);
    writeByte(message.type);
    writeI32(message.seqid);
  }*/

	public void writeMessageEnd() throws DriftException {
		popWriteContext();
		trans_.write(RBRACKET);
	}

	public void writeObjectBegin(TObject struct) throws DriftException {
		writeContext_.write();
		trans_.write(LBRACE);
		pushWriteContext(new StructContext());
	}

	public void writeObjectEnd() throws DriftException {
		popWriteContext();
		trans_.write(RBRACE);
	}

	public void writeAttributeBegin(TAttribute field) throws DriftException {
		// Note that extra type information is omitted in JSON!
		writeString(field.name);
	}

	public void writeAttributeEnd() {}

	public void writeAttributeStop() {}

	public void writeMapBegin(TMap map) throws DriftException {
		writeContext_.write();
		trans_.write(LBRACE);
		pushWriteContext(new StructContext());
		// No metadata!
	}

	public void writeMapEnd() throws DriftException {
		popWriteContext();
		trans_.write(RBRACE);
	}

	public void writeListBegin(TList list) throws DriftException {
		writeContext_.write();
		trans_.write(LBRACKET);
		pushWriteContext(new ListContext());
		// No metadata!
	}

	public void writeListEnd() throws DriftException {
		popWriteContext();
		trans_.write(RBRACKET);
	}

	public void writeSetBegin(TSet set) throws DriftException {
		writeContext_.write();
		trans_.write(LBRACKET);
		pushWriteContext(new ListContext());
		// No metadata!
	}

	public void writeSetEnd() throws DriftException {
		popWriteContext();
		trans_.write(RBRACKET);
	}

	public void writeBool(boolean b) throws DriftException {
		writeByte(b ? (byte)1 : (byte)0);
	}

	public void writeByte(byte b) throws DriftException {
		writeI32(b);
	}

	public void writeI16(short i16) throws DriftException {
		writeI32(i16);
	}

	public void writeI32(int i32) throws DriftException {
		writeContext_.write();
		_writeStringData(Integer.toString(i32));
	}

	public void _writeStringData(String s) throws DriftException {
		try {
			byte[] b = s.getBytes("UTF-8");
			trans_.write(b);
		} catch (UnsupportedEncodingException uex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
	}

	public void writeI64(long i64) throws DriftException {
		writeContext_.write();
		_writeStringData(Long.toString(i64));
	}

	public void writeDouble(double dub) throws DriftException {
		writeContext_.write();
		_writeStringData(Double.toString(dub));
	}

	public void writeString(String str) throws DriftException {
		writeContext_.write();
		int length = str.length();
		StringBuffer escape = new StringBuffer(length + 16);
		escape.append(QUOTE);
		for (int i = 0; i < length; ++i) {
			char c = str.charAt(i);
			switch (c) {
			case '"':
			case '\\':
				escape.append('\\');
				escape.append(c);
				break;
			case '\b':
				escape.append('\\');
				escape.append('b');
				break;
			case '\f':
				escape.append('\\');
				escape.append('f');
				break;
			case '\n':
				escape.append('\\');
				escape.append('n');
				break;
			case '\r':
				escape.append('\\');
				escape.append('r');
				break;
			case '\t':
				escape.append('\\');
				escape.append('t');
				break;
			default:
				// Control characeters! According to JSON RFC u0020 (space)
				if (c < ' ') {
					String hex = Integer.toHexString(c);
					escape.append('\\');
					escape.append('u');
					for (int j = 4; j > hex.length(); --j) {
						escape.append('0');
					}
					escape.append(hex);
				} else {
					escape.append(c);
				}
			break;
			}
		}
		escape.append(QUOTE);
		_writeStringData(escape.toString());
	}

	public void writeBinary(byte[] bin) throws DriftException {
		try {
			// TODO(mcslee): Fix this
			writeString(new String(bin, "UTF-8"));
		} catch (UnsupportedEncodingException uex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
	}

	/**
	 * Reading methods.

  public TMessage readMessageBegin() throws TException {
    TMessage message = new TMessage();
    // TODO(mcslee): implement
    return message;
  }

  public void readMessageEnd() {}
	 */

	public TObject readObjectBegin() {
		// TODO(mcslee): implement
		return new TObject();
	}

	public void readObjectEnd() {}

	public TAttribute readAttributeBegin() throws DriftException {
		TAttribute field = new TAttribute();
		// TODO(mcslee): implement
		return field;
	}

	public void readAttributeEnd() {}

	public TMap readMapBegin() throws DriftException {
		TMap map = new TMap();
		// TODO(mcslee): implement
		return map;
	}

	public void readMapEnd() {}

	public TList readListBegin() throws DriftException {
		TList list = new TList();
		// TODO(mcslee): implement
		return list;
	}

	public void readListEnd() {}

	public TSet readSetBegin() throws DriftException {
		TSet set = new TSet();
		// TODO(mcslee): implement
		return set;
	}

	public void readSetEnd() {}

	public boolean readBool() throws DriftException {
		return (readByte() == 1);
	}

	public byte readByte() throws DriftException {
		// TODO(mcslee): implement
		return 0;
	}

	public short readI16() throws DriftException {
		// TODO(mcslee): implement
		return 0;
	}

	public int readI32() throws DriftException {
		// TODO(mcslee): implement
		return 0;
	}

	public long readI64() throws DriftException {
		// TODO(mcslee): implement
		return 0;
	}

	public double readDouble() throws DriftException {
		// TODO(mcslee): implement
		return 0;
	}

	public String readString() throws DriftException {
		// TODO(mcslee): implement
		return "";
	}

	public String readStringBody(int size) throws DriftException {
		// TODO(mcslee): implement
		return "";
	}

	public byte[] readBinary() throws DriftException {
		// TODO(mcslee): implement
		return new byte[0];
	}

	public void writeSignature() throws DriftException {
		// TODO Auto-generated method stub

	}


}
