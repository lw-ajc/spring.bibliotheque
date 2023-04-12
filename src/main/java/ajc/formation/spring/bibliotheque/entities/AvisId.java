package ajc.formation.spring.bibliotheque.entities;

import java.io.Serializable;


public class AvisId implements Serializable{
	private Adherent adherent;
	private Livre livre;
	
	
	
	public AvisId() {
		super();
	}
	public AvisId(Adherent adherent, Livre livre) {
		super();
		this.adherent = adherent;
		this.livre = livre;
	}
	public Adherent getAdherent() {
		return adherent;
	}
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	
	
	
}
