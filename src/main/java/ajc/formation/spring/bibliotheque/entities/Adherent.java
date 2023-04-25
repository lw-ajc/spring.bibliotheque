package ajc.formation.spring.bibliotheque.entities;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;

@Entity
@Table(name="adherent")
@AttributeOverride(name = "id", column = @Column(name = "adherent_id"))
@AttributeOverride(name = "nom", column = @Column(name = "adherent_nom"))
@AttributeOverride(name = "prenom", column = @Column(name = "adherent_prenom"))
@AttributeOverride(name = "login", column = @Column(name = "adherent_login"))
@AttributeOverride(name = "password", column = @Column(name = "adherent_password"))
public class Adherent extends Utilisateur{
	@OneToMany(mappedBy = "emprunteur")
	@JsonView(JsonViews.AdherentWithLists.class)
	private List<Emprunt> emprunts;
	
	@Transient
//	@OneToMany(mappedBy = "adherent")
	@JsonView(JsonViews.AdherentWithLists.class)
	private List<Livre> favoris;
	
	@Transient
//	@OneToMany(mappedBy = "adherent")
	@JsonView(JsonViews.AdherentWithLists.class)
	private List<Avis> listeAvis;
	
	
	public Adherent() {
		super();
	}
	
	public Adherent(String nom, String prenom) {
		super(nom, prenom);
	}
	
	public Adherent(String nom, String prenom, String login, String password) {
		super(nom, prenom, login, password);
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


	
}
