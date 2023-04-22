package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Administrateur;
import ajc.formation.spring.bibliotheque.services.AdherentService;
import ajc.formation.spring.bibliotheque.services.AdministrateurService;

@SpringBootTest
public class UtilisateursTest {

	@Autowired
	AdherentService adherentService;
	
	@Autowired
	AdministrateurService adminSrv;
	
	@Test
	@Commit
	void insertTest() {
		Adherent adherent = new Adherent("Gérard", "Bouchard", "gbouchard", "mdp");
		adherent.imprimerUtilisateur();
		adherentService.createOrUpdate(adherent);
		System.out.println(adherent.getNom());
		assertNotNull(adherent.getId());
		
		Administrateur administrateur = new Administrateur("Gérard", "Bouchard", "gbouchard", "mdp");
		adminSrv.createOrUpdate(administrateur);
		assertNotNull(administrateur.getId());
	}
}
