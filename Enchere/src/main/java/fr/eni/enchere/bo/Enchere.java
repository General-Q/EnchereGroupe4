package fr.eni.enchere.bo;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class Enchere {

	// Propriétés d'une Enchère
	@NotNull(message="Merci de saisir une date valide")
	private Date dateEnchere;
	
	@NotNull(message="Merci de saisir un montant")
	private Integer montant_enchere;
	private long noUtil;
	private Integer noArticleVendu;	
	
	// Associations
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
	public Enchere(ArticleVendu articleVendu) {
		super();
		this.noUtil = articleVendu.getNoUtilisateur();
		this.dateEnchere = articleVendu.getDate_debut_encheres();
		this.montant_enchere = articleVendu.getPrix_vente();
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

	public long getNoUtil() {
		return noUtil;
	}

	public void setNoUtil(long noUtil) {
		this.noUtil = noUtil;
	}
	
	

	// Autres méthodes
//	@Override
//	public String toString() {
//		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", utilisateur="
//				+ utilisateur + ", articleVendu=" + articleVendu + "]";
//	}
	
	
	
	
	
	
}
