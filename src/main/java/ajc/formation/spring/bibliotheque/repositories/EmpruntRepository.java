package ajc.formation.spring.bibliotheque.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.entities.Livre;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
	
	List<Emprunt> findByEmprunteur (Adherent emprunteur);
	
	List<Emprunt> findByLivre (Livre livre);
	
//	Optional<Emprunt> findOne(Emprunt emprunt);
	
	void delete(Emprunt emprunt);
	
	boolean existsByLivre(Livre livre);
	
	@Query("select e from Emprunt e where e.dateFin<=LocalDate.now() and e.rendu=false")
	Optional<Emprunt> findByDateFinDepassee();
	
	@Query("select e from Emprunt e where e.dateFin=:date and e.rendu=true")
	Optional<Emprunt> findByArchive(@Param("date") LocalDate date);
	
	//TODO vérifier que la requête ets valide
	@Query("select e from Emprunt e where e.emprunteur.id=:emprunteurId")
	Optional<Emprunt> findByEmprunteurId(@Param("emprunteurId") Long emprenteurId);
	
	
	List<Emprunt> findByRendu (boolean rendu);
	
	@Transactional
	@Modifying
	void deleteByEmprunteur (Adherent emprunteur);
	
	@Transactional
	@Modifying
	void deleteByLivre (Livre livre);
	
	
	@Transactional
	@Modifying
	void deleteByRendu (boolean rendu);
}