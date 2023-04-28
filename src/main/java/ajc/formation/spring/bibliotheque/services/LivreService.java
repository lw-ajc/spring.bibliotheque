package ajc.formation.spring.bibliotheque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.exceptions.LivreException;
import ajc.formation.spring.bibliotheque.repositories.EmpruntRepository;
import ajc.formation.spring.bibliotheque.repositories.LivreRepository;




@Service
public class LivreService {
	
//	@Autowired
//	private Validator validator;
	
	@Autowired
	private LivreRepository livreRepo;
	
	@Autowired
	private EmpruntRepository empRepo;
	
	public List<Livre> getAll() {
		return livreRepo.findAll();
	}
	
	public List<Livre> getAllWithEtiquettes() {
		return livreRepo.findAllWithEtiquettes();
	}
	
	public Livre getById(Integer id) {
		if (id == null) {
			throw new LivreException("id obligatoire"); //TODO créer une exception spéciale
		}
		return livreRepo.findById(id).orElseThrow(() -> {
			throw new LivreException("id inconnu");
		});
	}
	
	public Livre getById(int id) {
		return getById(Integer.valueOf(id));
	}
	
	public Livre getByIdWithEtiquettes(int id) {
		return livreRepo.findByIdFectchEtiquettes(id).orElseThrow(() -> {
			throw new LivreException("id inconnu");
		});
	}
	
	public List<Livre>getLivreWithTitreContaining(String motif){
		return livreRepo.findByTitreContaining(motif);
	}
	
	
	public List<Livre> recherche(String motifTitre, String motifAuteur, StatutLivre statut, Set<String> etiquettes) {
		//TODO changer cette fonction hideuse
		if(!motifTitre.isEmpty()) {
			if(!motifAuteur.isEmpty()) {
				if(statut != null) {
					if(!etiquettes.isEmpty()) {
						return livreRepo.recherche(motifTitre, motifAuteur, statut, etiquettes);
					} else {
						return livreRepo.recherche(motifTitre, motifAuteur, statut);
					}
				} else {
					if (!etiquettes.isEmpty()) {
						return livreRepo.recherche(motifTitre, motifAuteur, etiquettes);
					} else {
						return livreRepo.recherche(motifTitre, motifAuteur);
					}
				}
			} else {
				if(statut != null) {
					if(!etiquettes.isEmpty()) {
						return livreRepo.recherche(motifTitre, statut, etiquettes);
					} else {
						return livreRepo.recherche(motifTitre, statut);
					}
				} else {
					if (!etiquettes.isEmpty()) {
						return livreRepo.recherche(motifTitre, etiquettes);
					} else {
						return livreRepo.recherche(motifTitre);
					}
				}
			}
		} else {
			if(!motifAuteur.isEmpty()) {
				if(statut != null) {
					if(!etiquettes.isEmpty()) {
						return livreRepo.recherche(motifAuteur, statut, etiquettes);
					} else {
						return livreRepo.recherche(motifAuteur, statut);
					}
				} else {
					if (!etiquettes.isEmpty()) {
						return livreRepo.recherche(motifAuteur, etiquettes);
					} else {
						return livreRepo.recherche(motifAuteur);
					}
				}
			} else {
				if(statut != null) {
					if(!etiquettes.isEmpty()) {
						return livreRepo.recherche(statut, etiquettes);
					} else {
						return livreRepo.recherche(statut);
					}
				} else {
					if (!etiquettes.isEmpty()) {
						return livreRepo.recherche(etiquettes);
					} else {
						return livreRepo.findAll();
					}
				}
			}
		}
	// fin de la fonction
	}
	
	public void delete(Livre livre) {
		livreRepo.delete(livre);
	}
	
	public void deleteById(Integer id) {
		livreRepo.delete(getById(id));
	}
	
	public void deleteAll() {
		livreRepo.deleteAll();
	}
	
	
	
	public Set<Emprunt> getEmpruntsById(int id){
		Livre livre = livreRepo.findByIdFetchEmprunts(id).orElseThrow(() -> {
			throw new LivreException("id inconnu");
		});
		return livre.getEmprunts();
	}
	
	public Set<Emprunt> getEmprunts(Livre livre){
		return getEmpruntsById(livre.getId());
	}
	
	public Emprunt getEmpruntActif(Livre livre) {
		//TODO lever une execption si le set est vide (pas d'emprunt actif)
		Emprunt emprunt = getEmprunts(livre).stream().filter(emp -> !emp.isRendu()).findFirst().get();
		return emprunt;
		
	}
	
	public void createOrUpdate(Livre livre) {
		//TODO validation, le nom doit être non nul et non vide
		//TODO le statut doit être non nul

//		Set<ConstraintViolation<Livre>> violations = validator.validate(livre);
//		if (!violations.isEmpty()) {
//			throw new LivreException();
//		}
		livreRepo.save(livre);
	}

	
	
	
	
	
	


}
