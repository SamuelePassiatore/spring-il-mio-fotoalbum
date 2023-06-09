package org.java.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/photo/filter")
	public String filterPhoto(Model model, @RequestParam(required = false) String title) {

		List<Photo> photoList = photoServ.findByTitle(title);
		model.addAttribute("photoList", photoList);
		model.addAttribute("title", title);

		return "photo_index";
	}
	
	@GetMapping("/photo/{id}")
	public String show(@PathVariable("id") Integer id, Model model){
		
		Optional<Photo> optPhoto = photoServ.findById(id);
		Photo photo = optPhoto.get();
		
		model.addAttribute("photo", photo);
		
		return "photo_show";
	}
}
