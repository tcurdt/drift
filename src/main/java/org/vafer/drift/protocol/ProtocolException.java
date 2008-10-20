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

public class ProtocolException extends DriftException {


	private static final long serialVersionUID = 1L;

	public static final int UNKNOWN = 0;
	public static final int INVALID_DATA = 1;
	public static final int NEGATIVE_SIZE = 2;
	public static final int SIZE_LIMIT = 3;
	public static final int BAD_VERSION = 4;
	public static final int NOT_IMPLEMENTED = 5;

	private int type = UNKNOWN;

	public ProtocolException() {
		super();
	}

	public ProtocolException(int pType) {
		super();
		type = pType;
	}

	public ProtocolException(int pType, String message) {
		super(message);
		type = pType;
	}

	public ProtocolException(String message) {
		super(message);
	}

	public ProtocolException(int pType, Throwable cause) {
		super(cause);
		type = pType;
	}

	public ProtocolException(Throwable cause) {
		super(cause);
	}

	public ProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProtocolException(int pType, String message, Throwable cause) {
		super(message, cause);
		type = pType;
	}

	public int getType() {
		return type;
	}

}
