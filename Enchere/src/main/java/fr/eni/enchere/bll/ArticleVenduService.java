package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import jakarta.validation.Valid;

public interface ArticleVenduService {
	List<ArticleVendu> consulterAV();
	void ajoutArticle(ArticleVendu articleVendu);
	ArticleVendu findById(Integer id);
	
}
