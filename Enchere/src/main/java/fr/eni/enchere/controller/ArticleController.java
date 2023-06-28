package fr.eni.enchere.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import jakarta.validation.Valid;

@Controller
public class ArticleController {

	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;

	@Autowired
	public ArticleController(ArticleVenduService articleVenduService, CategorieService categorieService) {
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
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

	/*
	 * @GetMapping("/detail_vente") public String detailVente(Integer id, Model
	 * model) { Enchere enchere = enchereService.findById(id);
	 * model.addAttribute("enchere", enchere); return "detail_vente";
	 * 
	 * }
	 */

	@GetMapping("/nouvel_article")
	public String ajoutArticle(Model model) {
		List<Categorie> categories = categorieService.categories();
		model.addAttribute("categories", categories);
		model.addAttribute("articleVendu", new ArticleVendu());
		return "nouvel_article";
	}

	@PostMapping("/nouvel_article")
	public String ajoutArticle(@Valid @ModelAttribute("articlevendu") ArticleVendu articleVendu, BindingResult bindingResult, Principal principal) {
		if(!bindingResult.hasErrors()) {
				System.out.println("Bien vu !");
				String no_utilisateur = principal.getName();
				Utilisateur util = Utilisateur.findById(no_utilisateur);
	            articleVendu.setUtilisateur(util);
				articleVenduService.ajoutArticle(articleVendu);
				return "redirect:/accueil";
		}else {
			System.out.println("Formulaire de création non conforme");
			System.out.println(bindingResult.getErrorCount());
			for (ObjectError err : bindingResult.getAllErrors()) {
				System.out.println(err.toString());
			}
			return "nouvel_article";
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