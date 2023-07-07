package fr.eni.enchere.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final String FIND_BY_ID = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article=?";
	private static final String FIND_ALL = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
	private static final String UPDATE = "UPDATE ENCHERES SET no_utilisateur = :no_utilisateur, date_enchere = :date_enchere, montant_enchere = :montant_enchere WHERE no_article = :no_article";
	private static final String FIND_CAT = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article = :no_article";
	//	private static final String FIND_CAT = "SELECT * FROM ENCHERES WHERE no_categorie = :no_categorie";
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Enchere> findAll() {
		List<Enchere>encheres = jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Enchere.class));
		
		return encheres;
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
	public void encherir(Enchere cible) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_utilisateur", cible.getNoUtil());
		namedParameters.addValue("no_article", cible.getNoArticleVendu());
		namedParameters.addValue("date_enchere", cible.getDateEnchere());
		namedParameters.addValue("montant_enchere", cible.getMontant_enchere());
		jdbcTemplate.update(UPDATE, namedParameters);

	}

	@Override
	public Enchere findById(Integer noE) {
		Enchere src = new Enchere(noE);
		Enchere enchere = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Enchere.class),noE);
		return enchere;
	}
//a finir Steph
//	@Override
//	public List<Enchere> getEncheresParCategorie(Integer no_categorie) {
//		 MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//	    namedParameters.addValue("idCategorie", no_categorie);
//	    return jdbcTemplate.query(FIND_CAT, namedParameters, new EnchereRowMapper());
//	}

	
	//juste pour pouvoir compiler modif à faire dans la méthode en commentaire
	@Override
	public List<Enchere> getEncheresParCategorie(List<ArticleVendu> lstA) {
	    List<Enchere> encheres = new ArrayList<>();

	    for (ArticleVendu articleVendu : lstA) {
	        Integer noCategorie = articleVendu.getNoCategorie(); // Récupérer le numéro de catégorie de l'article

	        Map<String, Object> param = new HashMap<>();
	        param.put("no_categorie", noCategorie);

	        // Exécuter la requête FIND_CAT pour la catégorie de l'article
	        List<Enchere> encheresParCategorie = jdbcTemplate.query(FIND_CAT, param, new BeanPropertyRowMapper<>(Enchere.class));

	        // Ajouter les résultats à la liste d'enchères
	        encheres.addAll(encheresParCategorie);
	    }

	    return encheres;
	}

}


