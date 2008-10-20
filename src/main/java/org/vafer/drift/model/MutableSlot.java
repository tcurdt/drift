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

import java.util.HashSet;
import java.util.Set;

public class MutableSlot implements ImmutableSlot {

	private final int number;

	private String name;
	private String type;

	private Set<ImmutableSlot> parents = new HashSet<ImmutableSlot>();
	private Set<ImmutableSlot> childs = new HashSet<ImmutableSlot>();
	
	public MutableSlot( int slot ) {
		number = slot;
	}
	
	public ImmutableSlot[] getChilds() {
		final ImmutableSlot[] ret = new ImmutableSlot[childs.size()];
		childs.toArray(ret);
		return ret;
	}

	public ImmutableSlot[] getParents() {
		final ImmutableSlot[] ret = new ImmutableSlot[parents.size()];
		parents.toArray(ret);
		return ret;
	}

	public void addParent(MutableSlot pParent) {
		parents.add(pParent);
		pParent.childs.add(this);
	}

	public void addChild(MutableSlot pChild) {
		childs.add(pChild);		
		pChild.parents.add(this);
	}

	public String getSlotName() {
		return name;
	}

	public void setSlotName(String pName) {
		name = pName;
	}
	
	public int getSlotNumber() {
		return number;
	}
	
	public void setType(String pType) {
		type = pType;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ImmutableSlot other = (ImmutableSlot) obj;
		if (number != other.getSlotNumber())
			return false;
		return true;
	}

	public int hashCode() {
		return number;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(name).append("->#").append(number);
		sb.append(";p").append(parents.size());
		sb.append(";c").append(childs.size());
		return sb.toString();
	}
}
