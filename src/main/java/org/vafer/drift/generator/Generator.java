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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;
import org.vafer.drift.model.ImmutableObject;
import org.vafer.drift.model.Schema;


public final class Generator {

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
	
	public void generate( Schema pSchema, String pLanguage, File pOutputDir ) throws IOException {

		final InputStream is = getClass().getResourceAsStream("/templates/" + pLanguage + ".stg");
		final InputStreamReader reader = new InputStreamReader(is);
		final StringTemplateGroup group = new StringTemplateGroup(reader, AngleBracketTemplateLexer.class);

		final ImmutableObject[] objects = pSchema.getObjects();
		
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
			t.registerRenderer(String.class, new CaseRenderer());
	        t.setAttribute("package", "com.joost.tracking");
	        t.setAttribute("name", object.getName());
	        t.setAttribute("parents", object.getParents());
	        t.setAttribute("attributes", object.getAttributes());
	        t.setAttribute("slots", object.getSlots());
	        
	        final File output = new File(pOutputDir, object.getName() + "." + pLanguage);
	        final FileWriter writer = new FileWriter(output);
	        writer.append(t.toString());
	        writer.close();
		}
				
	}
}