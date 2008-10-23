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
import java.net.URL;

import junit.framework.TestCase;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public final class GenerateAntTestCase extends TestCase {

	private Project project;

	protected void setUp() throws Exception {
		project = new Project();
		project.setCoreLoader(getClass().getClassLoader());
		project.init();

		final URL url = getClass().getResource("build.xml");
		
		assertNotNull(url);
		
		File buildFile = new File(url.getFile());
		project.setBaseDir(buildFile.getParentFile());
		
		final ProjectHelper helper = ProjectHelper.getProjectHelper();
		helper.parse(project, buildFile);

	}

	public void testGeneration() {
		project.executeTarget("generate");

		// FIXME proper class path
		assertTrue("source code not generated", new File("target/test-classes/").exists());
	}

}
