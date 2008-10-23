package org.vafer.drift.generator;

import java.io.IOException;

public interface CodeWriter {

	void write( final String sourceName, final String sourceContent ) throws IOException;
}
