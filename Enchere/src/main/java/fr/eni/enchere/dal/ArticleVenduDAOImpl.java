package fr.eni.enchere.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.enchere.bo.ArticleVendu;

public class ArticleVenduDAOImpl {
	private final String FIND_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ArticleVendu> findAll() {
		return jdbcTemplate.query(FIND_ALL, new ArticleVenduRowMapper());
	}
}
