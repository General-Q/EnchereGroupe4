package fr.eni.enchere.bll;

import java.security.Principal;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurService {

	void creerProfil (Utilisateur utilisateur);

	Utilisateur findById(Integer id);

	void supprimerProfil(Principal principal);

	void enregistrerProfil(Utilisateur utilisateur);

	Utilisateur findByPseudo(String pseudo);
}
