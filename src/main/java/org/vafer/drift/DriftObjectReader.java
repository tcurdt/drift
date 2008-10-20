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

import java.io.InputStream;

import org.vafer.drift.protocol.Protocol;
import org.vafer.drift.protocol.ProtocolFactory;
import org.vafer.drift.protocol.fields.TObject;
import org.vafer.drift.transport.Transport;
import org.vafer.drift.transport.impl.IOStreamTransport;


public class DriftObjectReader {

	private final InputStream inputStream;
	private final Transport transport;
	private final Protocol protocol;
	private final DriftObjectFactory factory;

	public DriftObjectReader( final InputStream pInputStream, final DriftObjectFactory pFactory) throws DriftException {
		inputStream = pInputStream;
		factory = pFactory;

		transport = new IOStreamTransport().readFrom(inputStream);
		protocol = new ProtocolFactory().getProtocolFrom(transport);		
		//protocol = new ProtocolLogger(new ProtocolFactory().getProtocolFrom(transport));		
	}
	
	public DriftObject read() throws DriftException {
		final TObject field = protocol.readObjectBegin();		
		final DriftObject object = factory.createDriftObject(field);		
		object.read(protocol);		
		protocol.readObjectEnd();
		return object;
	}

	public void close() {
		transport.close();
	}
}
