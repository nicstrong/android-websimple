package com.codepoets.websimple.filesystem;

import java.io.IOException;
import java.io.InputStream;

public class ProxyFileSystem implements FileSystem {
	FileSystem proxiedFileSystem;

	public ProxyFileSystem(FileSystem proxiedFileSystem) {
		this.proxiedFileSystem = proxiedFileSystem;
	}

	@Override
	public FileSystemFile root() throws IOException {
		return proxiedFileSystem.root();
	}

	@Override
	public boolean exists(String path) throws IOException {
		return proxiedFileSystem.exists(path);
	}

	@Override
	public FileSystemFile getFile(String path) throws IOException {
		return proxiedFileSystem.getFile(path);
	}

	@Override
	public InputStream open(String path) throws IOException {
		return open(path);
	}
}
