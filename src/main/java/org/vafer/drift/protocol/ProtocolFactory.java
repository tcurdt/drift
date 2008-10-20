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
import org.vafer.drift.protocol.impl.BinaryProtocol;
import org.vafer.drift.protocol.impl.JSONProtocol;
import org.vafer.drift.transport.Transport;

public class ProtocolFactory {

	public Protocol getProtocolFrom( Transport pInput ) throws DriftException {
		final byte[] signature = new byte[4];
		pInput.read(signature, 0, signature.length);
		
		if (BinaryProtocol.isSignatureMatching(signature)) {
			return new BinaryProtocol(pInput);
		}

		if (JSONProtocol.isSignatureMatching(signature)) {
			return new JSONProtocol(pInput);
		}

		return null;
	}
	
}
