package fr.eni.enchere.bo;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class Enchere {

	// Propriétés d'une Enchère
	@NotNull(message="Merci de saisir une date valide")
	private Date dateEnchere;
	
	@NotNull(message="Merci de saisir un montant")
	private Integer montant_enchere;
	private Integer noArticleVendu;	
	
	// Associations

	
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
	public Enchere(Date dateEnchere, Integer montant_enchere, ArticleVendu articleVendu) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.noArticleVendu = articleVendu.getNo_article();
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

	public Integer getNoArticleVendu() {
		return noArticleVendu;
	}

	public void setNoArticleVendu(Integer noArticleVendu) {
		this.noArticleVendu = noArticleVendu;
	}

	// Autres méthodes
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", utilisateur="
				+ utilisateur + ", articleVendu=" + articleVendu + "]";
	}
	
	
	
	
	
	
}
