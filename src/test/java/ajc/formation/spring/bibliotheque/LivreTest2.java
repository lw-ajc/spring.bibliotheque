package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.services.EtiquetteService;
import ajc.formation.spring.bibliotheque.services.LivreService;

@SpringBootTest
public class LivreTest2 {
	
	@Autowired
	LivreService livreServ;
	@Autowired
	EtiquetteService etiqServ;
	
	@Test
	@Commit
	void ajouterLivre() {
		Livre livre = new Livre("Si c'est un homme", "Primo Lévi");
		livre.setStatut(StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		livre = new Livre("Si c'est un homme", "Primo Lévi");
		livre.setStatut(StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
	
	}
		
		

}
