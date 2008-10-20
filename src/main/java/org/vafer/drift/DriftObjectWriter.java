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

import java.io.OutputStream;

import org.vafer.drift.protocol.Protocol;
import org.vafer.drift.protocol.impl.BinaryProtocol;
import org.vafer.drift.transport.Transport;
import org.vafer.drift.transport.impl.IOStreamTransport;

public class DriftObjectWriter {

	private final OutputStream outputStream;
	private final Transport transport;
	private final Protocol protocol;
	
	public DriftObjectWriter( final OutputStream pOutputStream ) throws DriftException {
		outputStream = pOutputStream;
		transport = new IOStreamTransport().writeTo(outputStream);
		protocol = new BinaryProtocol(transport);
		//protocol = new ProtocolLogger(new BinaryProtocol(transport));

		protocol.writeSignature();
	}
	
	public void write( final DriftObject pObject ) throws DriftException {
		pObject.write(protocol);
	}
	
	public void close() {
		transport.close();
	}
}
