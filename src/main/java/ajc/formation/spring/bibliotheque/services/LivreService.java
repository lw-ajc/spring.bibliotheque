package ajc.formation.spring.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.spring.bibliotheque.entities.Livre;
import ajc.formation.spring.bibliotheque.exceptions.LivreException;
import ajc.formation.spring.bibliotheque.repositories.LivreRepository;




@Service
public class LivreService {
	
//	@Autowired
//	private Validator validator;
	
	@Autowired
	private LivreRepository livreRepo;
	
	public List<Livre> getAll() {
		return livreRepo.findAll();
	}
	
	public Livre getById(Long id) {
		if (id == null) {
			throw new LivreException("id obligatoire"); //TODO créer une exception spéciale
		}
		return livreRepo.findById(id).orElseThrow(() -> {
			throw new LivreException("id inconnu");
		});
	}
	
	public void deleteById(Long id) {
		livreRepo.delete(getById(id));
	}
	
	public void deleteAll() {
		livreRepo.deleteAll();
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
