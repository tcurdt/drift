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
package org.vafer.drift.ant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.vafer.drift.generator.FileCodeWriter;
import org.vafer.drift.generator.JavaGenerator;
import org.vafer.drift.model.Schema;

public final class DriftAntTask extends Task {

	private File schemaFile;
	private File outputDir;
	private String packageName;
	private String baseClass;
	
	public File getSchemaFile() {
		return schemaFile;
	}

	public void setSchemaFile(File schemaFile) {
		this.schemaFile = schemaFile;
	}

	
	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getBaseClass() {
		return baseClass;
	}

	public void setBaseClass(String baseClass) {
		this.baseClass = baseClass;
	}

	public void execute() {

		if (packageName == null) {
			throw new BuildException("Attribtue 'packageName' needs to be set.");			
		}
		
		if (outputDir == null || !outputDir.isDirectory()) {
			throw new BuildException("Attribtue 'outputDir' (" + outputDir + ") needs to point to a proper destination directory.");
		}

		if (schemaFile == null || !schemaFile.isFile()) {
			throw new BuildException("Attribtue 'schemaFile' (" + schemaFile + ") needs to point to a schema file.");
		}

		try {
			final Schema schema = Schema.build(new FileInputStream(schemaFile));
				
			final JavaGenerator javaGenerator = new JavaGenerator(packageName, baseClass);
			
			final FileCodeWriter writer = new FileCodeWriter(outputDir);
			
			javaGenerator.generate(schema, writer);
		
		} catch(IOException e) {
			e.printStackTrace();			
		}
	
	}

}
