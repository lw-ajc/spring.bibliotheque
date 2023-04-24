package ajc.formation.spring.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Emprunt;
import ajc.formation.spring.bibliotheque.services.EmpruntService;

@SpringBootTest
public class EmpruntTest {
	
	@Autowired
	EmpruntService empruntSrv;

	@Test
	@Commit
	void ajouterEmprunt() {
		Emprunt emprunt = new Emprunt();
		empruntSrv.createOrUpdate(emprunt);
		assertNotNull(emprunt.getId());
	}

}
