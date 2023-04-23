package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Avis;
import ajc.formation.spring.bibliotheque.entities.AvisId;
import ajc.formation.spring.bibliotheque.exceptions.AvisException;
import ajc.formation.spring.bibliotheque.repositories.AvisRepository;

@Service
public class AvisService {
	
	@Autowired
	private AvisRepository avisRepo;
	
	
	public List<Avis> getAll() {
		return avisRepo.findAll();
	}
	
//	public Adherent getById(Long id) {
//		if (id == null) {
//			throw new AdherentException("id obligatoire");
//		}
//		return adherentRepo.findById(id).orElseThrow(() -> {
//			throw new AdherentException("id inconnu");
//		});
//	}
	
	public Avis getById(AvisId avis) {
		return avisRepo.findById(avis).orElseThrow(() -> {
			throw new AvisException("id de l'avis inconnu");
		});
	}
	
	public void createOrUpdate(Avis avis) {
		avisRepo.save(avis);
	}

}
