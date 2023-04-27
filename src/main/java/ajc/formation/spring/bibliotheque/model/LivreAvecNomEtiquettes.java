package ajc.formation.spring.bibliotheque.model;

import java.util.List;

import ajc.formation.spring.bibliotheque.entities.StatutLivre;

public class LivreAvecNomEtiquettes {
	private String titre;
	private String auteur;
	private  StatutLivre statut;
	private List<String> etiquettes;
	
	public LivreAvecNomEtiquettes() {
		super();
	}
	public LivreAvecNomEtiquettes(String titre, String auteur, StatutLivre statut, List<String> etiquettes) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.statut = statut;
		this.etiquettes = etiquettes;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public StatutLivre getStatut() {
		return statut;
	}
	public void setStatut(StatutLivre statut) {
		this.statut = statut;
	}
	public List<String> getEtiquettes() {
		return etiquettes;
	}
	public void setEtiquettes(List<String> etiquettes) {
		this.etiquettes = etiquettes;
	}
	
	

}
