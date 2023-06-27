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
	String chargerUtilisateurEnSession(@ModelAttribute("utilisateurEnSession")Utilisateur utilisateur, Principal principal) {
		String email=principal.getName();
		Utilisateur aCharger = contexteService.charger(email);
	}
	
	
	
}
