package ajc.formation.spring.bibliotheque.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
import ajc.formation.spring.bibliotheque.services.LivreService;

@RestController
@RequestMapping("/api/livre")
@CrossOrigin(origins = "*")
public class LivreRestController {
	
	@Autowired
	private LivreService livreServ;
	
	@GetMapping("")
	@JsonView(JsonViews.Simple.class)
	public List<Livre> getAll() {
		return livreServ.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Simple.class)
	public Livre getById(@PathVariable int id) {
		Livre livre = null;
		livre = livreServ.getById(id);
		return livre;
	}
	
	@PostMapping("")
	@JsonView(JsonViews.Simple.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Livre create(@RequestBody Livre livre, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		livreServ.createOrUpdate(livre);
		return livre;
	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.Simple.class)
	public Livre update(@RequestBody Livre livre, @PathVariable int id) {
		Livre livreEnBase = livreServ.getById(id);
		livreEnBase.setTitre(livre.getTitre());
		livreEnBase.setAuteur(livre.getAuteur());
		livreEnBase.setStatut(livre.getStatut());
		livreServ.createOrUpdate(livreEnBase);
		return livreEnBase;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		livreServ.deleteById(id);
	}

}
