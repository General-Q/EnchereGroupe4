package fr.eni.enchere.bll;

import java.security.Principal;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurService {

	Utilisateur findById(Integer id);

	void supprimerProfil(Principal principal);

	Utilisateur findByPseudo(String pseudo);

	void saveProfil(Utilisateur utilisateur);
}
