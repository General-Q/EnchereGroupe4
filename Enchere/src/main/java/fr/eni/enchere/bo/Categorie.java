package fr.eni.enchere.bo;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Categorie {

	// Id = no_categorie
	private Integer no_categorie;
	
	// Autres propriétés de la Catégorie
	@NotNull(message="Merci de saisir un libelle")
	@Size(max=30)
	private String libelle;
	
	// Associations
	private List<ArticleVendu> articlesVendus;
	
	// Constructeur vide
	public Categorie() {
		// TODO Auto-generated constructor stub
	}


	// Constructeur sans associations
	public Categorie(Integer no_categorie,
			@NotNull(message = "Merci de saisir un libelle") @Size(max = 30) String libelle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}
	
	//Constructeur avec associations
	public Categorie(Integer no_categorie,
			@NotNull(message = "Merci de saisir un libelle") @Size(max = 30) String libelle,
			List<ArticleVendu> articlesVendus) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
		this.articlesVendus = articlesVendus;
	}
	

	public Integer getno_categorie() {
		return no_categorie;
	}

	public void setno_categorie(Integer no_categorie) {
		this.no_categorie = no_categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
}
