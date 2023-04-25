package ajc.formation.spring.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.spring.bibliotheque.entities.Administrateur;


public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer>{
	Optional<Administrateur> findByLogin(String login);

}
