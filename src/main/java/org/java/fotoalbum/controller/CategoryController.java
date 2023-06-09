package org.java.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.serv.CategoryServ;
import org.java.fotoalbum.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryServ categoryServ;

	@Autowired
	private PhotoServ photoServ;
	
	@GetMapping("/categories")
	public String index(Model model) {

		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);

		return "category_index";
	}
	
	@GetMapping("/categories/create")
	public String create(Model model) {
		model.addAttribute("category", new Category());

		return "category_create";
	}

	@PostMapping("/categories/store")
	public String store(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);

			return "category_create";
		}

		categoryServ.save(category);

		return "redirect:/categories";
	}
	
	@GetMapping("/categories/delete/{id}")
	public String delete(@PathVariable("id") int id) {

		Optional<Category> categoryOpt = categoryServ.findById(id);
		Category category = categoryOpt.get();
		
		for (Photo photo : photoServ.findAll()) {
			photo.removeCategory(category);
			photoServ.save(photo);
		}
		
		categoryServ.delete(category);

		return "redirect:/categories";
	}
}
