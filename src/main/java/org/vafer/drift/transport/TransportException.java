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
package org.vafer.drift.transport;

import org.vafer.drift.DriftException;


public class TransportException extends DriftException {

	private static final long serialVersionUID = 1L;

	public static final int UNKNOWN = 0;
	public static final int NOT_OPEN = 1;
	public static final int ALREADY_OPEN = 2;
	public static final int TIMED_OUT = 3;
	public static final int END_OF_FILE = 4;

	private int type = UNKNOWN;

	public TransportException() {
		super();
	}

	public TransportException(int pType) {
		super();
		type = pType;
	}

	public TransportException(int pType, String message) {
		super(message);
		type = pType;
	}

	public TransportException(String message) {
		super(message);
	}

	public TransportException(int pType, Throwable cause) {
		super(cause);
		type = pType;
	}

	public TransportException(Throwable cause) {
		super(cause);
	}

	public TransportException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransportException(int pType, String message, Throwable cause) {
		super(message, cause);
		type = pType;
	}

	public int getType() {
		return type;
	}

}
