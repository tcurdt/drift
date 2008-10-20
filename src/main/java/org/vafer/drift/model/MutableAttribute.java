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
package org.vafer.drift.model;


public final class MutableAttribute extends MutableSlot implements ImmutableAttribute {

	private boolean required;
	private String name;

	public MutableAttribute( int slot ) {
		super(slot);
	}
	
	public void setRequired( boolean pRequired ) {
		required = pRequired;
	}
	
	public boolean isRequired() {
		return required;
	}

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		name = pName;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(name).append(":").append(super.toString()).append("}");
		return sb.toString();
	}
}
