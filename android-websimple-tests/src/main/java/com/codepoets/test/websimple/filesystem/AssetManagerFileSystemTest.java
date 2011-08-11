package com.codepoets.test.websimple.filesystem;

import com.codepoets.websimple.filesystem.assetmanager.AssetManagerFileSystem;

public class AssetManagerFileSystemTest extends FileSystemTest<AssetManagerFileSystem> {
	@Override
	protected void setUp() throws Exception {
		fileSystem = new AssetManagerFileSystem(getContext().getAssets());
	}
}
