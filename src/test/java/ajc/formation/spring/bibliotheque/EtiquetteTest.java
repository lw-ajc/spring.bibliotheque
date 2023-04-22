package ajc.formation.spring.bibliotheque;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ajc.formation.spring.bibliotheque.entities.Etiquette;
import ajc.formation.spring.bibliotheque.services.EtiquetteService;

@SpringBootTest
public class EtiquetteTest {
	
	@Autowired
	EtiquetteService etiqSrv;
	
	@BeforeEach
	@Commit
	public void creerRacine1() {
		// La racine est réinitialisée à chaque test.
		etiqSrv.createRacine();
		Etiquette racine = etiqSrv.getByNom("racine");
	}
	
	@Test
	@Commit
	void creerEtiquette() {
		// Racine sans enfant
		Etiquette racine = etiqSrv.getByNom("racine");
		racine.imprimer();
		racine = etiqSrv.getByNomWithEnfants("racine");
		racine.imprimer();
		System.out.println("liste des enfants de la racine => " + racine.getEnfants().stream().map(x->x.getNom()).collect(Collectors.toList()));
		// Création d'une nouvelle étiquette fille immédiate de la racine
		Etiquette enfant = new Etiquette("genre littéraire", racine);
		etiqSrv.create(enfant);
		// Racine avec un premier enfant
		racine = etiqSrv.getByNomWithEnfants("racine");
		racine.imprimer();
		// Création d'une autre étiquette fille de la racine
		etiqSrv.create("genre narratif", "racine");
		racine = etiqSrv.getByNomWithEnfants("racine");
		racine.imprimer();
		System.out.println("liste des enfants de la racine => " + racine.getEnfants().stream().map(x->x.getNom()).collect(Collectors.toList()));
	}
	
	@Test
	@Commit
	void renommerEtiquette() {
		Etiquette racine = etiqSrv.getByNom("racine");
		etiqSrv.create("genre narratif", "racine");
		etiqSrv.create("science-fiction", "genre narratif");
		etiqSrv.create("polar", "genre narratif");
		etiqSrv.rename("genre narratif", "genre");
	}
	
	@Test
	@Commit
	void supprimerEtiquette() {
		Etiquette racine = etiqSrv.getByNom("racine");
		etiqSrv.create("genre littéraire", "racine");
		etiqSrv.create("genre narratif", "racine");
		etiqSrv.create("science-fiction", "genre narratif");
		etiqSrv.create("théâtre", "genre littéraire");
		etiqSrv.create("poésie", "genre littéraire");
		etiqSrv.create("vaudeville", "théâtre");
		etiqSrv.deleteByNom("genre littéraire");
	}
	
	

}
