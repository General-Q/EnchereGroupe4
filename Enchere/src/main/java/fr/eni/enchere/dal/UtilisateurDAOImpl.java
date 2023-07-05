package fr.eni.enchere.dal;

import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import fr.eni.enchere.bo.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO{
	private final String FIND_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, administrateur from UTILISATEURS WHERE no_utilisateur=?";
	private final String FIND_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, administrateur from UTILISATEURS WHERE pseudo=?";
	private final String FIND_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, administrateur from UTILISATEURS WHERE email =?"; // attention j'ai modifié c'était email =:email
	private static final String SELECT_BY_PSEUDO_OR_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit from UTILISATEURS WHERE ? IN ( pseudo , email )";
	private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
            + " values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)";
	private static final String UPDATE = "UPDATE UTILISATEURS set pseudo=:pseudo,nom=:nom, prenom=:prenom, email=:email, telephone=:telephone,rue=:rue, code_postal=:code_postal,ville=:ville,mot_de_passe=:mot_de_passe where no_utilisateur=:no_utilisateur";
	private static final String DELETE_BY_PSEUDO_OR_EMAIL = "DELETE UTILISATEURS WHERE IN (pseudo=:pseudo , email=:email)";
	private static final String SELECT_ALL = "select pseudo from UTILISATEURS";
	private static final String VERIF_PSEUDO = "select count(*) FROM UTILISATEURS where pseudo =:pseudo";
	private static final String VERIF_EMAIL = "select count(*) FROM UTILISATEURS where email =:email";
	private static final String SELECT_EMAIL_BY_PSEUDO = "SELECT email FROM UTILISATEURS where pseudo =:pseudo";
	private static final String SELECT_PSEUDO_BY_EMAIL = "SELECT pseudo FROM UTILISATEURS where email =:pseudo";
	
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
			user.setNoUtilisateur(rs.getInt("no_utilisateur"));
			user.setPseudo(rs.getString("pseudo"));
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));
			user.setEmail(rs.getString("email"));
			user.setTelephone(rs.getString("telephone"));
			user.setRue(rs.getString("rue"));
			user.setCodePostal(rs.getString("code_postal"));
			user.setVille(rs.getString("ville"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
			user.setCredit(rs.getInt("credit"));
			user.setAdministrateur(rs.getBoolean("administrateur"));
			return user;
		}
		
	}

	@Override
	public void save(Utilisateur utilisateur) {
		
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", utilisateur.getNoUtilisateur());
		paramSrc.addValue("pseudo", utilisateur.getPseudo());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("prenom", utilisateur.getPrenom());
		paramSrc.addValue("email", utilisateur.getEmail());
		paramSrc.addValue("telephone", utilisateur.getTelephone());
		paramSrc.addValue("rue", utilisateur.getRue());
		paramSrc.addValue("code_postal", utilisateur.getCodePostal());
		paramSrc.addValue("ville", utilisateur.getVille());
		paramSrc.addValue("mot_de_passe", utilisateur.getMotDePasse());
		paramSrc.addValue("credit", 300);
		paramSrc.addValue("administrateur", false);
		
		if (utilisateur.getNoUtilisateur()==null) {
			// insert
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(INSERT, paramSrc, keyHolder);
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
			System.out.println("Insert utilisateur : " + utilisateur);
		}else {
			// update
			jdbcTemplate.update(UPDATE, paramSrc);
			System.out.println("Update utilisateur : " + utilisateur);
		}
	}

	@Override
	public Utilisateur findById(Integer id) {
		Utilisateur src = new Utilisateur(id);
		Utilisateur utilisateur = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Utilisateur.class),id);
		return utilisateur;
	}

	@Override
	public void delete(String pseudo) {
		System.out.println(pseudo);
		Utilisateur user = null;
			user = jdbcTemplate.getJdbcOperations().queryForObject(SELECT_BY_PSEUDO_OR_EMAIL,new BeanPropertyRowMapper<>(Utilisateur.class), pseudo);
			System.out.println("delete " + user);
			jdbcTemplate.update(DELETE_BY_PSEUDO_OR_EMAIL, new BeanPropertySqlParameterSource(user));
	}

	@Override
	public Utilisateur findByPseudoOrEmail(String pseudo) {
		System.out.println("je rentre dans fin by pseudo or email");
		Utilisateur src = new Utilisateur(pseudo);
		System.out.println(src);
		Utilisateur utilisateur = jdbcTemplate.getJdbcOperations().queryForObject(SELECT_BY_PSEUDO_OR_EMAIL,new BeanPropertyRowMapper<>(Utilisateur.class), pseudo);
		System.out.println("USER RECUPERE "+utilisateur);
		return utilisateur;
	}

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs;
		utilisateurs = jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Utilisateur.class));
		return utilisateurs;
	}

	@Override
	public Boolean pseudoUnique(String pseudo) {
		Map<String, Object> map = Collections.singletonMap("pseudo", pseudo);
		Integer count = jdbcTemplate.queryForObject(VERIF_PSEUDO, map, Integer.class);
		System.out.println("count pseudoUnique : " + count);
		if (count>0) {
			return true;
		}
		return false;			
	}

	@Override
	public Boolean emailUnique(String email) {
		Map<String, Object> map = Collections.singletonMap("email", email);
		Integer count = jdbcTemplate.queryForObject(VERIF_EMAIL, map, Integer.class);
		System.out.println("count emailUnique : " + count);
		if (count>0) {
			return true;
		}
		return false;	
	}

	@Override
	public Utilisateur findEmailByPseudo(String pseudo) {
		System.out.println("je rentre dans find Email by pseudo");
		Utilisateur src = new Utilisateur(pseudo);
		Utilisateur utilisateur = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_PSEUDO, new BeanPropertyRowMapper<>(Utilisateur.class),pseudo);
		System.out.println("findEmailByPseudo "+utilisateur);
		return utilisateur;
	
		/*Utilisateur utilisateur = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_PSEUDO,new BeanPropertyRowMapper<>(Utilisateur.class), pseudo);
		System.out.println("findEmailByPseudo "+utilisateur);
		return utilisateur;*/
	}

	@Override
	public Utilisateur findPseudoByEmail(String pseudo) {
		System.out.println("je rentre dans find pseudo by email");
		Utilisateur utilisateur = jdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_EMAIL,new BeanPropertyRowMapper<>(Utilisateur.class), pseudo);
		System.out.println("findPseudoByEmail "+utilisateur);
		
		return utilisateur;
	}

	/*@Override
	public Utilisateur findByEmail(String email) {
		Utilisateur src = new Utilisateur(email);
		System.out.println(src);
		Utilisateur utilisateur = jdbcTemplate.getJdbcOperations().queryForObject(SELECT_BY_EMAIL,new BeanPropertyRowMapper<>(Utilisateur.class), email);
		System.out.println("USER RECUPERE "+utilisateur);
		return utilisateur;
	}*/
		
}
