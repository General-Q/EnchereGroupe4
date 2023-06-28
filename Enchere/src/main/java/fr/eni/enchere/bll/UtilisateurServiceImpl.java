package fr.eni.enchere.bll;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO utilisateurDAO;
	private final PasswordEncoder passwordEncoder;
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public void creerProfil(Utilisateur utilisateur) {
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		utilisateurDAO.save(utilisateur);
		
	}

}
