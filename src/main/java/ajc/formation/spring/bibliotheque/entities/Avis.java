package ajc.formation.spring.bibliotheque.entities;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;






@Entity
@Table(name = "avis")
@IdClass(AvisId.class)
public class Avis implements Serializable {
    @Id
    //@ManyToOne
    //@JoinColumn(name = "Adherent_Avis_id", foreignKey = @ForeignKey(name = "Adherent_Avis_id_fk"))
	private Long adherentId;
	@Id
//	@ManyToOne
//	@JoinColumn(name = "Livre_Avis_id", foreignKey = @ForeignKey(name = "Livre_Avis_id_fk"))
	private Long livreId;
	
	
	@Column(name="livre")
	private String commentaire;
	private int note;
	
	public Avis() {
		super();
	}

	
	public Avis(String commentaire, int note) {
		super();
		this.commentaire = commentaire;
		this.note = note;
	}


	

	public Avis(Long adherentId, Long livreId, String commentaire, int note) {
		super();
		this.adherentId = adherentId;
		this.livreId = livreId;
		this.commentaire = commentaire;
		this.note = note;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}



	public Long getAdherentId() {
		return adherentId;
	}


	public void setAdherentId(Long adherentId) {
		this.adherentId = adherentId;
	}


	public Long getLivreId() {
		return livreId;
	}


	public void setLivreId(Long livreId) {
		this.livreId = livreId;
	}


	public int getNote() {
		return note;
	}

	
	public void setNote(int note) {
		this.note = note;
	}


	@Override
	public int hashCode() {
		return Objects.hash(adherentId, livreId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avis other = (Avis) obj;
		return Objects.equals(adherentId, other.adherentId) && Objects.equals(livreId, other.livreId);
	}
	
	
	
	
	
	
	
	


}
