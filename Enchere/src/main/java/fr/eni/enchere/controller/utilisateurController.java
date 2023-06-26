package fr.eni.enchere.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.enchere.bo.Enchere;

@Controller
public class utilisateurController {
	
	@GetMapping({"/", "/accueil"})
	public String afficherEncheres(Model model){
		List<ArticleVendu> encheres = ArticleVenduService.consulterAV();
		return "accueil";
	}
	
	@GetMapping("/identification")
	public String identUser() {
		return "identification";
	}
	

	public utilisateurController() {
		// TODO Auto-generated constructor stub
	}

}
