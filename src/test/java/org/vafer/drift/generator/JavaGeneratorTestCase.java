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
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
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

	private File dir;
	private static int testCount;
	
	protected void setUp() throws Exception {
		dir = new File("target/tests/" + testCount);
		testCount++;
	}

	private static final class MutableInt {
		int value;
	}
	
	public void testGenerate() throws IOException {
		
		final InputStream input = new StringInputStream("object Simple { string simple; }");

		final Schema schema = Schema.build(input);

		assertEquals(1, schema.getObjects().length); 
		
		final JavaGenerator javaGenerator = new JavaGenerator("org.vafer.drift.generator.generated");
		
		final MutableInt count = new MutableInt();
		
		final CodeWriter writer = new CodeWriter() {
			public void write(String sourceName, String sourceContent) throws IOException {
				count.value++;
			}			
		};
		
		javaGenerator.generate(schema, writer);
		
		assertEquals(2, count.value);
	}
	
	private CompilationResult compile( final InputStream input ) throws IOException {
		final Schema schema = Schema.build(input);

		final JavaGenerator javaGenerator = new JavaGenerator("org.vafer.drift.generator.generated");
		final MemoryCodeWriter writer = new MemoryCodeWriter();		
		javaGenerator.generate(schema, writer);
		
        final ResourceStore store = new MemoryResourceStore();
        final ResourceReader reader = writer;
        final JavaCompiler compiler = new JavaCompilerFactory().createCompiler("eclipse");

        assertNotNull(compiler);
                
        final CompilationResult result = compiler.compile(writer.getNames(), reader, store);			

        final CompilationProblem[] errors = result.getErrors();
        
        final Set<String> fileNames = new HashSet<String>();
        
        for (int i = 0; i < errors.length; i++) {
			System.out.println(errors[i]);			
			fileNames.add(errors[i].getFileName());
		}
        
        final CompilationProblem[] warnings = result.getWarnings();
        for (int i = 0; i < warnings.length; i++) {
			System.out.println(warnings[i]);
			fileNames.add(warnings[i].getFileName());
		}

        if (fileNames.size() > 0) {
	        System.out.println("Writing out files with problems");
	        
	        for (Iterator<String> it = fileNames.iterator(); it.hasNext();) {
				final String name = it.next();
				final String content = new String(reader.getBytes(name));
				
				final File fullPath = new File(dir, name);
				
				fullPath.getParentFile().mkdirs();
				
				System.out.println(" writing " + fullPath.getAbsolutePath());
				
				FileUtils.writeStringToFile(fullPath, content);
			}	        
        }

        return result;
	}
	
	public void testStringCompile() throws IOException {
		final CompilationResult result = compile(
				new StringInputStream("object Simple { string simple; }"));
		assertEquals(0, result.getErrors().length);
	}

	private final String[] grammars = {
			"Simple.dg",
			"Migration.dg",
			"Complex.dg"
	};
	
	public void testResourceCompile() throws IOException {
		for (int i = 0; i < grammars.length; i++) {
			final InputStream input = getClass().getResourceAsStream("/grammars/" + grammars[i]);

			assertNotNull(input);
			
			final CompilationResult result = compile(input);
			
			input.close();

			assertEquals(0, result.getErrors().length);
		}

	}

}
