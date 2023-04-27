package ajc.formation.spring.bibliotheque.model;

import java.util.Set;

import ajc.formation.spring.bibliotheque.entities.StatutLivre;

public class Recherche {
	private String motifTitre;
	private String motifAuteur;
	private StatutLivre statut;
	private Set<String> etiquettes;
	
	public Recherche() {
		
	}

	public String getMotifTitre() {
		return motifTitre;
	}

	public void setMotifTitre(String motifTitre) {
		this.motifTitre = motifTitre;
	}

	public String getMotifAuteur() {
		return motifAuteur;
	}

	public void setMotifAuteur(String motifAuteur) {
		this.motifAuteur = motifAuteur;
	}

	public StatutLivre getStatut() {
		return statut;
	}

	public void setStatut(StatutLivre statut) {
		this.statut = statut;
	}

	public Set<String> getEtiquettes() {
		return etiquettes;
	}

	public void setEtiquettes(Set<String> etiquettes) {
		this.etiquettes = etiquettes;
	}

	

	
	

}
