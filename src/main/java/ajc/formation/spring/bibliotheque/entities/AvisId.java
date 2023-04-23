package ajc.formation.spring.bibliotheque.entities;

import java.io.Serializable;


public class AvisId implements Serializable{
	private Long adherentId;
	private Long livreId;
	
	
	
	public AvisId() {
		super();
	}



	public AvisId(Long adherentId, Long livreId) {
		super();
		this.adherentId = adherentId;
		this.livreId = livreId;
	}
	
	public AvisId(Long adherentId, int livreId) {
		super();
		this.adherentId = adherentId;
		this.livreId = Long.valueOf(livreId);
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
	
	
	
}
