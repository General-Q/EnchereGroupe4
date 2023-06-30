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
	
	public EnchereServiceImpl(ArticleVenduService articleVenduService, EnchereDAO enchereDAO) {
		this.articleVenduService = articleVenduService;
		this.enchereDAO = enchereDAO;
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
	public void ajouterVente(ArticleVendu articleVendu) {
			Enchere enchere = new Enchere(articleVendu);
			enchereDAO.ajouterVente(enchere);
	}
	
	@Override
	public Enchere findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
