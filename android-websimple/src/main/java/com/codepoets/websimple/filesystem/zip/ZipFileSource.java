package com.codepoets.websimple.filesystem.zip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipFile;

public interface ZipFileSource {
	ZipFile getZipFile() throws IOException;

	void close();
}
