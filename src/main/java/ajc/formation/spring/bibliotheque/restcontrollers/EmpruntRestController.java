package ajc.formation.spring.bibliotheque.restcontrollers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

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
import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.exceptions.EmpruntException;
import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
import ajc.formation.spring.bibliotheque.services.EmpruntService;

@RestController
@RequestMapping("/api/emprunt")
@CrossOrigin(origins = "*")
public class EmpruntRestController {
	
	@Autowired
	private EmpruntService empruntSrv;
	
	@GetMapping("")
	@JsonView(JsonViews.Emprunt.class)
	public List<Emprunt> getAll() {
		return empruntSrv.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Emprunt.class)
	public Emprunt getById(@PathVariable Long id) {
		Emprunt emprunt = null;
		try {
			emprunt = empruntSrv.getById(id);
		} catch (EmpruntException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return emprunt;
	}

	@PostMapping("")
	@JsonView(JsonViews.Emprunt.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Emprunt create(@Valid @RequestBody Emprunt emprunt, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		emprunt.setDateDebut(LocalDate.now());
		emprunt.setDateFin(LocalDate.now().plusMonths(1));
		empruntSrv.createOrUpdate(emprunt);
		return empruntSrv.getById(emprunt.getId());
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Emprunt.class)
	public Emprunt update(@RequestBody Emprunt emprunt, @PathVariable Long id) {
		Emprunt empruntEnBase = empruntSrv.getById(id);
		if (emprunt.getDateDebut() != null) {
			empruntEnBase.setDateDebut(emprunt.getDateDebut());
		}
		if (emprunt.getDateFin() != null) {
			empruntEnBase.setDateFin(emprunt.getDateFin());
		}
		if (emprunt.getEmprunteur() != null) {
			empruntEnBase.setEmprunteur(emprunt.getEmprunteur());
		}
		if (emprunt.getLivre() != null) {
			empruntEnBase.setLivre(emprunt.getLivre());
		}
		empruntSrv.createOrUpdate(empruntEnBase);
		return empruntSrv.getById(empruntEnBase.getId());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		empruntSrv.deleteById(id);
	}

}
