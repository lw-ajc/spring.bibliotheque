package ajc.formation.spring.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.spring.bibliotheque.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	Optional<Utilisateur> findByLogin(String login);

}
