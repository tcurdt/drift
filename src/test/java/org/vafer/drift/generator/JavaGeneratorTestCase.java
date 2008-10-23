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

import junit.framework.TestCase;

import org.apache.commons.jci.compilers.CompilationResult;
import org.apache.commons.jci.compilers.JavaCompiler;
import org.apache.commons.jci.compilers.JavaCompilerFactory;
import org.apache.commons.jci.problems.CompilationProblem;
import org.apache.commons.jci.readers.ResourceReader;
import org.apache.commons.jci.stores.MemoryResourceStore;
import org.apache.commons.jci.stores.ResourceStore;
import org.apache.tools.ant.filters.StringInputStream;
import org.vafer.drift.model.Schema;

public final class JavaGeneratorTestCase extends TestCase {

	public void testGenerate() throws IOException {
		
		final InputStream input = new StringInputStream("object Simple { string simple; }");

		final Schema schema = Schema.build(input);

		assertEquals(1, schema.getObjects().length); 
		
		final JavaGenerator javaGenerator = new JavaGenerator();
		
		final CodeWriter writer = new CodeWriter() {
			public void write(String sourceName, String sourceContent) throws IOException {
			}			
		};
		
		javaGenerator.generate(schema, writer);
	}
	
	public void testCompiles() throws IOException {

		final InputStream input = new StringInputStream("object Simple { string simple; }");

		final Schema schema = Schema.build(input);

		assertEquals(1, schema.getObjects().length); 
		
		final JavaGenerator javaGenerator = new JavaGenerator();

		final MemoryCodeWriter writer = new MemoryCodeWriter();
		
		javaGenerator.generate(schema, writer);

		final String[] resourceNames = writer.getNames(); 
		
		assertEquals(1, resourceNames.length);
		
        final JavaCompiler compiler = new JavaCompilerFactory().createCompiler("janino");

        assertNotNull(compiler);
        
        final ResourceStore store = new MemoryResourceStore();
        final ResourceReader reader = writer;
        
        final CompilationResult result = compiler.compile(resourceNames, reader, store);			

        final CompilationProblem[] errors = result.getErrors();
        for (int i = 0; i < errors.length; i++) {
			System.out.println(errors[i]);
		}
        
        assertEquals(0, errors.length);

        final CompilationProblem[] warnings = result.getWarnings();
        for (int i = 0; i < warnings.length; i++) {
			System.out.println(warnings[i]);
		}

        assertEquals(0, warnings.length);
		
	}

}
