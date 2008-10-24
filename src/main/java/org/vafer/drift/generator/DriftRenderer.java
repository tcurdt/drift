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

import org.antlr.stringtemplate.AttributeRenderer;

public final class DriftRenderer implements AttributeRenderer {

	public String toString(Object o) {
		return o.toString();
	}

	public String toString(Object o, String formatName) {
		if ("capitalizeFirst".equals(formatName)) {
			final char[] s = o.toString().toCharArray();
			if (s.length > 0) {
				s[0] = Character.toUpperCase(s[0]);
			}
			return new String(s);					
		} else if ("negative".equals(formatName)) {
			return o.toString().replace('-', 'N');
		} else if ("upper".equals(formatName)) {
			return o.toString().toUpperCase();			
		} else if ("lower".equals(formatName)) {
			return o.toString().toLowerCase();			
		} else {
			
			throw new IllegalArgumentException("Unsupported format name: " + formatName);
		}
	}

}
