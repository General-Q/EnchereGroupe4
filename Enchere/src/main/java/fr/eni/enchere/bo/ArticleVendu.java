package fr.eni.enchere.bo;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.context.annotation.Primary;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ArticleVendu {
	
	// Id = no_article
	private Integer no_article;
	
	// Autres propriétés de l'article
	@NotNull(message = "Merci de saisir un nom d'article")
	@Size(max=30)
	private String nom_article;
	
	@NotNull(message = "Merci de saisir une description d'article")
	@Size(max=300)
	private String description;
	
	@NotNull(message = "Merci de saisir une date valide")
	private Date date_debut_encheres;
	
	@NotNull(message = "Merci de saisir une date valide")
	private Date date_fin_encheres;
	
	private Integer prix_initial;
	private Integer prix_vente;
	
	// Associations
	private List<Enchere>encheres;
	private Integer noUtilisateur;
	private Integer noCategorie;
	// TODO Categorie categorie
	private Retrait retrait;	
	
	
	
	public ArticleVendu() {
	}
	
	public ArticleVendu(Integer noArticle, Integer noCategorie) {
        super();
        this.no_article = noArticle;
        this.noCategorie = noCategorie;
    }

	public ArticleVendu(String nomE, String description, Date dateD, Date dateF, Integer prixI, Integer prixV) {
		this.nom_article = nomE;
		this.description = description;
		this.date_debut_encheres = dateD;
		this.date_fin_encheres = dateF;
		this.prix_initial = prixI;
		this.prix_vente = prixV;
	}

	// Constructeur sans association
	public ArticleVendu(Integer no_article,
			@NotNull(message = "Merci de saisir un nom d'article") @Size(max = 30) String nom_article,
			@NotNull(message = "Merci de saisir une description d'article") @Size(max = 300) String description,
			@NotNull(message = "Merci de saisir une date valide") @NotNull(message = "Merci de saisir une date valide") Date date_debut_encheres,
			@NotNull(message = "Merci de saisir une date valide") @NotNull(message = "Merci de saisir une date valide") Date date_fin_encheres, Integer prix_initial,
			Integer prix_vente) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
	}

//	// Constructeur avec associations
//	public ArticleVendu(Integer no_article,
//			@NotNull(message = "Merci de saisir un nom d'article") @Size(max = 30) String nom_article,
//			@NotNull(message = "Merci de saisir une description d'article") @Size(max = 300) String description,
//			@NotNull(message = "Merci de saisir une date valide") @NotNull(message = "Merci de saisir une date valide") Date date_debut_encheres,
//			@NotNull(message = "Merci de saisir une date valide") @NotNull(message = "Merci de saisir une date valide") Date date_fin_encheres, Integer prix_initial,
//			Integer prix_vente, Boolean etatVente, List<Enchere> encheres, Utilisateur utilisateur, Categorie categorie,
//			Retrait retrait) {
//		super();
//		this.no_article = no_article;
//		this.nom_article = nom_article;
//		this.description = description;
//		this.date_debut_encheres = date_debut_encheres;
//		this.date_fin_encheres = date_fin_encheres;
//		this.prix_initial = prix_initial;
//		this.prix_vente = prix_vente;
//		this.encheres = encheres;
//		this.utilisateur = utilisateur;
//		this.categorie = categorie;
//		this.retrait = retrait;
//	}

	// Getters et Setters
	public Integer getNo_article() {
		return no_article;
	}

	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}

	public String getNom_article() {
		return nom_article;
	}

	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_debut_encheres() {
		return date_debut_encheres;
	}

	public void setDate_debut_encheres(Date date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}

	public Date getDate_fin_encheres() {
		return date_fin_encheres;
	}

	public void setDate_fin_encheres(Date date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}

	public Integer getPrix_initial() {
		return prix_initial;
	}

	public void setPrix_initial(Integer prix_initial) {
		this.prix_initial = prix_initial;
	}

	public Integer getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(Integer prix_vente) {
		this.prix_vente = prix_vente;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Utilisateur utilisateur) {
		this.noUtilisateur = utilisateur.getNoUtilisateur();
	}
	
	public void setNoStUtil(String util) {
		Integer id = Integer.parseInt(util);
		this.noUtilisateur = id;
	}

	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer cat) {
		this.noCategorie = cat;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	// Autres méthodes
	@Override
	public String toString() {
		return "ArticleVendu [nom_article=" + nom_article + ", description=" + description + ", date_debut_encheres="
				+ date_debut_encheres + ", date_fin_encheres=" + date_fin_encheres + ", prix_initial=" + prix_initial
				+ ", prix_vente=" + prix_vente + ", utilisateur=" + noUtilisateur + ", categorie=" + noCategorie
				+ ", retrait=" + retrait + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(no_article);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleVendu other = (ArticleVendu) obj;
		return Objects.equals(no_article, other.no_article);
	}



	
}
