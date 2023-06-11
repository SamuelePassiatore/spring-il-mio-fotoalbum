package org.java.fotoalbum.serv;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Contact;
import org.java.fotoalbum.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServ {
	
	@Autowired
	private ContactRepo contactRepo;
	
	public List<Contact> findAll() {
		
		return contactRepo.findAll();
	}
	
	public Contact save(Contact contact) {
		
		return contactRepo.save(contact);
	}
	
	public Optional<Contact> findById(int id) {
		
		return contactRepo.findById(id);
	}
	
	public void delete(Contact contact) {
		
		contactRepo.delete(contact);
	}
}
