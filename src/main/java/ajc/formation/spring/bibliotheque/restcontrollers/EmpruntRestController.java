package ajc.formation.spring.bibliotheque.restcontrollers;

import java.time.LocalDate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.exceptions.EmpruntException;
import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
import ajc.formation.spring.bibliotheque.model.CoupleEmprunt;
import ajc.formation.spring.bibliotheque.services.AdherentService;
import ajc.formation.spring.bibliotheque.services.EmpruntService;
import ajc.formation.spring.bibliotheque.services.LivreService;

@RestController
@RequestMapping("/api/emprunt")
@CrossOrigin(origins = "*")
public class EmpruntRestController {
	
	@Autowired
	private EmpruntService empruntSrv;
	@Autowired
	private AdherentService adSrv;
	@Autowired
	private LivreService livreSrv;
	
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
	
	@GetMapping("/par_adherent/{id}")
	@JsonView(JsonViews.Emprunt.class)
	public List<Emprunt> getByAdherent(@PathVariable Long id) {
		List<Emprunt> emprunts = null;
		try {
			emprunts = empruntSrv.getByEmprunteur(id);
		} catch (EmpruntException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return emprunts;
	}

	@PostMapping("/obsolete")
	@JsonView(JsonViews.Emprunt.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Emprunt createObsolete(@Valid @RequestBody CoupleEmprunt couple, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Adherent adherent = adSrv.getById(couple.getAdherentId());
		Livre livre = livreSrv.getById(couple.getLivreId());
		Emprunt emprunt = new Emprunt();
		emprunt.setDateDebut(LocalDate.now());
		emprunt.setDateFin(LocalDate.now().plusMonths(3));
		emprunt.setLivre(livre);
		emprunt.setEmprunteur(adherent);
		empruntSrv.createOrUpdate(emprunt);
		return empruntSrv.getById(emprunt.getId());
	}
	
	@PostMapping("")
	@JsonView(JsonViews.Emprunt.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Emprunt create(@Valid @RequestBody Livre livre, @AuthenticationPrincipal Adherent emprunteur) {
		Emprunt emprunt = new Emprunt();
		Livre livreEnBase = livreSrv.getById(livre.getId());
		livreEnBase.setStatut(StatutLivre.EMPRUNTE);
		emprunt.setDateDebut(LocalDate.now());
		emprunt.setDateFin(LocalDate.now().plusMonths(1));
		emprunt.setLivre(livreEnBase);
		emprunt.setEmprunteur(emprunteur);
		empruntSrv.create(emprunt);
		livreSrv.createOrUpdate(livreEnBase);
		return empruntSrv.getById(emprunt.getId());
	}
	
	

//	@PostMapping("")
//	@JsonView(JsonViews.Commande.class)
//	public Commande create(@RequestBody List<ElementPanier> panier, @AuthenticationPrincipal Compte compte) {
//		Commande commande = new Commande(compte.getClient());
//		Set<Achat> achats = new HashSet<>();
//		panier.forEach(e -> {
//			achats.add(new Achat(new AchatId(commande, produitSrv.getById(e.getIdProduit())), e.getQuantite()));
//		});
//		commande.setAchats(achats);
//		commandeService.create(commande);
//		return commande;
//	}

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
		empruntEnBase.setDateFin(emprunt.getDateFin());
		empruntEnBase.setRendu(emprunt.isRendu());
		empruntSrv.createOrUpdate(empruntEnBase);
		return empruntSrv.getById(empruntEnBase.getId());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		empruntSrv.deleteById(id);
	}

}
