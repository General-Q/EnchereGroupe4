package fr.eni.enchere.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import jakarta.validation.Valid;

@Controller
public class ArticleController {

	private ArticleVenduService articleVenduService;

	@Autowired
	public ArticleController(ArticleVenduService articleVenduService) {
		this.articleVenduService = articleVenduService;
	}

	@GetMapping({ "/", "/accueil" })
	public String afficherEncheres(Model model) {
		List<ArticleVendu> articlesVendus = articleVenduService.consulterAV();

		if (articlesVendus == null) {
			// Gérer le cas où la liste est nulle, par exemple, afficher un message d'erreur
			model.addAttribute("message", "Aucun article vendu disponible.");
		} else {
			// Ajouter la liste des articles vendus au modèle pour l'affichage dans la vue
			model.addAttribute("articlesVendus", articlesVendus);
		}

		return "accueil";
	}

	@GetMapping("/identification")
	public String identUser() {
		return "identification";
	}
	/*
	 * @GetMapping("/detail_vente") public String detailVente(Integer id, Model
	 * model) { Enchere enchere = enchereService.findById(id);
	 * model.addAttribute("enchere", enchere); return "detail_vente";
	 * 
	 * }
	 */

	@GetMapping("/nouvel_article")
	public String ajoutArticle(Model model) {
		model.addAttribute("articleVendu", new ArticleVendu());
		return "nouvel_article";
	}

	@PostMapping("/nouvel_article")
	public String ajoutArticle(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			try {
				articleVenduService.ajoutArticle(articleVendu);
				return "redirect:/accueil";
			} catch (Exception e) {
				System.out.println("Ajout de la vente impossible, nous sommes désolés");
				return "redirect:/nouvel_article";
			}
		}else {
			System.out.println("Formulaire de création non conforme");
			return "redirect:/nouvel_article";
		}
	}
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();//SpringSecurity
//        boolean utilisateurConnecte = auth.isAuthenticated();
//        model.addAttribute("utilisateurConnecte", utilisateurConnecte);
//        return "nom-de-votre-page";

	@PostMapping("/nouvelle_vente")
	public String ajouterVente(@Valid @ModelAttribute Enchere enchere, BindingResult validationResult) {
		if (validationResult.hasErrors()) {
			return "nouvelle_vente";

		}
		// enchereService.ajouterVente(enchere);

		return "redirection:/accueil";

	}

}