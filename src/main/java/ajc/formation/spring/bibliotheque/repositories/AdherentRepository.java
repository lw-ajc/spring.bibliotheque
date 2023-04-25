package ajc.formation.spring.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Administrateur;


public interface AdherentRepository extends JpaRepository<Adherent, Integer>{
	Optional<Adherent> findByLogin(String login);

}
