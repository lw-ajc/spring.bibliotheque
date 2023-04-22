package ajc.formation.spring.bibliotheque.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;

@Entity
@Table(name ="livre")
public class Livre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Simple.class)
	private int livreId;
	@Column(name = "titre", nullable = false, length = 255)
	@JsonView(JsonViews.Simple.class)
	private String titre;
	@Column(name = "auteur", nullable = false, length = 255)
	@JsonView(JsonViews.Simple.class)
	private String auteur;
	
	@Column(name = "statut", nullable = false)
	@Enumerated(EnumType.STRING)
	@JsonView(JsonViews.Simple.class)
	private StatutLivre statut;
	
	@ManyToMany
	@JoinTable(
			name = "etiquette_livre",
			joinColumns = @JoinColumn(name  ="livre_id"),
			inverseJoinColumns = @JoinColumn(name = "etiquette_id"))
	Set<Etiquette> etiquettes = new HashSet<Etiquette>();
	
	@OneToMany(mappedBy = "livre")
	Set<Emprunt> emprunts = new HashSet<Emprunt>();
	
	//relation favori
	//relation avis
		

	public Livre() {
		super();
	}
	
	public Livre(String titre, String auteur, StatutLivre statut) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.statut = statut;
	}
	
	public Livre(String titre, String auteur) {
		super();
		this.titre = titre;
		this.auteur = auteur;
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

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	
	
	
	
	
	
}
