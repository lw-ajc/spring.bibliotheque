package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ajc.formation.spring.bibliotheque.entities.Etiquette;
import ajc.formation.spring.bibliotheque.exceptions.EtiquetteException;
import ajc.formation.spring.bibliotheque.repositories.EtiquetteRepository;


public class EtiquetteService {
	
	@Autowired
	private EtiquetteRepository etiquetteRepo;
	
	
	public List<Etiquette> getAll() {
		return etiquetteRepo.findAll();
	}
	
	public Etiquette getByNom(String nom) {
		if (nom == null) {
			throw new EtiquetteException("nom obligatoire");
		}
		// NOTE : le repo implémente par défaut
		return etiquetteRepo.findById(nom).orElseThrow(() -> {
			throw new EtiquetteException("nom inconnu");
		});
	}
	
	public Etiquette getByNomWithLivres(String nom) {
		if (nom == null) {
			throw new EtiquetteException("nom d'étiquette obligatoire");
		}
		return etiquetteRepo.findByNomFetchLivres(nom).orElseThrow(() -> {
			throw new EtiquetteException("nom de l'étiquette inconnu");
		});
	}
	
	public void createOrUpdate(Etiquette etiquette) {
		if (etiquette.getNom() == null || etiquette.getNom().isBlank()) {
			throw new EtiquetteException("nom d'étiquette obligatoire");
		}
		if (etiquette.getParent() == null || etiquette.getParent().getNom().isBlank()) {
			throw new EtiquetteException("toute étiquette non racine doit avoir un parent valide");

		}
		etiquetteRepo.save(etiquette);
	}
	
	public void createOrUpdate(Etiquette etiquette, boolean racine) {
		if (etiquette.getNom() == null || etiquette.getNom().isBlank()) {
			throw new EtiquetteException("nom d'étiquette obligatoire");
		}
		//TODO cherche si le nom/id du parent est valide, et existe déjà dans la base de données
		if ( (etiquette.getParent() == null || etiquette.getParent().getNom().isBlank()) && racine) {
			throw new EtiquetteException("toute étiquette non racine doit avoir un parent valide");

		}
		etiquetteRepo.save(etiquette);
	}

}
