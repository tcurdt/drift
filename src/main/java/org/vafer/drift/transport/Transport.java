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

/**
 * Generic class that encapsulates the I/O layer. This is basically a thin
 * wrapper around the combined functionality of Java input/output streams.
 */
public abstract class Transport {

	public abstract boolean isOpen();

	public boolean peek() {
		return isOpen();
	}

	public abstract void open() throws TransportException;

	public abstract void close();

	public abstract int read(byte[] buf, int off, int len) throws TransportException;

	/**
	 * Guarantees that all of len bytes are actually read off the transport.
	 */
	public int readAll(byte[] buf, int off, int len) throws TransportException {
		int got = 0;
		int ret = 0;
		while (got < len) {
			ret = read(buf, off+got, len-got);
			if (ret <= 0) {
				throw new TransportException("Cannot read. Remote side has closed. Tried to read " + len + " bytes, but only got " + got + " bytes.");
			}
			got += ret;
		}
		return got;
	}

	public void write(byte[] buf) throws TransportException {
		write(buf, 0, buf.length);
	}

	public abstract void write(byte[] buf, int off, int len) throws TransportException;

	public void flush() throws TransportException {}
}
