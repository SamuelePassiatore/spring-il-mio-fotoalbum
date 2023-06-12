package org.java.fotoalbum.serv;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServ {
	
	@Autowired
	private PhotoRepo photoRepo;
	
	public List<Photo> findAll() {
		
		return photoRepo.findAll();
	}
	
	public Photo save(Photo photo) {
		
		return photoRepo.save(photo);
	}
	
	public Optional<Photo> findById(int id) {
		
		return photoRepo.findById(id);
	}
	
	public List<Photo> findByTitle(String title) {
		
		return photoRepo.findByTitleContaining(title);
	}
	
	public List<Photo> findByVisibleTrue(){
		return photoRepo.findByVisibleTrue();
	}
	
	public List<Photo> findByUserId(int userId) {
	    return photoRepo.findByUserId(userId);
	}
	
	public List<Photo> findByTitleAndUserId(String title, int userId) {
		return photoRepo.findByTitleAndUserId(title, userId);
	}
	
	public void delete(Photo photo) {
		
		photoRepo.delete(photo);
	}
}
