package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Set;

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
	void asqdfprunt() {		
		
		
		Livre livre = new Livre("À l'ouest rien de nouveau", "Erich Maria Remarque", StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		Adherent adherent;
		try {
			adherent =  adherentService.getByLogin("gbouchard");
		} catch (Exception e) {
			adherent = new Adherent("Gérard", "Bouchard", "gbouchard", "mdp");
			adherentService.createOrUpdate(adherent);
		}
		assertNotNull(adherent.getId());
		
		
		Emprunt emprunt = new Emprunt(LocalDate.now(), adherent, livre);
		empruntSrv.createOrUpdate(emprunt);

	}
	
	@Test
	@Commit
	void livreEmprunt() {		
		
		
		Livre livre = new Livre("À l'ouest rien de nouveau", "Erich Maria Remarque", StatutLivre.DISPONIBLE);
		livreServ.createOrUpdate(livre);
		assertNotNull(livre.getId());
		
		Adherent adherent;
		try {
			adherent =  adherentService.getByLogin("gbouchard");
		} catch (Exception e) {
			adherent = new Adherent("Gérard", "Bouchard", "gbouchard", "mdp");
			adherentService.createOrUpdate(adherent);
		}
		assertNotNull(adherent.getId());
		
		
		Emprunt emprunt = new Emprunt(LocalDate.now(), adherent, livre);
		empruntSrv.createOrUpdate(emprunt);
		
		Set<Emprunt> emp = livreServ.getEmprunts(livre);
		System.out.println("ID LIVRE ==============================> " + livre.getId());
		System.out.println("TAILLE DE EMPR ==============================> " + emp.size());
		System.out.println("TAILLE DE EMPR ==============================> " + emp);
		
		Emprunt em = livreServ.getEmpruntActif(livre);
		System.out.println("+++ ++ + + + EMPR actif ==============================> " + em);
		System.out.println("+++ ++ + + + RENDU ???? ==============================> " + em.isRendu());

	}
	


}
