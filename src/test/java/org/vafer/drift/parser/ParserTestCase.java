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
package org.vafer.drift.parser;

import java.io.InputStream;

import junit.framework.TestCase;

import org.vafer.drift.model.ImmutableObject;
import org.vafer.drift.model.Schema;

public final class ParserTestCase extends TestCase {

	private final String[] grammars = {
			"Simple.dg",
			"Migration.dg",
			"Complex.dg"
	};
	
	public void testParsing() throws Exception {
		
		for (int i = 0; i < grammars.length; i++) {
			
			final InputStream input = getClass().getResourceAsStream("/grammars/" + grammars[i]); 
			
			assertNotNull(input);

			final Schema schema = Schema.build(input);
			
			final ImmutableObject[] objects = schema.getObjects();
			
			assertNotNull(objects);
			assertTrue( objects.length > 0 );
			
			input.close();
		}
	}
}
