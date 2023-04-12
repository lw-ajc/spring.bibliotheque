package ajc.formation.spring.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.spring.bibliotheque.entities.Etiquette;

//TODO vérif le typage du JpaRepository
// <Etiquette, String> car l'@Id est un string
public interface EtiquetteRepository extends JpaRepository<Etiquette, String> {
	
	@Query("select e from Etiquette e left join fetch e.livres where e.nom=:nom")
	Optional<Etiquette> findByNomFetchLivres(@Param("nom") String nom);
	
	

}


//public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
//	@Query("select f from Fournisseur f left join fetch f.produits where f.id=:id")
//	Optional<Fournisseur> findByIdFetchProduits(@Param("id") Long id);
//}