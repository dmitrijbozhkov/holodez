package org.nure.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.nure.models.ui.StringFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	private final static Path imageDir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "images");
	
	public String saveFile(StringFile file) {
		String imagePath = imageDir.resolve("pepe.txt").toString();
		File image = new File(imagePath);
		
		return "name";
	}
}
