package fr.eni.enchere.bo;

import java.util.List;
import java.util.Objects;

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
	
	// Getters et Setters
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

	public List<ArticleVendu> getArticlesVendus() {
		return articlesVendus;
	}

	public void setArticlesVendus(List<ArticleVendu> articlesVendus) {
		this.articlesVendus = articlesVendus;
	}

	// Autres méthodes
	@Override
	public String toString() {
		return "Categorie [no_categorie=" + no_categorie + ", libelle=" + libelle + ", articlesVendus=" + articlesVendus
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(no_categorie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		return Objects.equals(no_categorie, other.no_categorie);
	}
}
