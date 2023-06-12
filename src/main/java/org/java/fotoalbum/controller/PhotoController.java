package org.java.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.auth.User;
import org.java.fotoalbum.serv.PhotoServ;
import org.java.fotoalbum.serv.CategoryServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.validation.Valid;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoServ photoServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/photo")
	public String index(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		int userId = user.getId();
		
		List<Photo> photoList;
		
		if (userId == 1) { 
	        photoList = photoServ.findAll();
	    } else {
	        photoList = photoServ.findByUserId(userId);
	    }
		
		model.addAttribute("photoList", photoList);

		return "photo_index";
	}
	
	@PostMapping("/photo/filter")
	public String filterPhoto(Model model, @RequestParam(required = false) String title) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		int userId = user.getId();
		List<Photo> photoList;
		
		if (userId == 1) {
	        photoList = photoServ.findByTitle(title);
	    } else {
	        photoList = photoServ.findByTitleAndUserId(title, userId);
	    }
		
		model.addAttribute("photoList", photoList);
		model.addAttribute("title", title);

		return "photo_index";
	}
	
	@GetMapping("/photo/{id}")
	public String show(@PathVariable("id") Integer id, Model model){
		
		Optional<Photo> optPhoto = photoServ.findById(id);
		Photo photo = optPhoto.get();
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    int userId = user.getId();
		
	    if (userId != 1 && photo.getUser().getId() != userId) {
	        return "redirect:/photo";
	    }
	    
		List<Category> categories = photo.getCategories();
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo_show";
	}
	
	@GetMapping("/photo/create")
	public String create(Model model) {
		List<Category> categories = categoryServ.findAll();
				
		model.addAttribute("photo", new Photo());
		model.addAttribute("categories", categories);
		
		return "photo_create";
	}

	@PostMapping("/photo/store")
	public String store(
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {

			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());

			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			
			return "photo_create";
			
		}
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    photo.setUser(user);
	    photoServ.save(photo);

		return "redirect:/photo";
	}
	
	@GetMapping("/photo/edit/{id}")
	public String edit(
			@PathVariable("id") int id,
			Model model
		) {
		List<Category> categories = categoryServ.findAll();
		
		Optional<Photo> photoOpt = photoServ.findById(id);
		Photo photo = photoOpt.get();
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);

		return "photo_edit";
	}
	
	@PostMapping("/photo/update/{id}")
	public String update(
			@PathVariable("id") int id,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult,
			Model model){
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			
			return "photo_edit";
		}
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    photo.setUser(user);
	    photoServ.save(photo);

		return "redirect:/photo";
	}
	
	@GetMapping("/photo/delete/{id}")
	public String delete(
			@PathVariable("id") int id
		) {

		Optional<Photo> photoOpt = photoServ.findById(id);
		Photo photo = photoOpt.get();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    photo.setUser(user);
		photoServ.delete(photo);

		return "redirect:/photo";
	}
}
