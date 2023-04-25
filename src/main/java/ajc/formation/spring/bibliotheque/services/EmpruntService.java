package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.exceptions.EmpruntException;
import ajc.formation.spring.bibliotheque.repositories.AdherentRepository;
import ajc.formation.spring.bibliotheque.repositories.EmpruntRepository;

@Service
public class EmpruntService {
	
	@Autowired
	private EmpruntRepository empruntRepo;
	
	@Autowired
	private AdherentRepository adRepo;
	
	public List<Emprunt> getAll(){
		return empruntRepo.findAll();
	}
	
	public Emprunt getById(Long id) {
		if (id == null) {
			throw new EmpruntException("Id obligatoire");
		}
		return empruntRepo.findById(id).orElseThrow(() -> {
			throw new EmpruntException("Id d'emprunt inconnu");
		});
	}
	
	public List<Emprunt> getByEmprunteur(Long id) {
		Adherent adherent = adRepo.findById(id).orElseThrow(() -> {
			throw new EmpruntException("Id de l'emprunteur inconnu");
		});
		return empruntRepo.findByEmprunteur(adherent);
	}
	
	public void delete(Emprunt emprunt) {
		deleteById(emprunt.getId());
	}
	
	public void deleteById (Long id) {
		empruntRepo.delete(getById(id));
	}
	
	public void deleteByEmprunteur (Adherent emprunteur) {
		empruntRepo.deleteByEmprunteur(emprunteur);
	}
	
	
	public void createOrUpdate(Emprunt emprunt) {
		//TODO valider un emprunt seulement si le livre est disponible
//		if (emprunt.getLivre()==null) {
//			throw new EmpruntException("Un livre doit être renseigné pour créer l'emprunt");
//		}
//		if (emprunt.getEmprunteur()==null) {
//			throw new EmpruntException("Un emprunteur doit être renseigné pour créer l'emprunt");
//		}
//		if (emprunt.getDateDebut()!=LocalDate.now()) {
//			throw new EmpruntException("La date date du début d'emprunt doit être aujourd'hui");
//		}
//		if (emprunt.getDateDebut()!=(LocalDate.now().plusDays(21))) {
//			throw new EmpruntException("La date date de retour ne peut pas excéder 3 semaines");
//		}
		empruntRepo.save(emprunt);
	}
	
}
