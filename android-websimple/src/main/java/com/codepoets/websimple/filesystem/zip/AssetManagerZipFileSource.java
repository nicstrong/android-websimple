package com.codepoets.websimple.filesystem.zip;

import android.content.Context;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.zip.ZipFile;

public class AssetManagerZipFileSource implements ZipFileSource {
	private Context context;
	private String path;
	private File zipFilePath;

	public AssetManagerZipFileSource(Context context, String path) {
		this.context = context;
		this.path = path;
		zipFilePath = new File(context.getFilesDir(), path);
	}

	@Override
	public ZipFile getZipFile() throws IOException {
		InputStream in = null;
		try {
			in = context.getAssets().open(path);
			FileUtils.copyInputStreamToFile(in, zipFilePath);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return new ZipFile(zipFilePath);
	}

	@Override
	public void close() {
		if (zipFilePath.exists()) {
			zipFilePath.delete();
		}
	}
}
