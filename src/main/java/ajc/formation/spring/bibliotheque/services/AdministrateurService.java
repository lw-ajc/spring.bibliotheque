package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ajc.formation.spring.bibliotheque.entities.Administrateur;
import ajc.formation.spring.bibliotheque.exceptions.AdministrateurException;
import ajc.formation.spring.bibliotheque.repositories.AdministrateurRepository;

public class AdministrateurService {
	@Autowired
	private AdministrateurRepository administrateurRepo;
	// @Autowired
	// private LivreRepository livreRepo;
	
	public List<Administrateur> getAll() {
		return administrateurRepo.findAll();
	}

	public Administrateur getById(Long id) {
		if (id == null) {
			throw new AdministrateurException("id obligatoire");
		}
		return administrateurRepo.findById(id).orElseThrow(() -> {
			throw new AdministrateurException("id inconnu");
		});
	}


	public void delete(Administrateur administrateur) {
		deleteById(administrateur.getId());
	}

	public void deleteById(Long id) {
		Administrateur a = getById(id);
		administrateurRepo.deleteById(id);
	}

	public void createOrUpdate(Administrateur adherent) {
		if (adherent.getNomUtilisateur() == null || adherent.getNomUtilisateur().isBlank()) {
			throw new AdministrateurException("nom d'utilisateur obligatoire");
		}
		if (adherent.getMotDePasse() == null || adherent.getMotDePasse().isBlank()) {
			throw new AdministrateurException("mot de passe obligatoire");
		}
		administrateurRepo.save(adherent);
	}
}
