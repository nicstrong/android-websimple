package com.codepoets.websimple.filesystem.file;

import com.codepoets.websimple.filesystem.FileSystem;
import com.codepoets.websimple.filesystem.FileSystemFile;

import java.io.*;

public class FileFileSystem implements FileSystem {
	private File rootDir;

	public FileFileSystem(File rootDir) throws IOException {
		if (!rootDir.isDirectory()) {
			throw new IOException("Root dir must be a directory");
		}
		this.rootDir = rootDir;
	}

	@Override
	public FileSystemFile root() throws IOException {
		if (!rootDir.exists()) {
			throw new IOException("Root dir " + rootDir + " not found");
		}
		return new FileFile(rootDir, null);
	}

	@Override
	public boolean exists(String path) {
		File file = new File(rootDir, path);
		return file.exists();
	}

	@Override
	public FileSystemFile getFile(String path) {
		File file = new File(rootDir, path);
		if (file.exists()) {
			return new FileFile(rootDir, path);
		}
		return null;
	}

	@Override
	public InputStream open(String path) throws IOException {
		File file = new File(rootDir, path);
		if (!file.exists()) {
			throw new FileNotFoundException(file.getPath() + " not found");
		}
		return new FileInputStream(file);
	}
}
