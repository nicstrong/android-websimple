package com.codepoets.test.websimple.filesystem;

import com.codepoets.websimple.filesystem.zip.AssetManagerZipFileSource;
import com.codepoets.websimple.filesystem.zip.ZipFileSystem;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.util.ArrayList;

public class ZipFileSystemTest extends FileSystemTest<ZipFileSystem> {
	private AssetManagerZipFileSource zipFileSource;
	private File baseDir;

	@Override
	protected void setUp() throws Exception {
		zipFileSource = new AssetManagerZipFileSource(getContext(), "webroot.zip");
		baseDir = new File(getContext().getCacheDir(), "ziptest");
		baseDir.mkdirs();
		ZipFileSystem zipFileSystem = new ZipFileSystem(zipFileSource, baseDir);
		fileSystem = zipFileSystem;
	}

	@Override
	protected void tearDown() throws Exception {
		File imageDir = new File(baseDir, "images");
		File f = new File(baseDir, "index.html");
		f.delete();
		f = new File(baseDir, "test.txt");
		f.delete();
		f = new File(imageDir, "image.png");
		f.delete();
		imageDir.delete();
		baseDir.delete();
	}
}
