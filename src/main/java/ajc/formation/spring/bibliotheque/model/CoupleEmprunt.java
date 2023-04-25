package ajc.formation.spring.bibliotheque.model;

public class CoupleEmprunt {
	private int adherentId;
	private int livreId;
	
	public CoupleEmprunt(int adherentId, int livreId) {
		super();
		this.adherentId = adherentId;
		this.livreId = livreId;
	}
	public int getAdherentId() {
		return adherentId;
	}
	public void setAdherentId(int adherentId) {
		this.adherentId = adherentId;
	}
	public int getLivreId() {
		return livreId;
	}
	public void setLivreId(int livreId) {
		this.livreId = livreId;
	}
	
	

}
