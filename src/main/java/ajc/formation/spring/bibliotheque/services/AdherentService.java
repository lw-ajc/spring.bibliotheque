package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Role;
import ajc.formation.spring.bibliotheque.exceptions.AdherentException;
import ajc.formation.spring.bibliotheque.exceptions.AdministrateurException;
import ajc.formation.spring.bibliotheque.repositories.AdherentRepository;


@Service
public class AdherentService {
	
	@Autowired
	private AdherentRepository adherentRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public List<Adherent> getAll() {
		return adherentRepo.findAll();
	}

	public Adherent getById(Long id) {
		if (id == null) {
			throw new AdherentException("id obligatoire");
		}
		//Optional<Adherent> adherent = adherentRepo.findById(id);
		return adherentRepo.findById(id).orElseThrow(() -> {
			throw new AdherentException("id inconnu");
		});
	}


	public void delete(Adherent adherent) {
		deleteById(adherent.getId());
	}

	public void deleteById(Long id) {
		@SuppressWarnings("unused")
		Adherent adherent = getById(id);
		adherentRepo.deleteById(id);
	}

	public void createOrUpdate(Adherent adherent) {
		if (adherent.getNom() == null || adherent.getNom().isBlank()) {
			throw new AdherentException("nom obligatoire");
		}
		if (adherent.getPrenom() == null || adherent.getPrenom().isBlank()) {
			throw new AdministrateurException("prénom obligatoire");
		}
		if (adherent.getLogin() == null || adherent.getLogin().isBlank()) {
			throw new AdministrateurException("login obligatoire");
		}
		if (adherent.getPassword() == null || adherent.getPassword().isBlank()) {
			throw new AdministrateurException("mot de passe obligatoire");
		}
		adherent.setPassword(passwordEncoder.encode(adherent.getPassword()));
		adherent.setRole(Role.ROLE_ADHERENT);
		adherentRepo.save(adherent);
	}

	
	
	public void emprunterLivre() {
		
	}
	
	
	public void retournerLivre() {
		
	}
	
	
	public void ajouterFavori() {
		
	}
	
	public void retirerFavori() {
		
	}
	
	
	public void avisLivre() {
		// commentaire + note
	}
	
}
