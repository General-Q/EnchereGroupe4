package fr.eni.enchere.bo;

import org.springframework.jdbc.core.RowMapper;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Retrait {

	// Propriétés de Retrait
	@NotNull(message="Merci de saisir une adresse")
	@Size(max=30)
	private String rue;
	
	@NotNull(message="Merci de saisir un code postal")
	@Size(max=15)
	private String code_postal;
	
	@NotNull(message="Merci de saisir une ville")
	@Size(max=30)
	private String ville;
	
	// Associations
	private ArticleVendu articleVendu;
	
	// Constructeur vide
	public Retrait() {
		// TODO Auto-generated constructor stub
	}

	// Constructeur sans associations
	public Retrait(String rue, String code_postal, String ville) {
		super();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	// Constructeur avec associations
	public Retrait(String rue, String code_postal, String ville, ArticleVendu articleVendu) {
		super();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.setArticleVendu(articleVendu);
	}
	
	public Retrait(Integer no_article) {
		// TODO Auto-generated constructor stub
	}

	// Getters et Setters	
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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
		return "Retrait [rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + "]";
	}


	
}
