package org.java.fotoalbum.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il campo email non può essere nullo!")
	@Email 
	private String email;
	
	@NotBlank(message = "Il campo messaggio non può essere nullo!")
	private String message;
	
	public Contact() { }
	public Contact(String email, String message) {
    	setEmail(email);
    	setMessage(message);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {

		return "[" + getId() + "] "
				+ "\nEmail: " + getEmail() 
				+ "\nMessaggio: " + getMessage();
	}
}
