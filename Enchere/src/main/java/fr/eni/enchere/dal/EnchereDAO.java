package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO {

	List<Enchere> findAll();
	/* Enchere read(Integer); */
	void ajouterVente(Enchere enchere);
	public Enchere findById(Integer noE);
	List<Enchere> getEncheresParCategorie(List<ArticleVendu> lstA);
	void encherir(Enchere cible);
}
