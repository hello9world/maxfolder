package my.sandbox.maxfolder.util;

import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes

import my.sandbox.maxfolder.MaxFolderApplication;
import my.sandbox.maxfolder.service.FolderService;
import my.sandbox.maxfolder.util.FolderUtils;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification

@ContextConfiguration(classes = MaxFolderApplication.class, loader = SpringApplicationContextLoader.class)
class FolderUtilsSpec extends Specification {

	@Autowired
	private FolderUtils folderUtils;

	@Autowired
	private FolderService folderService;

	def "create and delete root folder"() {
		when:
			Path rootFolder = Paths.get(folderService.getRootFolder())
			folderUtils.createFolder(rootFolder)
		
		then:
			Files.exists(rootFolder);
		
		when:
			folderUtils.deleteFolder(rootFolder)
		
		then:
			Files.notExists(rootFolder)
	}
	
}
