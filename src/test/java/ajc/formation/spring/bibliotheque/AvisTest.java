package ajc.formation.spring.bibliotheque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Avis;
import ajc.formation.spring.bibliotheque.entities.AvisId;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.services.AdherentService;
import ajc.formation.spring.bibliotheque.services.AvisService;
import ajc.formation.spring.bibliotheque.services.LivreService;

@SpringBootTest
public class AvisTest {
	
	@Autowired
	LivreService livreService;
	@Autowired
	AdherentService adherentService;
	@Autowired
	AvisService avisSrv;
	
	@BeforeEach
	@Commit
	public void initTable() {
		// La racine est réinitialisée à chaque test.
		livreService.deleteAll();
		adherentService.deleteAll();
		avisSrv.deleteAll();
	}
	
	@Test
	@Commit
	void creerAvis() {
		
		
	}

}
