package com.codepoets.websimple.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileSystem {
	FileSystemFile root() throws IOException;
	boolean exists(String path) throws IOException;
	FileSystemFile getFile(String path) throws IOException;

	InputStream open(String path) throws IOException;
}
