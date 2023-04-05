package com.aspire.user.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public static void saveFile(String uploadUrl,String filename,MultipartFile multipartFile) throws IOException {
		Path uploadpath=Paths.get(uploadUrl);
		
		if(!Files.exists(uploadpath)) {
			Files.createDirectories(uploadpath);
		}
		
		try(InputStream inputstrem=multipartFile.getInputStream()){
			Path filePath=uploadpath.resolve(filename);
			Files.copy(inputstrem, filePath,StandardCopyOption.REPLACE_EXISTING);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new IOException("Could not save file"+filename,e);
		}
	}

}
