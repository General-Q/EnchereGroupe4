package fr.eni.enchere.bll;

import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.EnchereDAO;
import jakarta.validation.Valid;

@Service
public class EnchereServiceImpl implements EnchereService{

	private EnchereDAO enchereDAO;
	private ArticleVenduService articleVenduService;
	
	public EnchereServiceImpl(ArticleVenduService articleVenduService) {
		this.articleVenduService = articleVenduService;
	}

	/*
	@Override
	public Enchere findById(Integer id) {
	Enchere enchere = null;
	try {
		enchere = enchereDAO.read(id);
	}catch (EnchereNotFoundException e) {
		
	}
		return enchere;
	}
*/
	
	@Override
	@Async
	public void debutEnchere(@Valid Enchere enchere, Date date) {
		Integer noA = enchere.getNoArticleVendu();
		ArticleVendu aV = articleVenduService.
	}
	
	@Override
	public Enchere findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
