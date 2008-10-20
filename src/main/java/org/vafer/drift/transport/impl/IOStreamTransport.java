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
package org.vafer.drift.transport.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.vafer.drift.transport.Transport;
import org.vafer.drift.transport.TransportException;

/**
 * This is the most commonly used base transport. It takes an InputStream
 * and an OutputStream and uses those to perform all transport operations.
 * This allows for compatibility with all the nice constructs Java already
 * has to provide a variety of types of streams.
 */
public class IOStreamTransport extends Transport {

	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	public IOStreamTransport() {	  
	}

	public IOStreamTransport readFrom( final InputStream pInputStream ) {
		inputStream = pInputStream;
		return this;
	}

	public IOStreamTransport writeTo( final OutputStream pOutputStream ) {
		outputStream = pOutputStream;
		return this;
	}

	/**
	 * The streams must already be open at construction time, so this should
	 * always return true.
	 *
	 * @return true
	 */
	public boolean isOpen() {
		return true;
	}

	/**
	 * The streams must already be open. This method does nothing.
	 */
	public void open() throws TransportException {}

	/**
	 * Closes both the input and output streams.
	 */
	public void close() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException iox) {
			}
			inputStream = null;
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException iox) {
			}
			outputStream = null;
		}
	}

	/**
	 * Reads from the underlying input stream if not null.
	 */
	public int read(byte[] buf, int off, int len) throws TransportException {
		if (inputStream == null) {
			throw new TransportException(TransportException.NOT_OPEN, "Cannot read from null inputStream");
		}
		try {
			return inputStream.read(buf, off, len);
		} catch (IOException iox) {
			throw new TransportException(TransportException.UNKNOWN, iox);
		}
	}

	/**
	 * Writes to the underlying output stream if not null.
	 */
	public void write(byte[] buf, int off, int len) throws TransportException {
		if (outputStream == null) {
			throw new TransportException(TransportException.NOT_OPEN, "Cannot write to null outputStream");
		}
		try {
			outputStream.write(buf, off, len);
		} catch (IOException iox) {
			throw new TransportException(TransportException.UNKNOWN, iox);
		}
	}

	/**
	 * Flushes the underlying output stream if not null.
	 */
	public void flush() throws TransportException {
		if (outputStream == null) {
			throw new TransportException(TransportException.NOT_OPEN, "Cannot flush null outputStream");
		}
		try {
			outputStream.flush();
		} catch (IOException iox) {
			throw new TransportException(TransportException.UNKNOWN, iox);
		}
	}
}
