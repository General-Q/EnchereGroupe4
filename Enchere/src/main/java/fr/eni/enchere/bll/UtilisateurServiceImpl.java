package fr.eni.enchere.bll;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public void creerProfil(Utilisateur utilisateur) {
		utilisateurDAO.save(utilisateur);
		
	}

}
