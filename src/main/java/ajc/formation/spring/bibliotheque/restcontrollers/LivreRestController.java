package ajc.formation.spring.bibliotheque.restcontrollers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import ajc.formation.spring.bibliotheque.entities.Etiquette;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
import ajc.formation.spring.bibliotheque.model.LivreAvecNomEtiquettes;
import ajc.formation.spring.bibliotheque.model.Recherche;
import ajc.formation.spring.bibliotheque.services.EtiquetteService;
import ajc.formation.spring.bibliotheque.services.LivreService;

@RestController
@RequestMapping("/api/livre")
@CrossOrigin(origins = "*")
public class LivreRestController {
	
	@Autowired
	private LivreService livreServ;
	@Autowired
	private EtiquetteService etiqServ;
	
//	@GetMapping("")
//	@JsonView(JsonViews.Simple.class)
//	public List<Livre> getAll() {
//		return livreServ.getAll();
//	}
	
	@GetMapping("")
	@JsonView(JsonViews.LivreWithEtiquettes.class)
	public List<Livre> getAll() {
		return livreServ.getAll();
	}
	
	
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.LivreWithEtiquettes.class)
	public Livre getById(@PathVariable int id) {
		Livre livre = null;
		livre = livreServ.getByIdWithEtiquettes(id);
		return livre;
	}
	
	
	
	@GetMapping("/{id}/emprunt_actif")
	@JsonView(JsonViews.Simple.class)
	public Emprunt getEmpruntActif(@PathVariable int id) {
		return livreServ.getEmpruntActif(livreServ.getById(id));
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
	
	@PostMapping("recherche")
	@JsonView(JsonViews.Simple.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<Livre> create(@RequestBody Recherche recherche, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		//TODO est-ce que l'optionnal interagi avec list ??? QUe se passe-t-il si aucun enregistrement correspond ?
		return livreServ.recherche(recherche.getMotifTitre(),
				recherche.getMotifAuteur(),
				recherche.getStatut(),
				recherche.getEtiquettes());
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
	
	@PutMapping("/etiquettes/{id}")
	@JsonView(JsonViews.Simple.class)
	public Livre update(@RequestBody LivreAvecNomEtiquettes livre, @PathVariable int id) {
		Livre livreEnBase = livreServ.getById(id);
		livreEnBase.setTitre(livre.getTitre());
		livreEnBase.setAuteur(livre.getAuteur());
		livreEnBase.setStatut(livre.getStatut());
		Set<Etiquette> etiquettes = new HashSet<Etiquette>();
		for(String nom : livre.getEtiquettes()) {
			Etiquette etiquette = etiqServ.getByNom(nom);
			etiquettes.add(etiquette);
		}
		livreEnBase.setEtiquettes(etiquettes);
		livreServ.createOrUpdate(livreEnBase);
		return livreEnBase;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		livreServ.deleteById(id);
	}

}
