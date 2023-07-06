package fr.eni.enchere.bo;

import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Utilisateur {
	
	public static final boolean MEMBRE = false;
	public static final boolean ADMINISTRATEUR = true;
	
	// Id = no_utilisateur
	private Integer noUtilisateur;
	
	// Autres propriétés de l'utilisateur
	@NotBlank
	@Size(max=30)
	@Pattern(regexp = "^[a-zA-Z0-9]+")
	private String pseudo;
	
	@NotNull
	@Size(max=30)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String nom;
	
	@NotNull
	@Size(max=30)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String prenom;
	
	@NotBlank
	@Size(max=20)
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
	private String email;
	
	@NotNull
	@Size(max=15)
	@Pattern(regexp = "^[0-9]{10}")
	private String telephone;
	
	@NotNull
	@Size(max=30)
	private String rue;
	
	@NotNull
	@Size(max=10)
	@Pattern(regexp = "^[0-9]{5}")
	private String codePostal;
	
	@NotNull
	@Size(max=30)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String ville;
	
	@NotNull
	@Size(max=30)
	@Size(min=6)
	private String motDePasse;
	
	private Integer credit;
	
	private Boolean administrateur;
	
	// Associations
	private List<ArticleVendu> articlesVendus;
	private List<Enchere> encheres;
	
	public Utilisateur() {
	// TODO Auto-generated constructor stub
	}
	//Constructeur vide
	public Utilisateur(Integer credit, @NotNull Boolean administrateur) {
		this.credit=credit;
		this.administrateur=MEMBRE;
	}

	// Constructeur sans association
	public Utilisateur(Integer noUtilisateur,String pseudo,String nom,String prenom,String email,String telephone,
			String rue,String codePostal,String ville,String motDePasse,Integer credit, Boolean administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	// Constructeur avec associations
	public Utilisateur(Integer noUtilisateur,String pseudo,String nom,String prenom,String email,String telephone,
			String rue,String codePostal,String ville,String motDePasse,Integer credit, Boolean administrateur,
			List<ArticleVendu> articlesVendus, List<Enchere> encheres) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articlesVendus = articlesVendus;
		this.encheres = encheres;
	}

	public Utilisateur(String pseudo) {
		this.pseudo=pseudo;
	}

	public Utilisateur(Integer id) {
		this.noUtilisateur=id;
	}

	// Getters et Setters
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Integer i) {
		this.noUtilisateur = i;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Boolean getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}

	public List<ArticleVendu> getArticlesVendus() {
		return articlesVendus;
	}

	public void setArticlesVendus(List<ArticleVendu> articlesVendus) {
		this.articlesVendus = articlesVendus;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	// Autres méthodes
	@Override
	public String toString() {
		return "Utilisateur [pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", code postal="
				+ codePostal + ", ville=" + ville;
	}

	@Override
	public int hashCode() {
		return Objects.hash(noUtilisateur);
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
		return Objects.equals(noUtilisateur, other.noUtilisateur);
	}
	
	
}
