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
		Livre livre = new Livre("Rubrique à brac [Tome 1]", "Gotlib", StatutLivre.DISPONIBLE);
		Adherent adherent = new Adherent("Gérard", "Bouchard", "ggbouchard", "mdp");
		livreService.createOrUpdate(livre);
		adherentService.createOrUpdate(adherent);
		
		Avis avis = new Avis(adherent.getId(), new Long(livre.getId()), "moyen le tome 1", 5);
		avisSrv.createOrUpdate(avis);
		avisSrv.getById(new AvisId(avis.getAdherentId(), avis.getLivreId()));
		assert(avisSrv.getById(new AvisId(avis.getAdherentId(), avis.getLivreId())) != null);
		
		livre = new Livre("Rubrique à brac [Tome 2]", "Gotlib", StatutLivre.DISPONIBLE);
		livreService.createOrUpdate(livre);
		avis = new Avis(adherent.getId(), new Long(livre.getId()), "super le tome 2", 5);
		avisSrv.createOrUpdate(avis);
		avisSrv.getById(new AvisId(avis.getAdherentId(), avis.getLivreId()));
		assert(avisSrv.getById(new AvisId(avis.getAdherentId(), avis.getLivreId())) != null);
		
		
		adherent = new Adherent("Jojo", "Mojo", "jmoj", "mdp");
		adherentService.createOrUpdate(adherent);
		livre =  new Livre("Rubrique à brac [Tome 3]", "Gotlib", StatutLivre.DISPONIBLE);
		livreService.createOrUpdate(livre);
		avis = new Avis(adherent.getId(), new Long(livre.getId()), "/!\\ à ééviter", 5);
		avisSrv.createOrUpdate(avis);
		avisSrv.getById(new AvisId(avis.getAdherentId(), avis.getLivreId()));
		assert(avisSrv.getById(new AvisId(avis.getAdherentId(), avis.getLivreId())) != null);
		
		
		
	}

}
