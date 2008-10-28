package org.vafer.drift.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jci.readers.ResourceReader;

public final class MemoryCodeWriter implements CodeWriter, ResourceReader {

	private final Map<String, String> sources = new HashMap<String, String>();
	
	public void write(String sourceName, String sourceContent) throws IOException {		
		sources.put(sourceName, sourceContent);
	}
	
	public String[] getNames() {
		final String[] names = new String[sources.size()];
		sources.keySet().toArray(names);
		return names;
	}

	public byte[] getBytes(String name) {

		final String content = sources.get(name);

		if (content == null) {
			return null;
		}

		return content.getBytes();
	}

	public boolean isAvailable(String name) {
		return sources.containsKey(name);
	}

}
