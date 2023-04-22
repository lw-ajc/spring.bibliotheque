package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Etiquette;
import ajc.formation.spring.bibliotheque.exceptions.EtiquetteException;
import ajc.formation.spring.bibliotheque.repositories.EtiquetteRepository;

@Service
public class EtiquetteService {
	private static String nomRacine = "racine";
	
	@Autowired
	private EtiquetteRepository etiquetteRepo;
	
	
	public List<Etiquette> getAll() {
		return etiquetteRepo.findAll();
	}
	
	public Etiquette getByNom(String nom) {
		if (nom == null) {
			throw new EtiquetteException("nom obligatoire");
		}
		return etiquetteRepo.findById(nom).orElseThrow(() -> {
			throw new EtiquetteException.IdIntrouvable("nom introuvable : " + nom);
		});
	}
	
//	public Etiquette getByNom(String nom) {
//		if (nom == null) {
//			throw new EtiquetteException("nom obligatoire");
//		}
//		return etiquetteRepo.findById(nom).orElseThrow(() -> {
//			throw new EtiquetteException("nom introuvable : " + nom);
//		});
//	}
	
	public Etiquette getByNomWithLivres(String nom) {
		if (nom == null) {
			throw new EtiquetteException("nom d'étiquette obligatoire");
		}
		return etiquetteRepo.findByNomFetchLivres(nom).orElseThrow(() -> {
			throw new EtiquetteException("nom d'étiquette inconnue");
		});
	}
	
	public Etiquette getByNomWithEnfants(String nom) {
		if (nom == null) {
			throw new EtiquetteException("nom d'étiquette obligatoire");
		}
		return etiquetteRepo.findByNomFetchEnfants(nom).orElseThrow(() -> {
			throw new EtiquetteException.IdIntrouvable("nom introuvable : " + nom);
		});
	}
	
	public void createRacine() {
		//TODO rajouter un test qui regarde si la racine n'existe pas déjà
		//TODO rajouter un test qui regarde si la table est vide
		//NOTE si la racine est recréée, alors toute la hiérarchie doit être reconstruite
		etiquetteRepo.save( new Etiquette(this.nomRacine, (Etiquette) null));
	}	
	
	public void create(String nom, String nomParent) {
		// Créer une étiquette et l'ajoute aux enfants de son parent IMMÉDIAT
		if (nom == null || nom.isBlank()) {
			throw new EtiquetteException("nom d'étiquette obligatoire");
		}
		if (nomParent == null || nomParent.isBlank()) {
			throw new EtiquetteException("toute étiquette sauf la racine doit avoir un nom de parent valide");
		}
		//TODO vérif si le parent existe dans la base
		Etiquette etiquette = new Etiquette(nom, new Etiquette(nomParent));
		etiquetteRepo.save(etiquette);
	}
	
	public void create(Etiquette etiquette) {
		// Créer une étiquette et l'ajoute aux enfants de son parent immédiat
		create(etiquette.getNom(), etiquette.getParent().getNom());
	}
	
	public void rename(String nom, String nouveauNom) {
		// Renomme une étiquette en notifiant les enfants et le parent
		//TODO renommer les étiquettes des LIVRES ÉTIQUETÉS
		if (nom == null || nom.isBlank()) {
			throw new EtiquetteException("nom d'étiquette obligatoire");
		}
		Etiquette etiquette = getByNomWithEnfants(nom);
		//TODO demander au prof pk on peut pas suppr l'objet ici
		etiquette.setNom(nouveauNom);
		etiquetteRepo.save(etiquette);
		for (Etiquette enfant : etiquette.getEnfants()) {
			enfant.setParent(etiquette);
			etiquetteRepo.save(enfant);
		}
		etiquetteRepo.delete(getByNom(nom));
	}
	
	public void deleteByNom(String nom) {
		etiquetteRepo.delete(getByNom(nom));
	}
	
	
	
	

}
