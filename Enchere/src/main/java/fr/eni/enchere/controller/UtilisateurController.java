package fr.eni.enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.Utilisateur;
import jakarta.validation.Valid;

@Controller
public class UtilisateurController {

	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping("/identification")
	public String identUser() {
		return "identification";
	}
	
	@GetMapping("/inscription")
	public String  inscription( @ModelAttribute Utilisateur utilisateur) {
		return "inscription";
	}
	
	@PostMapping("/creerProfil")
	public String creerProfil(Utilisateur utilisateur) {
		utilisateurService.creerProfil(utilisateur);
		return"redirect:/accueil";
	}
	
}
