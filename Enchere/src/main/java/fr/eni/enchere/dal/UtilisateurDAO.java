package fr.eni.enchere.dal;

import java.security.Principal;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	Utilisateur read (long no_utilisateur);
	
	Utilisateur read (String email);

	void save(Utilisateur utilisateur);

	Utilisateur findById(Integer id);

	void delete(String pseudo);

	Utilisateur findByPseudo(String pseudo);

	List<Utilisateur> findAll();

}
