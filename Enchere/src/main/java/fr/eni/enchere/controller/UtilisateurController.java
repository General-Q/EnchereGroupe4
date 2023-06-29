package fr.eni.enchere.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.Utilisateur;
import jakarta.validation.Valid;

@Controller
public class UtilisateurController {

	private UtilisateurService utilisateurService;

	@Autowired
	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping("/inscription")
	public String inscription(@ModelAttribute Utilisateur utilisateur) {
		return "profil-form";
	}
	
	@PostMapping("/creerProfil")
	public String creerProfil(Utilisateur utilisateur) {
		utilisateurService.creerProfil(utilisateur);
		return"redirect:/accueil";
	}
	
	@GetMapping("/modifierProfil")
	public String modifierProfil(Model modele) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.isAuthenticated()) {
	            // L'utilisateur est connecté, récupérez ses informations et passez-les au modèle
	            String pseudo = authentication.getName();
	            modele.addAttribute("utilisateur",utilisateurService.findByPseudo(pseudo));
	        } else {
	            // Aucun utilisateur n'est connecté, redirigez vers le formulaire d'inscription ou une autre page appropriée
	            return "redirect:/inscription";
	        }
		return "profil-form";
	}
	
	@GetMapping("/supprimerProfil")
	public String supprimerProfil(@RequestParam Principal principal) {
		utilisateurService.supprimerProfil(principal);
		return "redirect:/accueil";
	}
	
	@PostMapping("/enregistrerProfil")
	public String enregistrerProfil(Utilisateur utilisateur) {
		utilisateurService.enregistrerProfil(utilisateur);
		return "redirect:/accueil";
	}
	
}
