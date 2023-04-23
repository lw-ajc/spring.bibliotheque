package ajc.formation.spring.bibliotheque.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccueilAdminController {

		@GetMapping("/api/accueil_admin")
		public String sayHello() {
			return "Accueil pour les admins\nSEULS LES ADMINS SONT AUTORISÉS SUR CETTE PAGE !!!!!AccueilRestController.java";
		}
}
