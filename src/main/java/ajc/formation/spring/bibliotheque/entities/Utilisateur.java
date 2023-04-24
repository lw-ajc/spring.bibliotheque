package ajc.formation.spring.bibliotheque.entities;


import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Utilisateur implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@JsonView(JsonViews.Simple.class)
	private Long id;
	@Column(name = "nom", length = 255)
	@JsonView(JsonViews.Simple.class)
	private String nom;
	@Column(name = "prenom", length = 255)
	@JsonView(JsonViews.Simple.class)
	private String prenom;
	// coordonnées de connexion
	@Column(name = "login", nullable = false, unique = true)
	@JsonView(JsonViews.Simple.class)
	private String login;
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	@JsonView(JsonViews.Utilisateur.class)
	private Role role;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Long id, String nom, String prenom, Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}
	
	public Utilisateur(String nom, String prenom, Role role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}
	
	public Utilisateur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.role = null;
	}
	
	public Utilisateur(String nom, String prenom, String login, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void imprimerUtilisateur() {
		System.out.println("==== utilisateur ====\n" + 
				"nom :\t" + this.nom + "\n" +
				"prénom :\t" + this.prenom + "\n" +
				"login :\t" + this.login + "\n" +
				"mdp :\t" + this.password + "\n" +
				"role :\t" + this.role + "\n");
	}

	// interface UserDetails
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.asList(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}