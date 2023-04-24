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
public class UtilisateursTest {

	@Autowired
	AdherentService adherentService;
	
	@Autowired
	AdministrateurService adminSrv;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	@Test
	@Commit
	void ajouteAdminAdherent() {
		Utilisateur util = utilisateurService.getByLogin("admin");
		util.imprimerUtilisateur();
		
		util = utilisateurService.getByLogin("ad");
		util.imprimerUtilisateur();
		
		Administrateur admin = adminSrv.getById(Long.valueOf(2));
		admin.imprimerUtilisateur();
		

	}
	
	
}
