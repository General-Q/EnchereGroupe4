package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ArticleVenduDAO;
import jakarta.validation.Valid;

@Service
@Primary
public class ArticleVenduServiceImpl implements ArticleVenduService{
	private ArticleVenduDAO articleVenduDAO;

	public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
		this.articleVenduDAO = articleVenduDAO;
	}
	
	@Override
	public List<ArticleVendu> consulterAV(){
		List<ArticleVendu> articlesVendus = articleVenduDAO.findAll();
		return articlesVendus;
	}
	
	@Override
	public void ajoutArticle(ArticleVendu articleVendu) {
		articleVenduDAO.ajoutArticle(articleVendu);
	}
	
	@Override
	public ArticleVendu findById(Integer id) {
		return articleVenduDAO.findById(id);
	}
	
	

}
