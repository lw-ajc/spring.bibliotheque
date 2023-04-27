package ajc.formation.spring.bibliotheque.entities;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;

@Table(name = "etiquette")
@Entity
public class Etiquette {
	@Id
	@JsonView(JsonViews.Simple.class)
	private String nom;
	@ManyToOne
	@JoinColumn(name = "parent")
	@JsonView(JsonViews.EtiquetteWithParentEnfants.class)
	private Etiquette parent;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonView(JsonViews.EtiquetteWithParentEnfants.class)
	private Set<Etiquette> enfants = new HashSet<Etiquette>();
	@ManyToMany(mappedBy = "etiquettes")
	@JsonView(JsonViews.EtiquetteWithParentEnfantsLivres.class)
	private Set<Livre> livres = new HashSet<Livre>();
	

	public Etiquette() {
		super();
	}
	
	public Etiquette(String nom) {
		super();
		this.nom = nom;
	}
	
	public Etiquette(String nom, Etiquette parent) {
		this(nom);
		this.parent = parent;
	}
	
	public Etiquette getParent() {
		return parent;
	}

	public void setParent(Etiquette parent) {
		this.parent = parent;
	}
	

	public Set<Etiquette> getEnfants() {
		return enfants;
	}

	public void setEnfants(Set<Etiquette> enfants) {
		this.enfants = enfants;
	}
	
	public void addEnfant(Etiquette enfant) {
		this.enfants.add(enfant);
	}
	public void removeEnfant(Etiquette enfant) {
		this.enfants.remove(enfant);
	}
	public boolean removeEnfant(String nomEnfant) {
		// boucle au ca
		return this.enfants.remove(new Etiquette(nomEnfant, (Etiquette) null));
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Set<Livre> getLivres() {
		return livres;
	}
	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}
	
	
	public void imprimer() {
		System.out.println("=============> " + this.nom + "<=============");
		if (this.parent == null)
			System.out.println("=============> " + "PAS DE PARENT" + "<=============");
		else
			System.out.println("=============> " + this.parent.getNom() + "<=============");
	}
	@Override
	public int hashCode() {
		// Note : pour ce qui nous concerne, les enfants et le parent ne participent pas à définir l'identité d'une étiquette
		return Objects.hash(nom);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etiquette other = (Etiquette) obj;
		return nom.equals(other.nom);
	}


	
	
	
	
	
}
