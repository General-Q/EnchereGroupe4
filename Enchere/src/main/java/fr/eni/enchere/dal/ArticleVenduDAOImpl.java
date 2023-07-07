package fr.eni.enchere.dal;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO{
	private final String FIND_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article=?";
	private final String FIND_BY_CAT = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_categorie= :no_categorie";
	private final String FIND_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
	private final String INSERT = "insert into articles_vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)"
			+ " values (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
	
	private static final String INSERT_RETRAIT ="insert into RETRAITS (no_article,rue,code_postal, ville)"
			+ " values (:no_article, :rue, :code_postal, :ville)";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Override
	public List<ArticleVendu> findAll() {
		return jdbcTemplate.query(FIND_ALL, new ArticleVenduRowMapper());
	}
	
	@Override
    public ArticleVendu findById(Integer id) {

        //TODO suppr ArticleVendu src = new ArticleVendu(id);

        ArticleVendu articleVendu = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(ArticleVendu.class),id);

        return articleVendu;
    } 
	@Override
	public List<ArticleVendu> findByCat(Integer noC) {
		Map<String, Object> param = new HashMap<>();
		param.put("no_categorie", noC);
		List<ArticleVendu> lstA = jdbcTemplate.query(FIND_BY_CAT, param, new ArticleVenduRowMapper());
		return lstA;
	}
	
	@Override
	public void ajoutArticle(ArticleVendu articleVendu, Retrait retrait, Principal principal) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Utilisateur utilisateur = utilisateurService.findByPseudoOrEmail(principal.getName());
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("nom_article", articleVendu.getNom_article());
		namedParameters.addValue("description", articleVendu.getDescription());
		namedParameters.addValue("date_debut_encheres", articleVendu.getDate_debut_encheres());
		namedParameters.addValue("date_fin_encheres", articleVendu.getDate_fin_encheres());
		namedParameters.addValue("prix_initial", articleVendu.getPrix_initial());
		namedParameters.addValue("prix_vente", articleVendu.getPrix_vente());
		namedParameters.addValue("no_utilisateur", articleVendu.getNoUtilisateur());
		namedParameters.addValue("no_categorie", articleVendu.getNoCategorie());
		System.out.println(articleVendu.getNoCategorie());
	    namedParameters.addValue("rue", utilisateur.getRue());
	    namedParameters.addValue("code_postal", utilisateur.getCodePostal());
	    namedParameters.addValue("ville", utilisateur.getVille());

	    System.out.println(retrait);
	    System.out.println(articleVendu);

	    System.out.println("trace1");
	    
		jdbcTemplate.update(INSERT, namedParameters, keyHolder);
		System.out.println("trace");

		if (keyHolder != null && keyHolder.getKey() != null) {
			articleVendu.setNo_article(keyHolder.getKey().intValue());
		}
		namedParameters.addValue("no_article", articleVendu.getNo_article());
		jdbcTemplate.update(INSERT_RETRAIT, namedParameters);
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
			aV.setNoUtilisateur(utilisateurService.findById(rs.getInt("no_utilisateur")));
			aV.setNoCategorie(rs.getInt("no_categorie"));
			
			return aV;
		}
	}

}
