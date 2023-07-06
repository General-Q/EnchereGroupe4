package fr.eni.enchere.bll;

import java.util.Date;
import java.util.List;

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
	public Enchere findById(Integer noE) {
		return enchereDAO.findById(noE);
	}

	@Override
	public List<Enchere> rechercherEncheresParCategorie(Integer no_article) {
		// TODO Auto-generated method stub
		return enchereDAO.getEncheresParCategorie(no_article);
	}
	
	@Override
	public void encherir(Enchere cible) {
		System.out.println("util " + cible.getNoUtil() + "montant " + cible.getMontant_enchere() + "date " + cible.getDateEnchere() + "article " + cible.getNoArticleVendu());
		enchereDAO.encherir(cible);
	}

}
