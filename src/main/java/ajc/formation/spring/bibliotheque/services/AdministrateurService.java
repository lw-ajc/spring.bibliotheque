package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import ajc.formation.spring.bibliotheque.entities.Administrateur;
import ajc.formation.spring.bibliotheque.exceptions.AdministrateurException;
import ajc.formation.spring.bibliotheque.repositories.AdministrateurRepository;

@Service
public class AdministrateurService {
	@Autowired
	private AdministrateurRepository administrateurRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	// @Autowired
	// private LivreRepository livreRepo;
	
	public List<Administrateur> getAll() {
		return administrateurRepo.findAll();
	}

	public Administrateur getById(int id) {
		
		return administrateurRepo.findById(id).orElseThrow(() -> {
			throw new AdministrateurException("id inconnu");
		});
	}


	public void delete(Administrateur administrateur) {
		deleteById(administrateur.getId());
	}

	public void deleteById(int id) {
		Administrateur a = getById(id);
		administrateurRepo.deleteById(id);
	}

	public void createOrUpdate(Administrateur administrateur) {
		if (administrateur.getNom() == null || administrateur.getNom().isBlank()) {
			throw new AdministrateurException("nom d'utilisateur obligatoire");
		}
		if (administrateur.getPrenom() == null || administrateur.getPrenom().isBlank()) {
			throw new AdministrateurException("mot de passe obligatoire");
		}
		if (administrateur.getLogin() == null || administrateur.getLogin().isBlank()) {
			throw new AdministrateurException("login obligatoire");
		}
		if (administrateur.getPassword() == null || administrateur.getPassword().isBlank()) {
			throw new AdministrateurException("mot de passe obligatoire");
		}
		administrateur.setPassword(passwordEncoder.encode(administrateur.getPassword()));
		administrateurRepo.save(administrateur);
	}
}
