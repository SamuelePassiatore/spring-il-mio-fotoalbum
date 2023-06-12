package org.java.fotoalbum.pojo;

import java.util.Arrays;
import java.util.List;

import org.java.fotoalbum.auth.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il campo titolo non può essere nullo!")
	private String title;
	@NotBlank(message = "Il campo descrizione non può essere nullo!")
	private String description;
	@NotBlank(message = "Il campo url non può essere nullo!")
	private String url;
	@NotNull(message = "Il campo disponibilità non può essere nullo!")
	private boolean visible;
	
	@ManyToMany
	@JsonManagedReference
	private List<Category> categories;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public Photo() { }
	public Photo(String title, String description, String url, boolean visible) {
    	setTitle(title);
    	setDescription(description);
    	setUrl(url);
    	setVisible(visible);
	}
	
	public Photo(String title, String description, String url, Boolean visible, Category... categories) {
		setTitle(title);
    	setDescription(description);
    	setUrl(url);
    	setVisible(visible);
    	
		setCategories(categories);
	}
	 
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void setCategories(Category[] categories) {
		setCategories(Arrays.asList(categories));
	}
	public void removeCategory(Category category) {
		getCategories().remove(category);		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getVisibleText() {
	    return visible ? "Si" : "No";
	}
	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}
	 
	@Override
	public String toString() {
		
		return "[" + getId() + "] "
				+ "\nTitolo: " + getTitle() 
				+ "\nDescrizione: " + getDescription()
				+ "\nUrl: " + getUrl()
				+ "\nVisibile: " + isVisible();
	}

}
