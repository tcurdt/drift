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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.vafer.drift.model.MutableObject.FlatStruct;
import org.vafer.drift.parser.DriftLexer;
import org.vafer.drift.parser.DriftParser;

public final class Schema {
	
	private final Map<String, ImmutableObject> flatStructs = new HashMap<String, ImmutableObject>();

	public static Schema build( final InputStream inputStream ) throws IOException {

		final DriftLexer lexer = new DriftLexer(new ANTLRInputStream(inputStream));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);        
        final DriftParser parser = new DriftParser(tokens);

        final Schema schema = new Schema();
        final Map<String, ImmutableObject> flatStructs = schema.flatStructs;

        try {
        	
        	parser.file();

        	final Map<String, MutableObject> structs = parser.objects;

        	for (MutableObject struct : structs.values()) {
        		final FlatStruct flatStruct = struct.flatten(structs);
        		flatStructs.put(flatStruct.getName(), flatStruct);
			}
        	
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
		
		
		return schema;
	}
	
	private Schema() {
		
	}
	
	public ImmutableObject[] getObjects() {
		final ImmutableObject[] objects = new ImmutableObject[flatStructs.size()];
		flatStructs.values().toArray(objects);
		return objects;
	}
	
	public ImmutableObject getObject( final String name ) {
		return flatStructs.get(name);
	}

}
