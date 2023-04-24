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
public class LivreTest {
	
	@Autowired
	LivreService livreServ;
	@Autowired
	EtiquetteService etiqServ;
	
	@Test
	@Commit
	void ajouterLivre() {
		Livre livre = new Livre("Le petit prince", "Saint Exupéry");
		livre.setStatut(StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		livreServ.createOrUpdate(new Livre("La cousine Bette", "Balzac", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(livre);
	}
	
	@Test
	@Commit
	void supprimerLivre() {
		Livre livre = new Livre("À l'ouest rien de nouveau", "Erich Maria Remarque", StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		livreServ.deleteById(livre.getId());
		assertNotNull(livre);
	}
	
	@Test
	@Commit
	void majLivre() {
		Livre livre = new Livre("Le sid", "Korneille", StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		livre = livreServ.getById(livre.getId());
		livre.setAuteur("Le cid");
		livre.setTitre("Corneille");
		livreServ.createOrUpdate(livre);
		assertNotNull(livre);
	}
	
	@Test
	@Commit
	void rechercheTitre() {
		Livre livre = new Livre("Le livre noir du communisme", "Stéphane Courtois", StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		livre = new Livre("Le rouge et le noir", "Stendhal", StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		
		List<Livre> noir = livreServ.getLivreWithTitreContaining("noir");
		for(Livre livreNoir : noir) {
			livreNoir.imprimer();
		}
		assert(noir.size() > 1);
		
	}
		
		

}
