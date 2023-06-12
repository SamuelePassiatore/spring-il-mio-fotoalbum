package org.java.fotoalbum.controller;


import java.util.Collections;

import org.java.fotoalbum.auth.Role;
import org.java.fotoalbum.auth.RoleServ;
import org.java.fotoalbum.auth.User;
import org.java.fotoalbum.auth.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserServ userServ;

	@Autowired
	private RoleServ roleServ;

	@GetMapping("/user/create")
	public String create(Model model) {
				
		model.addAttribute("user", new User());
		
		return "user_create";
	}
	
	@PostMapping("/user/store")
	public String store(
			@Valid @ModelAttribute User user,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {

			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());

			model.addAttribute("user", user);
			model.addAttribute("errors", bindingResult);
			
			return "user_create";
			
		}
		
		String password = user.getPassword();
	    String encryptedPassword = new BCryptPasswordEncoder().encode(password);
	    user.setPassword(encryptedPassword);
	    Role adminRole = roleServ.findByName("ADMIN");
	    roleServ.save(adminRole);
	    user.setRoles(Collections.singleton(adminRole));
	    userServ.save(user);
	    
	    model.addAttribute("successMessage", "Utente creato con successo!");
	    model.addAttribute("user", new User()); 

		return "user_create";
	}
	
}
