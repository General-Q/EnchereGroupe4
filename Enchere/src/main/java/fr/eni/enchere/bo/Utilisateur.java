package fr.eni.enchere.bo;

import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Utilisateur {
	
	// Id = no_utilisateur
	private Long no_utilisateur;
	
	// Autres propriétés de l'utilisateur
	@NotBlank(message = "Merci de saisir un pseudo valide")
	@Size(max=30)
	private String pseudo;
	
	@NotNull(message="Merci de saisir un nom")
	@Size(max=30)
	private String nom;
	
	@NotNull(message="Merci de saisir un prenom")
	@Size(max=30)
	private String prenom;
	
	@NotBlank(message = "Merci de saisir un email valide")
	@Size(max=20)
	private String email;
	
	@Size(max=15)
	private String telephone;
	
	@NotNull(message="Merci de saisir une adresse")
	@Size(max=30)
	private String rue;
	
	@NotNull(message="Merci de saisir un code postal")
	@Size(max=10)
	private String code_postal;
	
	@NotNull(message="Merci de saisir une ville")
	@Size(max=30)
	private String ville;
	
	@NotNull(message="Merci de saisir un mot de passe valide")
	@Size(max=30)
	private String mot_de_passe;
	
	@NotNull(message="Merci de saisir un montant")
	private Integer credit = 0;
	
	@NotNull
	private Boolean administrateur;
	
	// Associations
	private List<ArticleVendu> articlesVendus;
	private List<Enchere> encheres;
	

	//Constructeur vide
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}

	// Constructeur sans association
	public Utilisateur(Long no_utilisateur,
			@NotBlank(message = "Merci de saisir un pseudo valide") @Size(max = 30) String pseudo,
			@NotNull(message = "Merci de saisir un nom") @Size(max = 30) String nom,
			@NotNull(message = "Merci de saisir un prenom") @Size(max = 30) String prenom,
			@NotBlank(message = "Merci de saisir un email valide") @Size(max = 20) String email,
			@Size(max = 15) String telephone,
			@NotNull(message = "Merci de saisir une adresse") @Size(max = 30) String rue,
			@NotNull(message = "Merci de saisir un code postal") @Size(max = 10) String code_postal,
			@NotNull(message = "Merci de saisir une ville") @Size(max = 30) String ville,
			@NotNull(message = "Merci de saisir un mot de passe valide") @Size(max = 30) String mot_de_passe,
			@NotNull(message = "Merci de saisir un montant") Integer credit) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
	}
	
	// Constructeur avec associations
	public Utilisateur(Long no_utilisateur,
			@NotBlank(message = "Merci de saisir un pseudo valide") @Size(max = 30) String pseudo,
			@NotNull(message = "Merci de saisir un nom") @Size(max = 30) String nom,
			@NotNull(message = "Merci de saisir un prenom") @Size(max = 30) String prenom,
			@NotBlank(message = "Merci de saisir un email valide") @Size(max = 20) String email,
			@Size(max = 15) String telephone,
			@NotNull(message = "Merci de saisir une adresse") @Size(max = 30) String rue,
			@NotNull(message = "Merci de saisir un code postal") @Size(max = 10) String code_postal,
			@NotNull(message = "Merci de saisir une ville") @Size(max = 30) String ville,
			@NotNull(message = "Merci de saisir un mot de passe valide") @Size(max = 30) String mot_de_passe,
			@NotNull(message = "Merci de saisir un montant") Integer credit, @NotNull Boolean administrateur,
			List<ArticleVendu> articlesVendus, List<Enchere> encheres) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articlesVendus = articlesVendus;
		this.encheres = encheres;
	}

	// Getters et Setters
	public Long getno_utilisateur() {
		return no_utilisateur;
	}

	public void setno_utilisateur(Long no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
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

	public String getcode_postal() {
		return code_postal;
	}

	public void setcode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getmot_de_passe() {
		return mot_de_passe;
	}

	public void setmot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
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
				+ code_postal + ", ville=" + ville;
	}

	@Override
	public int hashCode() {
		return Objects.hash(no_utilisateur);
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
		return Objects.equals(no_utilisateur, other.no_utilisateur);
	}
	
	
}
