package my.sandbox.maxfolder.service;

import java.io.File;

import my.sandbox.maxfolder.MaxFolderApplication;
import my.sandbox.maxfolder.service.FolderService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MaxFolderApplication.class)
public class FolderServiceTest {

	@Autowired
	private FolderService folderService;

	@Test
	public void testRun() {
		
		Assert.assertNotNull(folderService);
		
		Assert.assertNotNull(folderService.getRootFolder());
		Assert.assertTrue(folderService.getMaxNumberOfSubfolder() > 0);

		folderService.run();

		File rootFolder = new File(folderService.getRootFolder());
		Assert.assertFalse("root folder is removed after run",
				rootFolder.exists());
	}

}
