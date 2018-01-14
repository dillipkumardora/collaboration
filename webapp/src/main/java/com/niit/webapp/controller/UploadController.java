package com.niit.webapp.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.Response;


@RestController
@RequestMapping("/upload")
@PropertySource("classpath:config.properties")
public class UploadController {

	@Autowired
	UserDAO userDAO;
	
	@Value("${imageBasePath}")
	private String imageBasePath;
	
	@PostMapping("/profile-picture")
	public ResponseEntity<Response> uploadProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		System.out.println("Uploading image now!");
		String message = null;
		
		String fileName = "User_Profile" + id + ".png";
		
		if(uploadFile(imageBasePath, fileName, file)) {
			
			userDAO.updateUserProfile(fileName, id);
			
			return new ResponseEntity<Response>(new Response(1,fileName,null), HttpStatus.OK);
			
		} else {
			message = "Failed to update the profile picture!";
			return new ResponseEntity<Response>(new Response(0,message,null),HttpStatus.NOT_FOUND);
		}	
		
	}

	private boolean uploadFile(String directory, String fileName, MultipartFile file) {
		// TODO Auto-generated method stub
		
		if(!new File(directory).exists()) {
			new File(directory).mkdirs();
		}
		
		try {
			file.transferTo(new File(directory + fileName));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}
	
	//To resolve ${} in @Value
	 @Bean
	    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }   	
}
