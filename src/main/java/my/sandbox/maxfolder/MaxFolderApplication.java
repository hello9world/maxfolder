package my.sandbox.maxfolder;

import my.sandbox.maxfolder.MaxFolderApplication;
import my.sandbox.maxfolder.service.FolderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaxFolderApplication implements CommandLineRunner  {

	@Autowired
	private FolderService folderService;
	
	@Override
	public void run(String... args) {
		this.folderService.run();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MaxFolderApplication.class, args);
		//TODO exit in case of exception
	}

}
