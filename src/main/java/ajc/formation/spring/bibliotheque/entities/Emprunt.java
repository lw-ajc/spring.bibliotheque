package ajc.formation.spring.bibliotheque.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="emprunt")
public class Emprunt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_emprunt")
	private Long id;
	@Column (name="date_debut_emprunt")
	private LocalDate dateDebut;
	@Column (name="date_fin_emprunt")
	private LocalDate dateFin;
	@Column (name="rendu")
	private boolean rendu;
	@ManyToOne
	@JoinColumn(name="id_emprunteur_emprunt", foreignKey = @ForeignKey(name="id_emprunteur_emprunt_fk"))
	private Adherent emprunteur;
	@ManyToOne
	@JoinColumn(name="id_livre_emprunt", foreignKey = @ForeignKey(name="id_livre_emprunt_fk"))
	private Livre livre;
	
	public Emprunt() {
		super();
	}

	public Emprunt(LocalDate dateFin, LocalDate dateDebut, Adherent emprunteur,Livre livre) {
		super();
		this.dateFin = dateFin;
		this.dateDebut = LocalDate.now();
		this.emprunteur = emprunteur;
		this.livre=livre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Adherent emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}


	public boolean isRendu() {
		return rendu;
	}

	public void setRendu(boolean rendu) {
		this.rendu = rendu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprunt other = (Emprunt) obj;
		return Objects.equals(id, other.id);
	}
	
	

}