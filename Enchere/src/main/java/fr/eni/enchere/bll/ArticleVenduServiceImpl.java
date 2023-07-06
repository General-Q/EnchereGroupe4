package fr.eni.enchere.bll;

import java.security.Principal;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.ArticleVenduDAO;
import jakarta.validation.Valid;

@Service
@Primary
public class ArticleVenduServiceImpl implements ArticleVenduService{
	private ArticleVenduDAO articleVenduDAO;
	private Retrait retrait;
	
	public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
		this.articleVenduDAO = articleVenduDAO;
	}
	
	@Override
	public List<ArticleVendu> consulterAV(){
		List<ArticleVendu> articlesVendus = articleVenduDAO.findAll();
		return articlesVendus;
	}
	
	@Override
	public void ajoutArticle(ArticleVendu articleVendu, Retrait retrait, Principal principal) {
		articleVenduDAO.ajoutArticle(articleVendu, retrait, principal);
	}
	
	@Override
	public ArticleVendu findById(Integer id) {
		return articleVenduDAO.findById(id);
	}
	
	@Override
	public List<ArticleVendu> findByCat(Integer noC){
		return articleVenduDAO.findByCat(noC);
	}

	public Retrait getRetrait() {
		return retrait;
	}


	

}
