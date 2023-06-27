package fr.eni.enchere.controller.security;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.enchere.bll.contexte.ContexteService;
import fr.eni.enchere.bo.Utilisateur;

@Controller
// Injection de la liste des attributs en session
@SessionAttributes ({"utilisateurEnSession"})
public class LoginController {
	private ContexteService contexteService;

	public LoginController(ContexteService contexteService) {
		this.contexteService = contexteService;
	}

	@GetMapping ("/identification")
	String identification() {
		return "identification";
	}
	
	@GetMapping("/session")
	String chargerUtilisateurEnSession(@ModelAttribute("utilisateurEnSession")Utilisateur utilisateurEnSession, Principal principal) {
		String email=principal.getName();
		Utilisateur aCharger = contexteService.charger(email);
		if (aCharger !=null) {
			utilisateurEnSession.setno_utilisateur(aCharger.getno_utilisateur());
			utilisateurEnSession.setPseudo(aCharger.getPseudo());
			utilisateurEnSession.setNom(aCharger.getNom());
			utilisateurEnSession.setPrenom(aCharger.getPrenom());
			utilisateurEnSession.setAdministrateur(aCharger.getAdministrateur());
			
		}else {
			utilisateurEnSession.setno_utilisateur((long) 0);
			utilisateurEnSession.setPseudo("Test");
			utilisateurEnSession.setNom("Test");
			utilisateurEnSession.setPrenom("Test");
			utilisateurEnSession.setAdministrateur(false);
		}
		System.out.println(utilisateurEnSession);
		return "redirect:/accueil";
	}
	
	// méthode permettant un nouvel utilisateur en session par défaut
	@ModelAttribute("utilisateurEnSession")
	public Utilisateur utilisateurEnSession() {
		System.out.println("Ajout attribute session");
		return new Utilisateur();
	}
	
	
}
