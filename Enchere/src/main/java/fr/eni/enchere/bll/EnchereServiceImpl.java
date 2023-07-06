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
	public List<Enchere> rechercherEncheresParCategorie(Integer no_categorie) {
		// TODO Auto-generated method stub
		return enchereDAO.getEncheresParCategorie(no_categorie);
	}
	
	@Override
	public void encherir(Enchere cible, int noArticle) {
		System.out.println(cible.getNoUtil() + " " + cible.getMontant_enchere() + " " + cible.getDateEnchere() + " " + noArticle);
		cible.setNoArticleVendu(noArticle);
		enchereDAO.encherir(cible, noArticle);
	}

}
