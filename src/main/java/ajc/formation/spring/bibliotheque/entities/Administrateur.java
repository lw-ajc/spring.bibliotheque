package ajc.formation.spring.bibliotheque.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrateur")
@AttributeOverride(name = "id", column = @Column(name = "admin_id"))
@AttributeOverride(name = "nom", column = @Column(name = "admin_nom"))
@AttributeOverride(name = "prenom", column = @Column(name = "admin_prenom"))
@AttributeOverride(name = "login", column = @Column(name = "admin_login"))
@AttributeOverride(name = "password", column = @Column(name = "admin_password"))
@AttributeOverride(name = "role", column = @Column(name = "role"))
public class Administrateur extends Utilisateur{
	
	@Column(name="mail")
	private String mail;
	
	
	public Administrateur() {
		
	}
	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}