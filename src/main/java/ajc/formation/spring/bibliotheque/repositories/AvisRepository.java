package ajc.formation.spring.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.spring.bibliotheque.entities.Avis;
import ajc.formation.spring.bibliotheque.entities.AvisId;

public interface AvisRepository extends JpaRepository<Avis, AvisId>{
	Optional<Avis> findByAdherentId(Long adhrentId);
	Optional<Avis> findByLivreId(Long livreId);
}
