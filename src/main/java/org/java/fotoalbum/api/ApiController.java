package org.java.fotoalbum.api;

import java.util.List;

import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ApiController {

	@Autowired
	private PhotoServ photoServ;
	
	@GetMapping("/photos")
    public ResponseEntity<List<Photo>> getPhoto(@RequestParam(required = false) String title) {
        if (title != null) {
        	List<Photo> photos = photoServ.findByTitle(title);
        	return new ResponseEntity<>(photos, HttpStatus.OK);
        }
        
        List<Photo> photos = photoServ.findAll();
		return new ResponseEntity<>(photos, HttpStatus.OK);
    }
}
