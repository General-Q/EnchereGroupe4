package fr.eni.enchere.bll;

import java.security.Principal;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurService {

	Utilisateur findById(Integer id);

	void supprimerProfil(String pseudo);

	Utilisateur findByPseudo(String pseudo);

	void saveProfil(Utilisateur utilisateur);

	List<Utilisateur> findAllUsers();

	Utilisateur findByEmail(String email);
}
