package my.sandbox.maxfolder.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import my.sandbox.maxfolder.service.FolderService;
import my.sandbox.maxfolder.util.FolderUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FolderService {

	final Logger logger = LoggerFactory.getLogger(FolderService.class);

	@Value("${root.folder}")
	private String rootFolder;

	@Value("${max.subfolder}")
	private long maxNumberOfSubfolder;

	@Value("${max.dummyfile}")
	private String dummyFile;

	@Autowired
	private FolderUtils folderUtils;

	private Random randomGenerator = new Random();
	
	public FolderService() {
		super();
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}

	public long getMaxNumberOfSubfolder() {
		return maxNumberOfSubfolder;
	}

	public void setMaxNumberOfSubfolder(long maxNumberOfSubfolder) {
		this.maxNumberOfSubfolder = maxNumberOfSubfolder;
	}

	public String getDummyFile() {
		return dummyFile;
	}

	public void setDummyFile(String dummyFile) {
		this.dummyFile = dummyFile;
	}

	public void run() {
		try {
			this.init();
			
			long max = this.getMaxNumberOfSubfolder();
			for (int i = 1; i < max; i++) {
				Path folder = Paths.get(this.getRootFolder(), String.valueOf(i));
				this.folderUtils.createFolder(folder);
				
				int randomNumber = randomGenerator.nextInt(i) + 1;
				Path randomFolder = Paths.get(this.getRootFolder(), String.valueOf(randomNumber));
				Path dummyFile1 = Paths.get(dummyFile);
				this.folderUtils.copyFileToFolder(dummyFile1, randomFolder);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				this.cleanup();
			} catch (IOException e) {
				// do nothing
			}
		}
	}

	private void init() throws IOException {
		this.folderUtils.createFolder(Paths.get(this.getRootFolder()));
		logger.debug("root folder {} is created", this.getRootFolder());
	}

	private void cleanup() throws IOException {
		this.folderUtils.deleteFolder(Paths.get(this.getRootFolder()));
		logger.debug("root folder {} is deleted", this.getRootFolder());
	}

}
