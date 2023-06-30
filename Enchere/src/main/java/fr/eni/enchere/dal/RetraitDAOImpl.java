package fr.eni.enchere.dal;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.Utilisateur;

@Repository
public class RetraitDAOImpl implements RetraitDAO{

private UtilisateurService utilisateurService;

private static final String INSERT ="insert into RETRAIT (no_article,rue,code_postal, ville)"
		+ " values (:no_utilisateur, :no_article, :rue, :code_postal, :ville)";


@Autowired
private NamedParameterJdbcTemplate jdbcTemplate;

public RetraitDAOImpl(UtilisateurService utilisateurService) {
	this.utilisateurService = utilisateurService;
}

public void adresseRetrait(Utilisateur utilisateur) {
MapSqlParameterSource namedParameters = new MapSqlParameterSource();
namedParameters.addValue("rue", utilisateur.getRue());
namedParameters.addValue("code_postal", utilisateur.getCodePostal());
namedParameters.addValue("ville", utilisateur.getVille());
jdbcTemplate.update(INSERT, namedParameters);

}







	
}
