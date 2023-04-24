package ajc.formation.spring.bibliotheque.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.spring.bibliotheque.entities.Avis;
import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
import ajc.formation.spring.bibliotheque.services.AvisService;

@RestController
@RequestMapping("/api/avis")
@CrossOrigin(origins = "*")
public class AvisRestController {
	
	@Autowired
	private AvisService avisSrv;

	@GetMapping("")
	@JsonView(JsonViews.Simple.class)
	public List<Avis> getAll() {
		return avisSrv.getAll();
	}

}
