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
package org.vafer.drift.generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;
import org.vafer.drift.DriftObjectBase;
import org.vafer.drift.model.ImmutableObject;
import org.vafer.drift.model.Schema;


public final class JavaGenerator {

	private final String packageName;
	private final String baseClass;

	public JavaGenerator( final String pPackageName) {
		this(pPackageName, DriftObjectBase.class.getName());
	}
	
	public JavaGenerator( final String pPackageName, final String pBaseClass ) {
		packageName = pPackageName;
		baseClass = pBaseClass;
	}
	
	/*
	private void printSlot( ImmutableSlot slot, int level ) {
		
		for (int i = 0; i < level; i++) {
			System.out.print("  ");
		}
		
		System.out.println("slot " + slot + "(" + slot.getType() + ")");

		ImmutableSlot[] parents = slot.getParents();
		for (int p = 0; p < parents.length; p++) {
			printSlot(parents[p], level+1);
		}
		
	}*/
	
	public void generate( Schema pSchema, CodeWriter pWriter ) throws IOException {

		final InputStream is = getClass().getResourceAsStream("/templates/java.stg");
		final InputStreamReader reader = new InputStreamReader(is);
		final StringTemplateGroup group = new StringTemplateGroup(reader, AngleBracketTemplateLexer.class);
		group.registerRenderer(String.class, new DriftRenderer());
		group.registerRenderer(Integer.class, new DriftRenderer());

		final ImmutableObject[] objects = pSchema.getObjects();
		
		final String resourcePrefix = packageName.replace('.', '/') + '/';
		
		for (int i = 0; i < objects.length; i++) {
			final ImmutableObject object = objects[i];

			/*
			final ImmutableAttribute[] attributes = object.getAttributes();

			for (int j = 0; j < attributes.length; j++) {
				final ImmutableAttribute attribute = attributes[j];
				
				System.out.print("attribute ");
				
				printSlot(attribute, 0);				
			}
			
			System.out.println("--");

			final ImmutableSlot[] slots = object.getSlots();
			for (int j = 0; j < slots.length; j++) {
				System.out.println("case " + slots[j]);
			}

			System.out.println("--");
			*/
			
			final StringTemplate t = group.getInstanceOf("object");
	        t.setAttribute("package", packageName);
	        t.setAttribute("baseClass", baseClass);
	        t.setAttribute("name", object.getName());
	        t.setAttribute("parents", object.getParents());
	        t.setAttribute("attributes", object.getAttributes());
	        t.setAttribute("slots", object.getSlots());
	        t.setAttribute("migration", "java.lang.Object");
	        
	        pWriter.write(resourcePrefix + object.getName() + ".java", t.toString());
	        
		}

		final StringTemplate t = group.getInstanceOf("factory");
		t.registerRenderer(String.class, new DriftRenderer());
        t.setAttribute("package", packageName);
        t.setAttribute("objects", objects);
        pWriter.write(resourcePrefix + "ObjectFactory.java", t.toString());
				
	}
}
