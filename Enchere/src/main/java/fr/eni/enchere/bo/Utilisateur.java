package fr.eni.enchere.bo;

import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Utilisateur {
	
	// Id = no_utilisateur
	private Long noUtilisateur;
	
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
	private String codePostal;
	
	@NotNull(message="Merci de saisir une ville")
	@Size(max=30)
	private String ville;
	
	@NotNull(message="Merci de saisir un mot de passe valide")
	@Size(max=30)
	private String motDePasse;
	
	@NotNull(message="Merci de saisir un mot de passe valide")
	@Size(max=30)
	private String confirmMotDePasse;
	
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
	public Utilisateur(Long noUtilisateur,
			@NotBlank(message = "Merci de saisir un pseudo valide") @Size(max = 30) String pseudo,
			@NotNull(message = "Merci de saisir un nom") @Size(max = 30) String nom,
			@NotNull(message = "Merci de saisir un prenom") @Size(max = 30) String prenom,
			@NotBlank(message = "Merci de saisir un email valide") @Size(max = 20) String email,
			@Size(max = 15) String telephone,
			@NotNull(message = "Merci de saisir une adresse") @Size(max = 30) String rue,
			@NotNull(message = "Merci de saisir un code postal") @Size(max = 10) String codePostal,
			@NotNull(message = "Merci de saisir une ville") @Size(max = 30) String ville,
			@NotNull(message = "Merci de saisir un mot de passe valide") @Size(max = 30) String motDePasse,
			@NotNull(message = "Merci de saisir un mot de passe valide") @Size(max = 30) String confirmMotDePasse,
			@NotNull(message = "Merci de saisir un montant") Integer credit, @NotNull Boolean administrateur) {
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
		this.confirmMotDePasse = confirmMotDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	// Constructeur avec associations
	public Utilisateur(Long noUtilisateur,
			@NotBlank(message = "Merci de saisir un pseudo valide") @Size(max = 30) String pseudo,
			@NotNull(message = "Merci de saisir un nom") @Size(max = 30) String nom,
			@NotNull(message = "Merci de saisir un prenom") @Size(max = 30) String prenom,
			@NotBlank(message = "Merci de saisir un email valide") @Size(max = 20) String email,
			@Size(max = 15) String telephone,
			@NotNull(message = "Merci de saisir une adresse") @Size(max = 30) String rue,
			@NotNull(message = "Merci de saisir un code postal") @Size(max = 10) String codePostal,
			@NotNull(message = "Merci de saisir une ville") @Size(max = 30) String ville,
			@NotNull(message = "Merci de saisir un mot de passe valide") @Size(max = 30) String motDePasse,
			@NotNull(message = "Merci de saisir un montant") Integer credit, @NotNull Boolean administrateur,
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
		this.noUtilisateur=(long)id;
	}

	// Getters et Setters
	public Long getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Long noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
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
	
	
	public String getConfirmMotDePasse() {
		return confirmMotDePasse;
	}

	public void setConfirmMotDePasse(String confirmMotDePasse) {
		this.confirmMotDePasse = confirmMotDePasse;
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
