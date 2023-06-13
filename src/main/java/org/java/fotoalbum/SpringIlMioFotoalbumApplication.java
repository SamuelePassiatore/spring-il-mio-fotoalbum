package org.java.fotoalbum;

import org.java.fotoalbum.auth.RoleServ;
import org.java.fotoalbum.auth.UserServ;

import org.java.fotoalbum.auth.Role;
import org.java.fotoalbum.auth.User;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.serv.CategoryServ;
import org.java.fotoalbum.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoServ photoServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private RoleServ roleServ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category("Viaggi");
		Category category2 = new Category("Architettura");
		Category category3 = new Category("Paesaggi");
		Category category4 = new Category("Moda");
		Category category5 = new Category("Ritratti");
		
		categoryServ.save(category1);
		categoryServ.save(category2);
		categoryServ.save(category3);
		categoryServ.save(category4);
		categoryServ.save(category5);
		
		Photo photo1 = new Photo("Montagna", "Bella vista panoramica delle montagne.", "https://media.istockphoto.com/id/478627080/it/foto/vista-serale-di-ama-dablam.jpg?s=612x612&w=0&k=20&c=yUnV1ibUnUFmNMGfTaG_rneVufjLHL0fCuhC9xy8xBc=", true, category1, category2);
		Photo photo2 = new Photo("Spiaggia", "Splendida spiaggia tropicale con palme.", "https://static.vecteezy.com/ti/foto-gratuito/t2/2030159-tramonto-sulla-spiaggia-tropicale-gratuito-foto.jpg", true, category5);
		Photo photo3 = new Photo("Città", "Veduta notturna di una città moderna.", "https://static2-viaggi.corriereobjects.it/wp-content/uploads/2022/02/empire-state-building-visto-dal-rockefeler-centre-iStock-956426314-1080x721.jpeg?v=1643745279", false, category3, category4);
		
		photoServ.save(photo1);
		photoServ.save(photo2);
		photoServ.save(photo3);
		
		Role adminRole = new Role("ADMIN");
		roleServ.save(adminRole);
		
		final String psw = new BCryptPasswordEncoder().encode("password");

		User userAdmin = new User("admin", psw, adminRole);

		userServ.save(userAdmin);
		
		
		Role superAdmin = new Role("SUPERADMIN");
        roleServ.save(superAdmin);	
        final String password = new BCryptPasswordEncoder().encode("superadmin");

        User superadmin = new User("superadmin", password, superAdmin);
        userServ.save(superadmin);
		
	}

}
