package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

public class ArticleVenduDAOImpl {
	private final String FIND_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
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

			// Association pour le réalisateur
			Participant realisateur = new Participant();
			realisateur.setId(rs.getLong("ID_REALISATEUR"));
			f.setRealisateur(realisateur);

			// Association pour le genre
			Genre genre = new Genre();
			genre.setId(rs.getLong("ID_GENRE"));
			f.setGenre(genre);

			return f;
		}
	}
}
