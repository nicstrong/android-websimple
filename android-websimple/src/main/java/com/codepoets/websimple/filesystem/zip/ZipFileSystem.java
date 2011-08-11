package com.codepoets.websimple.filesystem.zip;

import com.codepoets.websimple.filesystem.FileSystem;
import com.codepoets.websimple.filesystem.ProxyFileSystem;
import com.codepoets.websimple.filesystem.file.FileFileSystem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileSystem extends ProxyFileSystem {
	private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ZipFileSystem.class);

	public ZipFileSystem(ZipFileSource source, File mountDir) throws IOException {
		super(createFileSystem(mountDir));
		expandZip(source.getZipFile(), mountDir);
	}

	private static FileSystem createFileSystem(File mountDir) throws IOException {
		if (!mountDir.exists()) {
			mountDir.mkdirs();
		}
		return new FileFileSystem(mountDir);
	}

	private void expandZip(ZipFile zipFile, File destDir) throws IOException {
		logger.debug("Expanding zip file {}", zipFile.getName());
		Enumeration<? extends ZipEntry> iter = zipFile.entries();
		while (iter.hasMoreElements()) {
			ZipEntry entry = iter.nextElement();
			logger.debug("Expanding zip entry {}", entry.getName());
			if (entry.isDirectory()) {
				File dir = new File(destDir, entry.getName());
				dir.mkdir();
			} else {
				String path = FilenameUtils.getPath(entry.getName());

				File file = new File(destDir, entry.getName());
				InputStream in = null;
				try {
					in = zipFile.getInputStream(entry);
					FileUtils.copyInputStreamToFile(in, file);
				} catch (IOException e) {
					logger.error("IO:", e);
				} finally {
					IOUtils.closeQuietly(in);
				}
			}
		}
	}
}
