package fr.eni.enchere.bll;

import java.security.Principal;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.execptions.UtilisateurNotFoundException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO utilisateurDAO;
	private final PasswordEncoder passwordEncoder;
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public void supprimerProfil(String pseudo) {
		utilisateurDAO.delete(pseudo);
		
	}

	@Override
	public Utilisateur findById(Integer id) {
		return utilisateurDAO.findById(id);
	}

	@Override
	public Utilisateur findByPseudoOrEmail(String pseudo) {
		return utilisateurDAO.findByPseudoOrEmail(pseudo);
	}

	@Override
	public void saveProfil(Utilisateur utilisateur) {
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		utilisateurDAO.save(utilisateur);
	}

	@Override
	public List<Utilisateur> findAllUsers() {
		return utilisateurDAO.findAll();
	}

	@Override
	public Boolean pseudoUnique(String pseudo) {
		return utilisateurDAO.pseudoUnique(pseudo);
	}

	@Override
	public Boolean emailUnique(String email) {
		return utilisateurDAO.emailUnique(email);
	}

}
