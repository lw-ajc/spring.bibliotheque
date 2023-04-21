package ajc.formation.spring.bibliotheque.entities;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="adherent")
@AttributeOverride(name = "id", column = @Column(name = "adherent_id"))
@AttributeOverride(name = "nom", column = @Column(name = "adherent_nom"))
@AttributeOverride(name = "prenom", column = @Column(name = "adherent_prenom"))
@AttributeOverride(name = "login", column = @Column(name = "adherent_login"))
@AttributeOverride(name = "password", column = @Column(name = "adherent_password"))
@AttributeOverride(name = "role", column = @Column(name = "role"))
public class Adherent extends Utilisateur{
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
		super(nom, prenom);
	}
	
}
