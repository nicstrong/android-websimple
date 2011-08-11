package com.codepoets.websimple.filesystem.file;

import com.codepoets.websimple.filesystem.FileSystemFile;
import com.codepoets.websimple.filesystem.FileSystemUtils;
import sun.misc.FormattedFloatingDecimal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IllegalFormatFlagsException;
import java.util.List;

public class FileFile implements FileSystemFile {
	private File baseDir;
	private String path;

	public FileFile(File baseDir, String path) {
		this.baseDir = baseDir;
		this.path = path;
	}

	@Override
	public boolean isDirectory() {
		return getInternalPath().isDirectory();
	}

	@Override
	public String getPath() {
		if (path == null) {
			return FileSystemUtils.PATH_SEPARATOR;
		}
		return FileSystemUtils.PATH_SEPARATOR + path;
	}

	public File getInternalPath() {
		if (path == null) {
			return baseDir;
		}
		return new File(baseDir, path);
	}

	@Override
	public Collection<? extends FileSystemFile> getEntries() {
		List<FileFile> list = new ArrayList<FileFile>();
		File dir = getInternalPath();
		for (String file: dir.list()) {
			list.add(new FileFile(baseDir, path == null ? file : FileSystemUtils.join(path, file)));
		}
		return list;
	}

	@Override
	public long length() throws IOException {
		return getInternalPath().length();
	}
}
