package fr.eni.enchere.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;

@Service
public class EnchereDAOImpl implements EnchereDAO{
	private static final String INSERT = "insert into ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)"
            + " values (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";
	private final String FIND_BY_ID = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article=?";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Enchere> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void ajouterVente(Enchere enchere) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_utilisateur", enchere.getNoUtil());
		namedParameters.addValue("no_article", enchere.getNoArticleVendu());
		namedParameters.addValue("date_enchere", enchere.getDateEnchere());
		namedParameters.addValue("montant_enchere", enchere.getMontant_enchere());
		jdbcTemplate.update(INSERT, namedParameters);
	}

	@Override
	public Enchere findById(Integer noE) {
		Enchere src = new Enchere(noE);
		Enchere enchere = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Enchere.class),noE);
		return enchere;
	}

}
