package com.mulcam.ai.web.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	
	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		
		try {
			 		
					File dir = new File(FileSystemView.getFileSystemView().getHomeDirectory().toString()+"/shotting-fv/");
					dir.mkdir();
			 		file.transferTo(new File(FileSystemView.getFileSystemView().getHomeDirectory().toString()+"/shotting-fv/"+"\\"+file.getOriginalFilename()));		
		
			return "upload ok!!!";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upload fail!!!";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upload fail!!!";
		}
	}

}