package org.java.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.Photo;
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

import jakarta.validation.Valid;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoServ photoServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
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

		photoServ.save(photo);

		return "redirect:/photo";
	}
	
	@GetMapping("/photo/delete/{id}")
	public String delete(
			@PathVariable("id") int id
		) {

		Optional<Photo> photoOpt = photoServ.findById(id);
		Photo photo = photoOpt.get();
		
		photoServ.delete(photo);

		return "redirect:/photo";
	}
}
