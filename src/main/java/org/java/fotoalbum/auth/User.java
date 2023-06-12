package org.java.fotoalbum.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.java.fotoalbum.pojo.Photo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Il campo username non può essere nullo!")
	private String username;

	@NotBlank(message = "Il campo password non può essere nullo!")
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Photo> photos;
		
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public User() { }
	public User(String username, String password, Role... roles) {

		setUsername(username);
		setPassword(password);
		setRole(roles);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> auths = new HashSet<>();

		for (Role r : getRoles()) 
			auths.add(new SimpleGrantedAuthority(r.getName()));

		return auths;
	}
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}
	@Override
	public boolean isEnabled() {

		return true;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setRole(Role[] roles) {

		setRoles(new HashSet<>(Arrays.asList(roles)));
	}	
	
	public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

	@Override
	public String toString() {

		return "[" + getId() + "] " + getUsername();
	}
}