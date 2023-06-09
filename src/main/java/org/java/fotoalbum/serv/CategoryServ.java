package org.java.fotoalbum.serv;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServ {

	@Autowired
	private CategoryRepo categoryRepo;

	public List<Category> findAll() {

		return categoryRepo.findAll();
	}

	public Category save(Category category) {

		return categoryRepo.save(category);
	}

	public Optional<Category> findById(int id) {

		return categoryRepo.findById(id);
	}

	public void delete(Category category) {

		categoryRepo.delete(category);
	}
}
