package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import fr.eni.enchere.bo.ArticleVendu;
@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO{
	private final String FIND_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS";
	private final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ArticleVendu> findAll() {
		return jdbcTemplate.query(FIND_ALL, new ArticleVenduRowMapper());
	}
	
	@Override
	public void ajoutArticle(ArticleVendu articleVendu) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//		namedParameters.addValue("no_article", articleVendu.getNo_article());
		namedParameters.addValue("nom_article", articleVendu.getNom_article());
		namedParameters.addValue("description", articleVendu.getDescription());
		namedParameters.addValue("date_debut_encheres", articleVendu.getDate_debut_encheres());
		namedParameters.addValue("date_fin_encheres", articleVendu.getDate_fin_encheres());
		namedParameters.addValue("prix_initial", articleVendu.getPrix_initial());
		namedParameters.addValue("prix_vente", articleVendu.getPrix_vente());
		namedParameters.addValue("etatVente", articleVendu.getEtatVente());

		jdbcTemplate.update(INSERT, namedParameters, keyHolder);

		if (keyHolder != null && keyHolder.getKey() != null) {
			articleVendu.setNo_article(keyHolder.getKey().intValue());
		}
	}
	
	class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {
		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu aV = new ArticleVendu();
			aV.setNo_article(rs.getInt("no_article"));
			aV.setNom_article(rs.getString("nom_article"));
			aV.setDescription(rs.getString("description"));
			aV.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
			aV.setDate_fin_encheres(rs.getDate("date_fin_encheres"));
			aV.setPrix_initial(rs.getInt("prix_initial"));
			aV.setPrix_vente(rs.getInt("prix_vente"));
			aV.setEtatVente(rs.getBoolean("etat_vente"));
			
			return aV;
		}
	}
}
