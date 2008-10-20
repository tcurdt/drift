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

import org.vafer.drift.DriftException;
import org.vafer.drift.protocol.Protocol;
import org.vafer.drift.protocol.fields.TAttribute;
import org.vafer.drift.protocol.fields.TList;
import org.vafer.drift.protocol.fields.TMap;
import org.vafer.drift.protocol.fields.TObject;
import org.vafer.drift.protocol.fields.TSet;

public class ProtocolLogger implements Protocol {

	private final Protocol delegate;
	
	public ProtocolLogger( final Protocol pDelegate ) {
		delegate = pDelegate;
	}

	public byte[] readBinary() throws DriftException {
		System.out.println("readBinary");
		return delegate.readBinary();
	}

	public boolean readBool() throws DriftException {
		System.out.println("readBool");
		return delegate.readBool();
	}

	public byte readByte() throws DriftException {
		System.out.println("readByte");
		return delegate.readByte();
	}

	public double readDouble() throws DriftException {
		System.out.println("readDouble");
		return delegate.readDouble();
	}

	public TAttribute readAttributeBegin() throws DriftException {
		System.out.println("readFieldBegin");
		return delegate.readAttributeBegin();
	}

	public void readAttributeEnd() throws DriftException {
		System.out.println("readFieldEnd");
		delegate.readAttributeEnd();
	}

	public short readI16() throws DriftException {
		System.out.println("readI16");
		return delegate.readI16();
	}

	public int readI32() throws DriftException {
		System.out.println("readI32");
		return delegate.readI32();
	}

	public long readI64() throws DriftException {
		System.out.println("readI64");
		return delegate.readI64();
	}

	public TList readListBegin() throws DriftException {
		System.out.println("readListBegin");
		return delegate.readListBegin();
	}

	public void readListEnd() throws DriftException {
		System.out.println("readListEnd");
		delegate.readListEnd();
	}

	public TMap readMapBegin() throws DriftException {
		System.out.println("readMapBegin");
		return delegate.readMapBegin();
	}

	public void readMapEnd() throws DriftException {
		System.out.println("readMapEnd");
		delegate.readMapEnd();
	}

	public TSet readSetBegin() throws DriftException {
		System.out.println("readSetBegin");
		return delegate.readSetBegin();
	}

	public void readSetEnd() throws DriftException {
		System.out.println("readSetEnd");
		delegate.readSetEnd();
	}

	public String readString() throws DriftException {
		System.out.println("readString");
		return delegate.readString();
	}

	public TObject readObjectBegin() throws DriftException {
		System.out.println("readStructBegin");
		return delegate.readObjectBegin();
	}

	public void readObjectEnd() throws DriftException {
		System.out.println("readStructEnd");
		delegate.readObjectEnd();
	}

	public void writeBinary(byte[] bin) throws DriftException {
		System.out.println("writeBinary");
		delegate.writeBinary(bin);
	}

	public void writeBool(boolean b) throws DriftException {
		System.out.println("writeBool");
		delegate.writeBool(b);
	}

	public void writeByte(byte b) throws DriftException {
		System.out.println("writeByte");
		delegate.writeByte(b);
	}

	public void writeDouble(double dub) throws DriftException {
		System.out.println("writeDouble");
		delegate.writeDouble(dub);
	}

	public void writeAttributeBegin(TAttribute field) throws DriftException {
		System.out.println("writeFieldBegin");
		delegate.writeAttributeBegin(field);
	}

	public void writeAttributeEnd() throws DriftException {
		System.out.println("writeFieldEnd");
		delegate.writeAttributeEnd();
	}

	public void writeAttributeStop() throws DriftException {
		System.out.println("writeFieldStop");
		delegate.writeAttributeStop();
	}

	public void writeI16(short i16) throws DriftException {
		System.out.println("writeI16");
		delegate.writeI16(i16);
	}

	public void writeI32(int i32) throws DriftException {
		System.out.println("writeI32");
		delegate.writeI32(i32);
	}

	public void writeI64(long i64) throws DriftException {
		System.out.println("writeI64");
		delegate.writeI64(i64);
	}

	public void writeListBegin(TList list) throws DriftException {
		System.out.println("writeListBegin");
		delegate.writeListBegin(list);
	}

	public void writeListEnd() throws DriftException {
		System.out.println("writeListEnd");
		delegate.writeListEnd();
	}

	public void writeMapBegin(TMap map) throws DriftException {
		System.out.println("writeMapBegin");
		delegate.writeMapBegin(map);
	}

	public void writeMapEnd() throws DriftException {
		System.out.println("writeMapEnd");
		delegate.writeMapEnd();
	}

	public void writeSetBegin(TSet set) throws DriftException {
		System.out.println("writeSetBegin");
		delegate.writeSetBegin(set);
	}

	public void writeSetEnd() throws DriftException {
		System.out.println("writeSetEnd");
		delegate.writeSetEnd();
	}

	public void writeSignature() throws DriftException {
		System.out.println("writeSignature");
		delegate.writeSignature();
	}

	public void writeString(String str) throws DriftException {
		System.out.println("writeString");
		delegate.writeString(str);
	}

	public void writeObjectBegin(TObject struct) throws DriftException {
		System.out.println("writeStructBegin");
		delegate.writeObjectBegin(struct);
	}

	public void writeObjectEnd() throws DriftException {
		System.out.println("writeStructEnd");
		delegate.writeObjectEnd();
	}

}
