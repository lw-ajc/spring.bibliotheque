package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Administrateur;
import ajc.formation.spring.bibliotheque.entities.Etiquette;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.services.AdherentService;
import ajc.formation.spring.bibliotheque.services.AdministrateurService;
import ajc.formation.spring.bibliotheque.services.EtiquetteService;
import ajc.formation.spring.bibliotheque.services.LivreService;
import ajc.formation.spring.bibliotheque.services.UtilisateurService;

@SpringBootTest
public class FinalTest {
	@Autowired
	AdherentService adherentService;
	
	@Autowired
	AdministrateurService adminSrv;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	LivreService livreServ;
	
	@Autowired
	EtiquetteService etiqSrv;
	
	@Test
	@Commit
	void ajouteAdminAdherent() {
		// création d'un administrateur
		Administrateur administrateur = new Administrateur("admin", "admin", "admin", "admin");
		adminSrv.createOrUpdate(administrateur);
		assertNotNull(administrateur.getId());
		// création d'un adhérent
		Adherent adherent = new Adherent("Gérard", "Bouchard", "gbouchard", "mdp");
		adherentService.createOrUpdate(adherent);
		assertNotNull(adherent.getId());
		
		// création des étiquettes
		etiqSrv.createRacine();
		// genre littéraire
		etiqSrv.create("genre littéraire", "racine");
		etiqSrv.create("théâtre", "genre littéraire");
		etiqSrv.create("vaudeville", "théâtre");
		etiqSrv.create("poésie", "genre littéraire");
		etiqSrv.create("roman", "genre littéraire");
		etiqSrv.create("conte", "genre littéraire");
		etiqSrv.create("documentaire", "genre littéraire");
		// genre narratif
		etiqSrv.create("genre narratif", "racine");
		etiqSrv.create("science-fiction", "genre narratif");
		etiqSrv.create("guerre", "genre narratif");
		etiqSrv.create("fantasie", "genre narratif");
		etiqSrv.create("médiéval fantastique", "fantasie");
		// type de publication
		etiqSrv.create("publication", "racine");
		etiqSrv.create("magazine", "publication");
		etiqSrv.create("bande dessinée", "publication");
		
		// création de quelques livres
		livreServ.createOrUpdate(new Livre("Le petit prince", "Saint Exupéry", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(new Livre("La cousine Bette", "Balzac", StatutLivre.DISPONIBLE));
		
		livreServ.createOrUpdate(new Livre("Le Cid", "Corneille", StatutLivre.DISPONIBLE));
		
		livreServ.createOrUpdate(new Livre("Le rouge et le noir", "Stendhal", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(new Livre("La nausée", "Sartre", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(new Livre("Les fleurs du mal", "Baudelaire", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(new Livre("Le seigneur des annneaux", "JRR Tolkien", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(new Livre("La rubrique à brac [tome 1]", "Gotlib", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(new Livre("À l'ouest rien de nouveau", "Erich Maria Remarque", StatutLivre.DISPONIBLE));
		livreServ.createOrUpdate(new Livre("Le livre noir du communisme", "Stéphane Courtois", StatutLivre.DISPONIBLE));
		
		
		
		
	}

}
