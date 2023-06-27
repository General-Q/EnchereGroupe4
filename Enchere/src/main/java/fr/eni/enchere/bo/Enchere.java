package fr.eni.enchere.bo;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class Enchere {

	// Propriétés d'une Enchère
	@NotNull(message="Merci de saisir une date valide")
	private Date dateEnchere;
	
	@NotNull(message="Merci de saisir un montant")
	private Integer montant_enchere;
	
	
	// Associations
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	
	// Constructeur vide
	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	//Constructeur sans associations
	public Enchere(Date dateEnchere, Integer montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	
	// Constructeur avec associations
	public Enchere(Date dateEnchere, Integer montant_enchere, Utilisateur utilisateur,
			ArticleVendu articleVendu) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
	}

	// Getters et Setters
	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	// Autres méthodes
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", utilisateur="
				+ utilisateur + ", articleVendu=" + articleVendu + "]";
	}
	
	
	
	
	
	
}
