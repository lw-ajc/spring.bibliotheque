package ajc.formation.spring.bibliotheque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Administrateur;
import ajc.formation.spring.bibliotheque.services.AdministrateurService;


@SpringBootTest
public class AdministrateurTest {
	
	@Autowired
	AdministrateurService adminSrv;
	
	
	@Test
	@Commit
	void creerAdmin() {
		Administrateur admin = new Administrateur();
		admin = new Administrateur("Coco", "Channel", "admin", "admin");
		adminSrv.createOrUpdate(admin);
		assertNotNull(admin.getId());
		
	}
	
	@Test
	@Commit
	void suppr() {
		Administrateur admin = new Administrateur("Jean", "Jean", "jj", "mdp", "jj@trucmuche.com");
		adminSrv.createOrUpdate(admin);
		
	}


}
