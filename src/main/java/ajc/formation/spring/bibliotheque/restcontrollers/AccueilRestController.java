package ajc.formation.spring.bibliotheque.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccueilRestController {

		@GetMapping("/api/accueil")
		public String sayHello() {
			return "Accueil test tomcat";
		}
}
