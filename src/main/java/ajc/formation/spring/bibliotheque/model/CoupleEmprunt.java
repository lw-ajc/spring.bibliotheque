package ajc.formation.spring.bibliotheque.model;

public class CoupleEmprunt {
	private Long adherentId;
	private int livreId;
	
	public CoupleEmprunt(Long adherentId, int livreId) {
		super();
		this.adherentId = adherentId;
		this.livreId = livreId;
	}
	public Long getAdherentId() {
		return adherentId;
	}
	public void setAdherentId(Long adherentId) {
		this.adherentId = adherentId;
	}
	public int getLivreId() {
		return livreId;
	}
	public void setLivreId(int livreId) {
		this.livreId = livreId;
	}
	
	

}
