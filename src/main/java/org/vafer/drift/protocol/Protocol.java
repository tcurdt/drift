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
package org.vafer.drift.protocol;

import org.vafer.drift.DriftException;
import org.vafer.drift.protocol.fields.TAttribute;
import org.vafer.drift.protocol.fields.TList;
import org.vafer.drift.protocol.fields.TMap;
import org.vafer.drift.protocol.fields.TSet;
import org.vafer.drift.protocol.fields.TObject;


public interface Protocol {


	void writeSignature() throws DriftException;

	/**
	 * Writing methods.
	 */

	void writeObjectBegin(TObject struct) throws DriftException;

	void writeObjectEnd() throws DriftException;

	void writeAttributeBegin(TAttribute field) throws DriftException;

	void writeAttributeEnd() throws DriftException;

	void writeAttributeStop() throws DriftException;

	void writeMapBegin(TMap map) throws DriftException;

	void writeMapEnd() throws DriftException;

	void writeListBegin(TList list) throws DriftException;

	void writeListEnd() throws DriftException;

	void writeSetBegin(TSet set) throws DriftException;

	void writeSetEnd() throws DriftException;

	void writeBool(boolean b) throws DriftException;

	void writeByte(byte b) throws DriftException;

	void writeI16(short i16) throws DriftException;

	void writeI32(int i32) throws DriftException;

	void writeI64(long i64) throws DriftException;

	void writeDouble(double dub) throws DriftException;

	void writeString(String str) throws DriftException;

	void writeBinary(byte[] bin) throws DriftException;

	/**
	 * Reading methods.
	 */

	TObject readObjectBegin() throws DriftException;

	void readObjectEnd() throws DriftException;

	TAttribute readAttributeBegin() throws DriftException;

	void readAttributeEnd() throws DriftException;

	TMap readMapBegin() throws DriftException;

	void readMapEnd() throws DriftException;

	TList readListBegin() throws DriftException;

	void readListEnd() throws DriftException;

	TSet readSetBegin() throws DriftException;

	void readSetEnd() throws DriftException;

	boolean readBool() throws DriftException;

	byte readByte() throws DriftException;

	short readI16() throws DriftException;

	int readI32() throws DriftException;

	long readI64() throws DriftException;

	double readDouble() throws DriftException;

	String readString() throws DriftException;

	byte[] readBinary() throws DriftException;

}
