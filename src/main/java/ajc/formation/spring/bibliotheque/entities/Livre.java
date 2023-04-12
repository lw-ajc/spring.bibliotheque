package ajc.formation.spring.bibliotheque.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name ="livre")
public class Livre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int livreId;
	@Column(name = "titre", nullable = false, length = 255)
	private String titre;
	@Column(name = "statut", nullable = false)
	private StatutLivre statut;
	
	@ManyToMany
	@JoinTable(
			name = "etiquette_livre",
			joinColumns = @JoinColumn(name  ="livre_id"),
			inverseJoinColumns = @JoinColumn(name = "etiquette_id"))
	Set<Etiquette> etiquettes = new HashSet();

	public Livre() {
		super();
	}
	
	public Livre(int livreId, String titre, StatutLivre statut) {
		super();
		this.livreId = livreId;
		this.titre = titre;
		this.statut = statut;
	}
	
	public Livre(String titre, StatutLivre statut) {
		super();
		this.titre = titre;
		this.statut = statut;
	}

	public int getLivreId() {
		return livreId;
	}

	public void setLivreId(int livreId) {
		this.livreId = livreId;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public StatutLivre getStatut() {
		return statut;
	}

	public void setStatut(StatutLivre statut) {
		this.statut = statut;
	}
	
	
	
	//relation favori
	//relation avis
	//relation emprunt
	
	
	
	
}
