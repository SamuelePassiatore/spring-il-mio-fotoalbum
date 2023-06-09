package org.java.fotoalbum.controller;

import java.util.List;

import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoServ photoServ;
	
	@GetMapping("/photo")
	public String index(Model model){
		List<Photo> photoList = photoServ.findAll();
		
		model.addAttribute("photoList", photoList);

		return "photo_index";
	}
}
