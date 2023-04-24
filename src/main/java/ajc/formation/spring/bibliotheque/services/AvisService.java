package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Adherent;
import ajc.formation.spring.bibliotheque.entities.Avis;
import ajc.formation.spring.bibliotheque.entities.AvisId;
import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.exceptions.AvisException;
import ajc.formation.spring.bibliotheque.repositories.AvisRepository;

@Service
public class AvisService {
	
	@Autowired
	private AvisRepository avisRepo;
	
	
	public List<Avis> getAll() {
		return avisRepo.findAll();
	}
	
	
	public Avis getById(AvisId avis) {
		return avisRepo.findById(avis).orElseThrow(() -> {
			throw new AvisException("id de l'avis inconnu");
		});
	}
	
	public Avis getByAdherentId(Long adherentId) {
		return avisRepo.findByAdherentId(adherentId).orElseThrow(() -> {
			throw new AvisException("avis inconnu");
		});
	}
	public Avis getByAdherent(Adherent adherent) {
		return getByAdherentId(adherent.getId());
	}
	
	public Avis getByLivreId(Long livreId) {
		return avisRepo.findByLivreId(livreId).orElseThrow(() -> {
			throw new AvisException("avis inconnu");
		});
	}
	public Avis getByLivre(Livre livre) {
		return getByLivreId(Long.valueOf(livre.getId()));
	}
	
	public void deleteAll() {
		avisRepo.deleteAll();
	}
	
	public void createOrUpdate(Avis avis) {
		avisRepo.save(avis);
	}


	
	
	

}
