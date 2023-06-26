package fr.eni.enchere.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bo.ArticleVendu;

@Controller
public class UtilisateurController {
	
	@GetMapping({"/", "/accueil"})
	public String afficherEncheres(Model model){
		List<ArticleVendu> articlesV = ArticleVenduService.consulterAV();
		model.addAttribute("articlesV", articlesV);
		return "accueil";
	}
	
	@GetMapping("/identification")
	public String identUser() {
		return "identification";
	}
	

	public UtilisateurController() {
		// TODO Auto-generated constructor stub
	}

}
