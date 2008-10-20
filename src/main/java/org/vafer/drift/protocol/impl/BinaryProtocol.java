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

import org.vafer.drift.DriftException;
import org.vafer.drift.protocol.Protocol;
import org.vafer.drift.protocol.fields.TAttribute;
import org.vafer.drift.protocol.fields.TList;
import org.vafer.drift.protocol.fields.TMap;
import org.vafer.drift.protocol.fields.TSet;
import org.vafer.drift.protocol.fields.TObject;
import org.vafer.drift.protocol.fields.TType;
import org.vafer.drift.transport.Transport;

public final class BinaryProtocol implements Protocol {

	private static final byte[] MAGIC = { 0x7d, 0x7e };
	private static final byte[] VERSION = { 0x00, 0x01 };

	private Transport transport;
	
	public BinaryProtocol( final Transport pTransport ) {
		transport = pTransport;
	}
	
	public void writeSignature() throws DriftException {
		transport.write(MAGIC);
		transport.write(VERSION);		
	}
	

	private byte[] signature = new byte[MAGIC.length + VERSION.length];	
	public void skipSignature() throws DriftException {
		transport.readAll(signature, 0, signature.length);
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

	
	public void writeObjectBegin(TObject struct) throws DriftException {
		writeString(struct.name);
	}

	public void writeObjectEnd() {
	}

	public void writeAttributeBegin(TAttribute field) throws DriftException {
		writeByte(field.type);
		writeI32(field.id);
	}

	public void writeAttributeEnd() {}

	public void writeAttributeStop() throws DriftException {
		writeByte(TType.STOP);
	}

	public void writeMapBegin(TMap map) throws DriftException {
		writeByte(map.keyType);
		writeByte(map.valueType);
		writeI32(map.size);
	}

	public void writeMapEnd() {}

	public void writeListBegin(TList list) throws DriftException {
		writeByte(list.elemType);
		writeI32(list.size);
	}

	public void writeListEnd() {}

	public void writeSetBegin(TSet set) throws DriftException {
		writeByte(set.elemType);
		writeI32(set.size);
	}

	public void writeSetEnd() {}

	public void writeBool(boolean b) throws DriftException {
		writeByte(b ? (byte)1 : (byte)0);
	}

	private byte [] bout = new byte[1];
	public void writeByte(byte b) throws DriftException {
		bout[0] = b;
		transport.write(bout, 0, 1);
	}

	private byte[] i16out = new byte[2];
	public void writeI16(short i16) throws DriftException {
		i16out[0] = (byte)(0xff & (i16 >> 8));
		i16out[1] = (byte)(0xff & (i16));
		transport.write(i16out, 0, 2);
	}

	private byte[] i32out = new byte[4];
	public void writeI32(int i32) throws DriftException {
		i32out[0] = (byte)(0xff & (i32 >> 24));
		i32out[1] = (byte)(0xff & (i32 >> 16));
		i32out[2] = (byte)(0xff & (i32 >> 8));
		i32out[3] = (byte)(0xff & (i32));
		transport.write(i32out, 0, 4);
	}

	private byte[] i64out = new byte[8];
	public void writeI64(long i64) throws DriftException {
		i64out[0] = (byte)(0xff & (i64 >> 56));
		i64out[1] = (byte)(0xff & (i64 >> 48));
		i64out[2] = (byte)(0xff & (i64 >> 40));
		i64out[3] = (byte)(0xff & (i64 >> 32));
		i64out[4] = (byte)(0xff & (i64 >> 24));
		i64out[5] = (byte)(0xff & (i64 >> 16));
		i64out[6] = (byte)(0xff & (i64 >> 8));
		i64out[7] = (byte)(0xff & (i64));
		transport.write(i64out, 0, 8);
	}

	public void writeDouble(double dub) throws DriftException {
		writeI64(Double.doubleToLongBits(dub));
	}

	public void writeString(String str) throws DriftException {
		try {
			byte[] dat = str.getBytes("UTF-8");
			writeI32(dat.length);
			transport.write(dat, 0, dat.length);
		} catch (UnsupportedEncodingException uex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
	}

	public void writeBinary(byte[] bin) throws DriftException {
		writeI32(bin.length);
		transport.write(bin, 0, bin.length);
	}

	public TObject readObjectBegin() throws DriftException {
		final String name = readString();
		return new TObject(name);
	}

	public void readObjectEnd() {}

	public TAttribute readAttributeBegin() throws DriftException {
		TAttribute field = new TAttribute();
		field.type = readByte();
		if (field.type != TType.STOP) {
			field.id = readI32();
		}
		return field;
	}

	public void readAttributeEnd() {}

	public TMap readMapBegin() throws DriftException {
		TMap map = new TMap();
		map.keyType = readByte();
		map.valueType = readByte();
		map.size = readI32();
		return map;
	}

	public void readMapEnd() {}

	public TList readListBegin() throws DriftException {
		TList list = new TList();
		list.elemType = readByte();
		list.size = readI32();
		return list;
	}

	public void readListEnd() {}

	public TSet readSetBegin() throws DriftException {
		TSet set = new TSet();
		set.elemType = readByte();
		set.size = readI32();
		return set;
	}

	public void readSetEnd() {}

	public boolean readBool() throws DriftException {
		return (readByte() == 1);
	}

	private byte[] bin = new byte[1];
	public byte readByte() throws DriftException {
		readAll(bin, 0, 1);
		return bin[0];
	}

	private byte[] i16rd = new byte[2];
	public short readI16() throws DriftException {
		readAll(i16rd, 0, 2);
		return
		(short)
		(((i16rd[0] & 0xff) << 8) |
				((i16rd[1] & 0xff)));
	}

	private byte[] i32rd = new byte[4];
	public int readI32() throws DriftException {
		readAll(i32rd, 0, 4);
		return
		((i32rd[0] & 0xff) << 24) |
		((i32rd[1] & 0xff) << 16) |
		((i32rd[2] & 0xff) <<  8) |
		((i32rd[3] & 0xff));
	}

	private byte[] i64rd = new byte[8];
	public long readI64() throws DriftException {
		readAll(i64rd, 0, 8);
		return
		((long)(i64rd[0] & 0xff) << 56) |
		((long)(i64rd[1] & 0xff) << 48) |
		((long)(i64rd[2] & 0xff) << 40) |
		((long)(i64rd[3] & 0xff) << 32) |
		((long)(i64rd[4] & 0xff) << 24) |
		((long)(i64rd[5] & 0xff) << 16) |
		((long)(i64rd[6] & 0xff) <<  8) |
		((long)(i64rd[7] & 0xff));
	}

	public double readDouble() throws DriftException {
		return Double.longBitsToDouble(readI64());
	}

	public String readString() throws DriftException {
		int size = readI32();
		return readStringBody(size);
	}

	public String readStringBody(int size) throws DriftException {
		try {
			//checkReadLength(size);
			byte[] buf = new byte[size];
			transport.readAll(buf, 0, size);
			return new String(buf, "UTF-8");
		} catch (UnsupportedEncodingException uex) {
			throw new DriftException("JVM DOES NOT SUPPORT UTF-8");
		}
	}

	public byte[] readBinary() throws DriftException {
		int size = readI32();
		//checkReadLength(size);
		byte[] buf = new byte[size];
		readAll(buf, 0, size);
		return buf;
	}

	private int readAll(byte[] buf, int off, int len) throws DriftException {
		//checkReadLength(len);
		return transport.readAll(buf, off, len);
	}

	/*
	public void setReadLength(int readLength) {
		readLength_ = readLength;
		checkReadLength_ = true;
	}

	protected void checkReadLength(int length) throws TException {
		if (checkReadLength_) {
			readLength_ -= length;
			if (readLength_ < 0) {
				throw new TException("Message length exceeded: " + length);
			}
		}
	}
	*/

}
