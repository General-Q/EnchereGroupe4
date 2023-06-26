package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.ArticleVenduDAO;

@Service
@Primary
public class ArticleVenduServiceImpl implements ArticleVenduService{
	private ArticleVendu articleVendu;

	public ArticleVenduServiceImpl(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	
	public List<ArticleVendu> consulterAV(){
		List<ArticleVendu> articlesVendus = ArticleVenduDAO.findAll();
	}

}
