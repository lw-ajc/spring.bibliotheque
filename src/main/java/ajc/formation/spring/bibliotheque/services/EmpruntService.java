package ajc.formation.spring.bibliotheque.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.entities.StatutLivre;
import ajc.formation.spring.bibliotheque.exceptions.EmpruntException;
import ajc.formation.spring.bibliotheque.exceptions.LivreException;
import ajc.formation.spring.bibliotheque.repositories.AdherentRepository;
import ajc.formation.spring.bibliotheque.repositories.EmpruntRepository;

@Service
public class EmpruntService {
	
	@Autowired
	private EmpruntRepository empruntRepo;
	
	@Autowired
	private AdherentRepository adRepo;
	
	@Autowired
	private LivreService livreService;
	
	
	public List<Emprunt> getAll(){
		return empruntRepo.findAll();
	}
	
//	public Emprunt get(Emprunt emprunt) {
//		return empruntRepo.findOne(emprunt).orElseThrow(() -> {
//			throw new EmpruntException("Id d'emprunt inconnu");
//		});
//	}
	
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
		Livre livre = emprunt.getLivre();
		livre.setStatut(StatutLivre.DISPONIBLE);
		livreService.createOrUpdate(livre);
		empruntRepo.delete(emprunt);
	}
	
	public void deleteById (Long id) {
		empruntRepo.delete(getById(id));
	}
	
	public void deleteByEmprunteur (Adherent emprunteur) {
		empruntRepo.deleteByEmprunteur(emprunteur);
	}
	
	public void create(Emprunt emprunt) {
		Livre livre = emprunt.getLivre();
		if(empruntRepo.existsByLivre(livre)) {
			StatutLivre statut = livre.getStatut();
			if(statut != StatutLivre.DISPONIBLE)
				throw new EmpruntException("Impossible d'emprunter un livre déjà");
			livre.setStatut(StatutLivre.EMPRUNTE);
			livreService.createOrUpdate(livre);
		}
		empruntRepo.save(emprunt);
	}
	
	public void updateDate(Emprunt emprunt, LocalDate dateFin) {
		emprunt.setDateFin(dateFin);
		empruntRepo.save(emprunt);
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
//		if(!empruntRepo.exists(emprunt)) {
//			Livre livre = emprunt.getLivre();
//			livre.setStatut(StatutLivre.EMPRUNTE);
//			livreService.createOrUpdate(livre);
//		}
		
//		empruntRepo.exists(emprunt);
		
		
			
		empruntRepo.save(emprunt);
	}
	
}
