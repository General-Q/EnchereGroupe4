package fr.eni.enchere.bll;

import java.security.Principal;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurService {

	Utilisateur findById(Integer id);

	void supprimerProfil(String pseudo);

	Utilisateur findByPseudoOrEmail(String pseudo);

	void saveProfil(Utilisateur utilisateur);

	List<Utilisateur> findAllUsers();
}
