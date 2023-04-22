package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.services.LivreService;

@SpringBootTest
public class LivreTest {
	
	@Autowired
	LivreService livreServ;
	
	@Test
	@Commit
	void ajouterLivre() {
		Livre livre = new Livre("Le petit prince", "Saint Exupéry");
		livre.setStatut(StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getLivreId());
	}
	
//	@Test
//	@Commit
//	void supprimerLivre() {
//		Livre livre = new Livre("Le petit prince", "Saint Exupéry", StatutLivre.DISPONIBLE);
//		livreServ.createOrUpdate(livre);
//		assertNotNull(livre.getLivreId());
//	}

}
