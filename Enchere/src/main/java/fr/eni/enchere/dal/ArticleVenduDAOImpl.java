package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.ArticleVendu;
@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO{
	private final String FIND_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS";

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ArticleVenduDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ArticleVendu> findAll() {
		return jdbcTemplate.query(FIND_ALL, new ArticleVenduRowMapper());
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
