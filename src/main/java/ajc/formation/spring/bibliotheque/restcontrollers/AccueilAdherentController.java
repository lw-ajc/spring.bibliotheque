package ajc.formation.spring.bibliotheque.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccueilAdherentController {

		@GetMapping("/api/accueil_adherent")
		public String sayHello() {
			return "Accueil pour les adhérent et admins\nSEULS LES ADHÉRENTS ET LES ADMINS SONT AUTORISÉS SUR CETTE PAGE !!!!!AccueilRestController.java";
		}
}
