package ajc.formation.spring.bibliotheque.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="adherent")
public class Adherent implements Utilisateur{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nomUtilisateur")
	private String nomUtilisateur;
	@Column(name="motDePasse")
	private String motDePasse;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	
	@Transient
	//@OneToMany(mappedBy = "adherent")
	private List<Emprunt> emprunts;
	
	@Transient
	//@OneToMany(mappedBy = "adherent")
	private List<Livre> favoris;
	
	@Transient
	//@OneToMany(mappedBy = "adherent")
	private List<Avis> listeAvis;
	
	
	public Adherent() {		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	public List<Livre> getFavoris() {
		return favoris;
	}

	public void setFavoris(List<Livre> favoris) {
		this.favoris = favoris;
	}

	public List<Avis> getListeAvis() {
		return listeAvis;
	}

	public void setListeAvis(List<Avis> listeAvis) {
		this.listeAvis = listeAvis;
	}

	public Adherent(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public void seConnecter() {
		System.out.println("L'adhérent se connecte.");
	}
	
	
}
