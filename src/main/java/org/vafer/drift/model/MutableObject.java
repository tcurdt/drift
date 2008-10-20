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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class MutableObject implements ImmutableObject {

	private final Set<String> parents = new HashSet<String>();
	private final Map<Integer, MutableAttribute> attributes = new HashMap<Integer, MutableAttribute>();

	private final Map<Integer, MutableSlot> slots = new HashMap<Integer, MutableSlot>();

	private String name;
	
	public void setName( final String pName ) {
		name = pName;
	}
	
	public String getName() {
		return name;
	}
		
	public void addParent( final String pParent ) {		
		parents.add(pParent);
	}
	
	public Set<String> getParents() {
		return new HashSet<String>(parents);
	}

	public MutableSlot createSlot( int pSlotNumber ) {
		MutableSlot slot = slots.get(pSlotNumber);
		
		if (slot == null) {
			slot = new MutableSlot(pSlotNumber);
			slots.put(pSlotNumber, slot);
		}
		
		return slot;
	}
	
	public MutableAttribute addAttribute( int pSlotNumber ) {
		
		MutableAttribute attribute = attributes.get(pSlotNumber);
		
		if (attribute == null) {
			attribute = new MutableAttribute(pSlotNumber);
			attributes.put(pSlotNumber, attribute);
			
			if (slots.containsKey(pSlotNumber)) {
				throw new RuntimeException("");
			}
			
			slots.put(pSlotNumber, attribute);
		}

		return attribute;
	}

	public ImmutableAttribute[] getAttributes() {
		final ImmutableAttribute[] ret = new ImmutableAttribute[attributes.size()];
		attributes.values().toArray(ret);
		return ret;
	}

	public ImmutableSlot[] getSlots() {
		final ImmutableSlot[] ret = new ImmutableSlot[slots.size()];
		slots.values().toArray(ret);
		return ret;
	}
	
	public FlatStruct flatten(Map<String, MutableObject> hierarchicalVersionsMap) {
		final Set<MutableObject> transitiveParents = new HashSet<MutableObject>(); 
			
		findTransitiveParents(hierarchicalVersionsMap, transitiveParents);
		
		final MutableObject flat = new MutableObject();
		
		flat.name = this.name;
				
		for (MutableObject parent : transitiveParents) {
			flat.parents.add(parent.getName());						
			flat.attributes.putAll(parent.attributes);
			flat.slots.putAll(parent.slots);
		}
		
		flat.attributes.putAll(attributes);
		flat.slots.putAll(slots);
		
		return new FlatStruct(flat);
	}

	private void findTransitiveParents( final Map<String, MutableObject> hierarchicalVersionsMap, final Set<MutableObject> transitiveParents) {
		for (String parent : parents) {
			final MutableObject p = hierarchicalVersionsMap.get(parent);
			
			if (p == null) {
				throw new RuntimeException("No such class " + parent);
			}
			
			if (!transitiveParents.contains(p)) {
				transitiveParents.add(p);
				
				p.findTransitiveParents(hierarchicalVersionsMap, transitiveParents);				
			}
		}		
	}
	
	public final class FlatStruct implements ImmutableObject {

		private final String name;
		private final Set<String> parents;
		private final Map<Integer, ImmutableAttribute> attributes;	
		private final Map<Integer, ImmutableSlot> slots;	

		public FlatStruct( final MutableObject subject ) {
			name = subject.name;
			parents = new HashSet<String>(subject.parents);
			attributes = new HashMap<Integer, ImmutableAttribute>(subject.attributes);
			slots = new HashMap<Integer, ImmutableSlot>(subject.slots);
		}

		public String getName() {
			return name;
		}

		public Set<String> getParents() {
			return new HashSet<String>(parents);
		}
		
		public ImmutableAttribute[] getAttributes() {
			final ImmutableAttribute[] ret = new ImmutableAttribute[attributes.size()];
			attributes.values().toArray(ret);
			return ret;
		}

		public ImmutableSlot[] getSlots() {
			final ImmutableSlot[] ret = new ImmutableSlot[slots.size()];
			slots.values().toArray(ret);
			return ret;
		}

		public String toString() {
			final StringBuilder sb = new StringBuilder();
			
			sb.append("{").append(name).append(":");
			sb.append(parents).append(";");
			sb.append(attributes.values());
			sb.append("}");
			
			return sb.toString();
		}


	}

}
