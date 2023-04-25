package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


import ajc.formation.spring.bibliotheque.entities.Utilisateur;
import ajc.formation.spring.bibliotheque.exceptions.UtilisateurException;
import ajc.formation.spring.bibliotheque.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Utilisateur> getAll() {
		return utilisateurRepo.findAll();
	}

	public Utilisateur getById(int id) {
		
		return utilisateurRepo.findById(id).orElseThrow(() -> {
			throw new UtilisateurException("id inconnu");
		});
	}
	
	public Utilisateur getByLogin(String username) {
		return utilisateurRepo.findByLogin(username).orElseThrow(() -> {
			throw new UtilisateurException("login inconnu");
		});
	}


	public void delete(Utilisateur utilisateur) {
		deleteById(utilisateur.getId());
	}

	public void deleteById(int id) {
		utilisateurRepo.deleteById(id);
	}

	public void createOrUpdate(Utilisateur utilisateur) {
		if (utilisateur.getNom() == null || utilisateur.getNom().isBlank()) {
			throw new UtilisateurException("nom d'utilisateur obligatoire");
		}
		if (utilisateur.getPrenom() == null || utilisateur.getPrenom().isBlank()) {
			throw new UtilisateurException("mot de passe obligatoire");
		}
		if (utilisateur.getLogin() == null || utilisateur.getLogin().isBlank()) {
			throw new UtilisateurException("login obligatoire");
		}
		if (utilisateur.getPassword() == null || utilisateur.getPassword().isBlank()) {
			throw new UtilisateurException("mot de passe obligatoire");
		}
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		utilisateurRepo.save(utilisateur);
	}

	
}
