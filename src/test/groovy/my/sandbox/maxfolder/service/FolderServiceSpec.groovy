package my.sandbox.maxfolder.service;

import my.sandbox.maxfolder.MaxFolderApplication;
import my.sandbox.maxfolder.service.FolderService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spock.lang.Specification

@ContextConfiguration(classes = MaxFolderApplication.class, loader = SpringApplicationContextLoader.class)
class FolderServiceSpec extends Specification {

	@Autowired
	private FolderService folderService;

	def "max number of subfolders is 100"() {
		expect:
		folderService.getMaxNumberOfSubfolder() == 100
	}

	def "root folder is defined"() {
		expect:
		folderService.getRootFolder() == "C://tmp//maxfolder"
	}

	def "dummy file exists"() {
		expect:
		File dummyFile = new File(folderService.getDummyFile())
		dummyFile.exists() == true
		dummyFile.isFile() == true
	}

	def "root folder is cleaned after run"() {
		expect:
		folderService.run();
		(new File(folderService.getRootFolder())).exists() == false
	}
}
