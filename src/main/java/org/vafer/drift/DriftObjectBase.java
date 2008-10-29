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
package org.vafer.drift;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.vafer.drift.protocol.Protocol;
import org.vafer.drift.protocol.impl.BinaryProtocol;
import org.vafer.drift.transport.Transport;
import org.vafer.drift.transport.impl.IOStreamTransport;


public abstract class DriftObjectBase implements DriftObject {

	/* Serializable
 	private void writeObject( ObjectOutputStream out ) throws IOException {
		final Transport transport = new IOStreamTransport().writeTo(out);
		final Protocol protocol = new BinaryProtocol(transport);
		this.write(protocol);
	}

	private void readObject( ObjectInputStream in ) throws IOException, ClassNotFoundException {
		final Transport transport = new IOStreamTransport().readFrom(in);
		final Protocol protocol = new BinaryProtocol(transport);
		this.read(protocol);
	}
	*/

	protected void readFromDriftObject( DriftObject o ) throws DriftException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		{
			final Transport transport = new IOStreamTransport().writeTo(out);
			final Protocol protocol = new BinaryProtocol(transport);
			o.write(protocol);
		}
		{
			final Transport transport = new IOStreamTransport().readFrom(new ByteArrayInputStream(out.toByteArray()));
			final Protocol protocol = new BinaryProtocol(transport);
			this.read(protocol);
		}		
	}
}
