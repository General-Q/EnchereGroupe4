package fr.eni.enchere.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import jakarta.validation.Valid;

@Controller
public class utilisateurController {
	
	
	private EnchereService enchereService;
	
	
	@GetMapping({"/", "/accueil"})
	public String afficherEncheres(Model model){
		List<ArticleVendu> encheres = ArticleVenduService.consulterAV();
		return "accueil";
	}
	
	@GetMapping("/identification")
	public String identUser() {
		return "identification";
	}
	
	
	@PostMapping("/nouvelle_vente")
	public String ajouterVente(@Valid @ModelAttribute Enchere enchere, BindingResult validationResult) { 
		if (validationResult.hasErrors()) {
			return "nouvelle_vente";
			
		}
	enchereService.ajouterVente(enchere);
	
	return "redirection:/accueil";
	
	
	}

	public utilisateurController() {
		// TODO Auto-generated constructor stub
	}

}
