package ajc.formation.spring.bibliotheque.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.spring.bibliotheque.entities.Livre;

public interface LivreRepository extends JpaRepository<Livre, Integer>{
	
	List<Livre> findByTitreContaining(String titre);
	
	@Query("select l from Livre l left join fetch l.etiquettes where l.id=:id")
	Optional<Livre> findByIdFectchEtiquettes(@Param("id") Long id);
	
	@Query("select l from Livre l left join fetch l.emprunts where l.id=:id")
	Optional<Livre> findByIdFectchEmprunts(@Param("id") Long id);
	
}
