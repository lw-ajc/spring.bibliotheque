package ajc.formation.spring.bibliotheque.restcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
import ajc.formation.spring.bibliotheque.services.AdherentService;

@RestController
@RequestMapping("/api/adherent")
public class AdherentRestController {

	@Autowired
	private AdherentService adherentSrv;

	@GetMapping("")
	@JsonView(JsonViews.Simple.class)
	public List<Adherent> getAll() {
		return adherentSrv.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Simple.class)
	public Adherent getById(@PathVariable Long id) {
		Adherent adherent = null;
		adherent = adherentSrv.getById(id);
		return adherent;
	}


	@PostMapping("")
	@JsonView(JsonViews.Simple.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Adherent create(@Valid @RequestBody Adherent adherent, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		adherentSrv.createOrUpdate(adherent);
		return adherent;
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Simple.class)
	public Adherent update(@RequestBody Adherent adherent, @PathVariable Long id) {
		Adherent adherentEnBase = adherentSrv.getById(id);
		adherentSrv.createOrUpdate(adherentEnBase);
		return adherentEnBase;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		adherentSrv.deleteById(id);
	}
}