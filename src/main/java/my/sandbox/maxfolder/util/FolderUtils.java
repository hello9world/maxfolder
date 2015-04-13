package my.sandbox.maxfolder.util;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class FolderUtils {

	public void createFolder(Path folder) throws IOException {
		FileUtils.forceMkdir(folder.toFile());
	}

	public void deleteFolder(Path folder) throws IOException {
		FileUtils.deleteDirectory(folder.toFile());
	}

	public void copyFileToFolder(Path srcFile, Path destDir) throws IOException {
		FileUtils.copyFileToDirectory(srcFile.toFile(), destDir.toFile());
	}

}
