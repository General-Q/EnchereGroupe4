package fr.eni.enchere.dal;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

@Repository
public class RetraitDAOImpl implements RetraitDAO{

private ArticleVenduService articleVenduService;	
private UtilisateurService utilisateurService;

private static final String INSERT ="insert into RETRAIT (no_article,rue,code_postal, ville)"
		+ " values (:no_utilisateur, :no_article, :rue, :code_postal, :ville)";

private static final String FIND_BY_ID = "SELECT no_article from ARTICLES_VENDUS WHERE no_article=?";

@Autowired
private NamedParameterJdbcTemplate jdbcTemplate;

public RetraitDAOImpl(UtilisateurService utilisateurService) {
	this.utilisateurService = utilisateurService;
}



//public Retrait findById(Integer no_article) {
//    ArticleVendu articleVendu = articleVenduService.findById(no_article);
//    if (articleVendu != null) {
//        Retrait retrait = articleVendu.getRetrait();
//        return retrait;
//    }
//    return null;
//}

//public Retrait findById(Integer no_article ) {
//	ArticleVenduService articleVenduService = (ArticleVenduService) articleVenduService.findById(no_article);
//	Retrait src = new Retrait(no_article);
//	Retrait retrait = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Retrait.class),articleVenduService.getRetrait().getNo_article());
//	
//	return retrait;
//	
//}

public void adresseRetrait(Principal principal, Retrait retrait) {
    Utilisateur utilisateur = utilisateurService.findByPseudoOrEmail(principal.getName());

    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
    namedParameters.addValue("no_article", retrait); // Remplacez "noArticle" par la valeur appropriée
    namedParameters.addValue("rue", utilisateur.getRue());
    namedParameters.addValue("code_postal", utilisateur.getCodePostal());
    namedParameters.addValue("ville", utilisateur.getVille());

    jdbcTemplate.update(INSERT, namedParameters);
	}



	
}
