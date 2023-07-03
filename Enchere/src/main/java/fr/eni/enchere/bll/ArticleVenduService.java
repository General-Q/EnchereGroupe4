package fr.eni.enchere.bll;

import java.security.Principal;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;
import jakarta.validation.Valid;

public interface ArticleVenduService {
	List<ArticleVendu> consulterAV();
	void ajoutArticle(ArticleVendu articleVendu, Retrait retrait, Principal principal);
	ArticleVendu findById(Integer id);
	Retrait getRetrait();
	
}
