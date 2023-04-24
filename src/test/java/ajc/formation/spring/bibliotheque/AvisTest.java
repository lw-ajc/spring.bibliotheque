package ajc.formation.spring.bibliotheque;

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
	
	
	@Test
	@Commit
	void creerAvis() {
		Livre livre = new Livre("Le petit prince", "Saint Exupéry", StatutLivre.DISPONIBLE);
		Adherent adherent = new Adherent("Gérard", "Bouchard", "gbouchard", "mdp");
		livreService.createOrUpdate(livre);
		adherentService.createOrUpdate(adherent);
		Avis avis = new Avis(adherent.getId(), new Long(livre.getLivreId()), "commentaire", 5);
		avisSrv.createOrUpdate(avis);
		
		AvisId avisId = new AvisId(avis.getAdherentId(), avis.getLivreId());
		avisSrv.getById(avisId);
		
		
	}

}
