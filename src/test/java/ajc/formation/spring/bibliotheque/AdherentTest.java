package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Administrateur;
import ajc.formation.spring.bibliotheque.entities.Role;
import ajc.formation.spring.bibliotheque.entities.Utilisateur;
import ajc.formation.spring.bibliotheque.services.AdherentService;
import ajc.formation.spring.bibliotheque.services.AdministrateurService;
import ajc.formation.spring.bibliotheque.services.UtilisateurService;

@SpringBootTest
public class AdherentTest {

	@Autowired
	AdherentService adherentService;
	
	@Autowired
	AdministrateurService adminSrv;
	
	@Autowired
	UtilisateurService utilisateurService;
	
//	@Test
//	@Commit
//	void ajouteAdminAdherent() {
//		Adherent adherent = new Adherent("Gérard", "Bouchard", "user", "user");
//		adherent.imprimerUtilisateur();
//		adherentService.createOrUpdate(adherent);
//		System.out.println(adherent.getNom());
//		assertNotNull(adherent.getId());
//		
//		Administrateur administrateur = new Administrateur("admin", "admin", "admin", "admin");
//		adminSrv.createOrUpdate(administrateur);
//		assertNotNull(administrateur.getId());
//		
//	}
//	
//	@Test
//	@Commit
//	void extraireUtilisateur() {
//		Adherent adherent = new Adherent("Dédé", "Michu", "dmichu", "mdp");
//		adherentService.createOrUpdate(adherent);
//		assertNotNull(adherent.getId());
//		
//		Administrateur administrateur = new Administrateur("adm", "adm", "ad", "adm");
//		adminSrv.createOrUpdate(administrateur);
//		assertNotNull(administrateur.getId());
//		
//		utilisateurService.getById(adherent.getId());
//		utilisateurService.getById(administrateur.getId());
//	}
	
	@Test
	@Commit
	void ajouteAdherent() {
		Adherent adherent = new Adherent("user", "user", "user", "mdp");
		adherent.imprimerUtilisateur();
		adherentService.createOrUpdate(adherent);
		System.out.println(adherent.getNom());
		assertNotNull(adherent.getId());
	}
}
