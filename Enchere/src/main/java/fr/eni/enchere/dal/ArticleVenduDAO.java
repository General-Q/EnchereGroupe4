package fr.eni.enchere.dal;

import java.security.Principal;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;

public interface ArticleVenduDAO {

		List<ArticleVendu> findAll();
		void ajoutArticle(ArticleVendu articleVendu, Retrait retrait, Principal principal);
		ArticleVendu findById(Integer id);
		List<ArticleVendu> findByCat(Integer noC);
}
