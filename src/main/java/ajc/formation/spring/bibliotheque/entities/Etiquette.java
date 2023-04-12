package ajc.formation.spring.bibliotheque.entities;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "etiquette")
@Entity
public class Etiquette {
	@Id
	private String nom;
	@Transient
	private Etiquette parent;
	@Transient
	private Etiquette cadet;
	@ManyToMany(mappedBy = "etiquettes")
	private Set<Livre> livres = new HashSet();
	
	
	
	
	public Etiquette() {
		super();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Etiquette getParent() {
		return parent;
	}
	public void setParent(Etiquette parent) {
		this.parent = parent;
	}
	public Etiquette getCadet() {
		return cadet;
	}
	public void setCadet(Etiquette cadet) {
		this.cadet = cadet;
	}
	public Set<Livre> getLivres() {
		return livres;
	}
	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}
	@Override
	public int hashCode() {
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
		return nom == other.nom;
	}
	
	
	
	
	
}
