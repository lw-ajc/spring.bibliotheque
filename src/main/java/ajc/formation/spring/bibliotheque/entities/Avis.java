package ajc.formation.spring.bibliotheque.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;





@Entity
@Table(name = "avis")
@IdClass(AvisId.class)
public class Avis implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "Adherent_Avis_id", foreignKey = @ForeignKey(name = "Adherent_Avis_id_fk"))
    @Transient
	private Adherent adherent;
	@Id
	@ManyToOne
	@JoinColumn(name = "Livre_Avis_id", foreignKey = @ForeignKey(name = "Livre_Avis_id_fk"))
	@Transient
	private Livre livre;
	@Column(name="livre")
	private String commentaire;
	private int note;
	
	
	public Avis(String commentaire, int note) {
		super();
		this.commentaire = commentaire;
		this.note = note;
	}


	public Avis() {
		super();
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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

	
	public int getNote() {
		return note;
	}

	
	public void setNote(int note) {
		this.note = note;
	}
	
	
	
	
	
	
	
	


}
