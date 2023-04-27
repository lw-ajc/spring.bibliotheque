package ajc.formation.spring.bibliotheque.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;

public interface LivreRepository extends JpaRepository<Livre, Integer>{
	
	List<Livre> findByTitreContaining(String titre);
	
	@Query("select l from Livre l left join fetch l.etiquettes where l.id=:id")
	Optional<Livre> findByIdFectchEtiquettes(@Param("id") int id);
	
	@Query("select l from Livre l left join fetch l.etiquettes")
	List<Livre> findAllWithEtiquettes();
	
	@Query("select l from Livre l left join fetch l.emprunts where l.id=:id")
	Optional<Livre> findByIdFetchEmprunts(@Param("id") int id);
	
	// Pour le moteur de recherche
	@Query("select l from Livre l JOIN l.etiquettes e WHERE l.titre like %:motifTitre% AND l.auteur like %:motifAuteur% AND l.statut=:statut AND e.nom IN :etiquettes")
	List<Livre> recherche(@Param("motifTitre") String motifTitre,
			@Param("motifAuteur") String motifAuteur,
			@Param("statut") StatutLivre statut,
			@Param("etiquettes") Set<String> etiquettes);
	
	@Query("select l from Livre l JOIN l.etiquettes e WHERE l.titre like %:motifTitre% AND l.auteur like %:motifAuteur% AND e.nom IN :etiquettes")
	List<Livre> recherche(@Param("motifTitre") String motifTitre,
			@Param("motifAuteur") String motifAuteur,
			@Param("etiquettes") Set<String> etiquettes);
	
	@Query("select l from Livre l WHERE l.titre like %:motifTitre% AND l.auteur like %:motifAuteur% AND l.statut=:statut")
	List<Livre> recherche(@Param("motifTitre") String motifTitre,
			@Param("motifAuteur") String motifAuteur,
			@Param("statut") StatutLivre statut);
	
	@Query("select l from Livre l WHERE l.titre like %:motifTitre% AND l.auteur like %:motifAuteur%")
	List<Livre> recherche(@Param("motifTitre") String motifTitre,
			@Param("motifAuteur") String motifAuteur);
	
	@Query("SELECT l FROM Livre l JOIN l.etiquettes e WHERE e.nom IN :etiquettes")
	List<Livre> rechercheEtiquette(@Param("etiquettes") Set<String> etiquettes);
	


	
}
