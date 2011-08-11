package com.codepoets.test.websimple.filesystem;

import com.codepoets.websimple.filesystem.file.FileFileSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFileSystemTest extends FileSystemTest<FileFileSystem> {
	private File baseDir;
	private File imageDir;
	private List<File> files;

	@Override
	protected void setUp() throws Exception {
		baseDir = new File(getContext().getCacheDir(), "tests");
		if (!baseDir.exists()) {
			baseDir.mkdirs();
		}
		imageDir = new File(baseDir, "images");
		if (!imageDir.exists()) {
			imageDir.mkdir();
		}
		files = new ArrayList<File>();
		File f = new File(baseDir, "index.html");
		f.createNewFile();
		files.add(f);
		f = new File(baseDir, "test.txt");
		f.createNewFile();
		files.add(f);
		f = new File(imageDir, "image.png");
		f.createNewFile();
		files.add(f);
		fileSystem = new FileFileSystem(baseDir);
	}

	@Override
	protected void tearDown() throws Exception {
		for (File f: files) {
			f.delete();
		}
		imageDir.delete();
		baseDir.delete();
	}
}
