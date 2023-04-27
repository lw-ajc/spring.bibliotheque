package ajc.formation.spring.bibliotheque.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.spring.bibliotheque.entities.Etiquette;
import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
import ajc.formation.spring.bibliotheque.services.EtiquetteService;

@RestController
@RequestMapping("/api/etiquette")
@CrossOrigin(origins = "*")
public class EtiquetteRestController {
	
	@Autowired
	private EtiquetteService etiquetteSrv;
	
	@GetMapping("")
	@JsonView(JsonViews.Simple.class)
	public List<Etiquette> getAll() {
		return etiquetteSrv.getAll();
	}

}
