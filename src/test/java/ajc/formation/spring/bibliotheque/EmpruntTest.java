package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.springframework.core.style.ToStringCreator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.services.AdherentService;
import ajc.formation.spring.bibliotheque.services.EmpruntService;
import ajc.formation.spring.bibliotheque.services.LivreService;

@SpringBootTest
public class EmpruntTest {
	
	@Autowired
	EmpruntService empruntSrv;
	@Autowired
	LivreService livreServ;
	@Autowired
	AdherentService adherentService;
	
	@Test
	@Commit
	void ajouterEmprunt() {		
		
		//adherentService.deleteAll();

		Livre livre = new Livre("À l'ouest rien de nouveau", "Erich Maria Remarque", StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		Adherent adherent = new Adherent("Gérard", "Bouchard", "gbouchard", "mdp");
		adherentService.createOrUpdate(adherent);
		assertNotNull(adherent.getId());
		
//		Emprunt emprunt = new Emprunt();		
//		empruntSrv.createOrUpdate(emprunt);
//		assertNotNull(emprunt.getId());
		
//		Emprunt emprunt = new Emprunt(LocalDate.now(), adherent, livre);
//		empruntSrv.createOrUpdate(emprunt);
//		
//		System.out.println(emprunt.toString());
	}
	
//	@Test
//	@Commit
//	void supprimerLivre() {		
//		
//		adherentService.deleteAll();
//
//		Livre livre = new Livre("À l'ouest rien de nouveau", "Erich Maria Remarque", StatutLivre.DISPONIBLE);
//		livreServ.createOrUpdate(livre);
//		assertNotNull(livre.getId());
//		
//		Adherent adherent = new Adherent("Gérard", "Bouchard", "gbouchard", "mdp");
//		adherentService.createOrUpdate(adherent);
//		assertNotNull(adherent.getId());
//		
//		Emprunt emprunt = new Emprunt();		
//		empruntSrv.createOrUpdate(emprunt);
//		assertNotNull(emprunt.getId());
//		
//		emprunt = new Emprunt(LocalDate.now(), adherent, livre);
//		empruntSrv.createOrUpdate(emprunt);
//		
//		livreServ.delete(livre);
//		System.out.println(emprunt.toString());
//	}

}