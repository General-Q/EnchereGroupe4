package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	Utilisateur read (long no_utilisateur);
	
	Utilisateur read (String email);

	void save(Utilisateur utilisateur);

}
