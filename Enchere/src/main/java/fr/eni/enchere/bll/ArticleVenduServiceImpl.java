package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.ArticleVenduDAO;

@Service
@Primary
public class ArticleVenduServiceImpl implements ArticleVenduService{
	private ArticleVenduDAO articleVenduDAO;

	public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
		this.articleVenduDAO = articleVenduDAO;
	}
	
	public List<ArticleVendu> consulterAV(){
		List<ArticleVendu> articlesVendus = articleVenduDAO.findAll();
		return articlesVendus;
	}
	
	public void ajoutArticle(ArticleVendu articleVendu) {
		articleVenduDAO.ajoutArticle(articleVendu);
	}

}
