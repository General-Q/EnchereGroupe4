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
	private ArticleVenduService articleVenduService;
	
	
	@GetMapping({"/", "/accueil"})
	public String afficherEncheres(Model model){
		List<ArticleVendu> articlesVendus = articleVenduService.consulterAV();
		return "accueil";
	}
	
	@GetMapping("/identification")
	public String identUser() {
		return "identification";
	}
	
	@GetMapping("/detail_vente")
	public String detailVente(Integer id, Model model) {
		Enchere enchere = enchereService.findById(id);
		model.addAttribute("enchere", enchere);
		return "detail_vente";
		
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
