package ajc.formation.spring.bibliotheque.entities;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@MappedSuperclass
public class Utilisateur implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nom", length = 255)
	private String nom;
	@Column(name = "prenom", length = 255)
	private String prenom;
	
	@Column(name = "login", nullable = false, unique = true)
	private String login;
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
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
	
	// Utilisateur
		// -> admin
		// -> adhérent
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//new SimpleGrantedAuthority("ROLE_"+this.getClass().getSimpleName());
		return Arrays.asList(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}