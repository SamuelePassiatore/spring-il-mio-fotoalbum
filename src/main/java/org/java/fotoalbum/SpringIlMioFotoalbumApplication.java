package org.java.fotoalbum;

import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoServ photoServ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Photo photo1 = new Photo("Montagna", "Bella vista panoramica delle montagne", "https://media.istockphoto.com/id/478627080/it/foto/vista-serale-di-ama-dablam.jpg?s=612x612&w=0&k=20&c=yUnV1ibUnUFmNMGfTaG_rneVufjLHL0fCuhC9xy8xBc=", true);
		Photo photo2 = new Photo("Spiaggia", "Splendida spiaggia tropicale con palme", "https://static.vecteezy.com/ti/foto-gratuito/t2/2030159-tramonto-sulla-spiaggia-tropicale-gratuito-foto.jpg", true);
		Photo photo3 = new Photo("Città", "Veduta notturna di una città moderna", "https://static2-viaggi.corriereobjects.it/wp-content/uploads/2022/02/empire-state-building-visto-dal-rockefeler-centre-iStock-956426314-1080x721.jpeg?v=1643745279", false);
		
		photoServ.save(photo1);
		photoServ.save(photo2);
		photoServ.save(photo3);
	}

}
