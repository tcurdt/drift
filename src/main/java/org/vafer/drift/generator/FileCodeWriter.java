package org.vafer.drift.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class FileCodeWriter implements CodeWriter {

	private final File outputDir;
	
	public FileCodeWriter( final File pOutputDir ) {
		outputDir = pOutputDir;
	}
	
	public void write(String sourceName, String sourceContent) throws IOException {
        final File output = new File(outputDir, sourceName);
        final FileWriter writer = new FileWriter(output);
        writer.append(sourceContent);
        writer.close();
	}

}
