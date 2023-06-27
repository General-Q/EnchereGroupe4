package fr.eni.enchere.dal;

import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.enchere.bo.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO{
	private final String FIND_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, administrateur from UTILISATEURS WHERE no_utilisateur=no_utilisateur";
	private final String FIND_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, administrateur from UTILISATEURS WHERE email =:email";
	private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
            + " values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)";


	
	
	@Autowired 
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Utilisateur read(long no_utilisateur) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_utilisateur", no_utilisateur);
		return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new UtilisateurRowMapper());
	}

	@Override
	public Utilisateur read(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("email", email);
		return jdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters, new UtilisateurRowMapper());
	}
	
	class UtilisateurRowMapper implements RowMapper<Utilisateur> {
		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur user = new Utilisateur();
			user.setno_utilisateur(rs.getLong("no_utilisateur"));
			user.setPseudo(rs.getString("pseudo"));
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));
			user.setEmail(rs.getString("email"));
			user.setAdministrateur(rs.getBoolean("administrateur"));
			return user;
		}
		
	}

	@Override
	public void save(Utilisateur utilisateur) {
		//Valorisation des paramètres nommés 
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", utilisateur.getPseudo() );
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("prenom", utilisateur.getPrenom());
		paramSrc.addValue("email", utilisateur.getEmail());
		paramSrc.addValue("telephone", utilisateur.getTelephone());
		paramSrc.addValue("rue", utilisateur.getRue());
		paramSrc.addValue("code_postal", utilisateur.getcode_postal());
		paramSrc.addValue("ville", utilisateur.getVille());
		paramSrc.addValue("mot_de_passe", utilisateur.getmot_de_passe());
		paramSrc.addValue("credit", 0);
		paramSrc.addValue("administrateur", false);		
		jdbcTemplate.update(INSERT, paramSrc);
		
	}
		
}
