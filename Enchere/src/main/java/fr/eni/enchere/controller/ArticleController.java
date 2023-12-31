package fr.eni.enchere.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.controller.converter.StringToUtilisateurConverter;
import jakarta.validation.Valid;

@Controller
public class ArticleController {

	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	private UtilisateurService utilisateurService;
	private EnchereService enchereService;
	@Autowired
	private StringToUtilisateurConverter stringToUtilisateurConverter;

	@Autowired
	public ArticleController(ArticleVenduService articleVenduService, CategorieService categorieService,
			UtilisateurService utilisateurService, EnchereService enchereService) {
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
		this.utilisateurService = utilisateurService;
		this.enchereService = enchereService;
	}

	@GetMapping({ "/", "/accueil" })
	public String afficherEncheres(Model model) {
		List<ArticleVendu> articlesVendus = articleVenduService.consulterAV();
		List<Categorie> categories = categorieService.findAll();
		model.addAttribute("categories", categories);
		if (articlesVendus == null) {
			// Gérer le cas où la liste est nulle, par exemple, afficher un message d'erreur
			model.addAttribute("message", "Aucun article vendu disponible.");
		} else {
			// Ajouter la liste des articles vendus au modèle pour l'affichage dans la vue
			model.addAttribute("articlesVendus", articlesVendus);
		}

		return "accueil";
	}

	@GetMapping("/encheres")
	public String rechercherEncheres(@RequestParam(value = "categorie", required = false) Integer no_categorie,
			Model model) {
		List<ArticleVendu> lstA = articleVenduService.findByCat(no_categorie);
		List<Enchere> encheresFiltrees = enchereService.rechercherEncheresParCategorie(lstA);

		// Passez les enchères filtrées à la vue pour les afficher
		model.addAttribute("encheres", encheresFiltrees);

		// Passez également les options de catégorie à la vue pour afficher la liste
		// déroulante ou les cases à cocher
		List<Categorie> categories = categorieService.findAll();
		model.addAttribute("categories", categories);

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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		List<Categorie> categories = categorieService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("articleVendu", new ArticleVendu());
		if (authentication != null && authentication.isAuthenticated()) {
			String pseudo = authentication.getName();
			model.addAttribute("utilisateur", utilisateurService.findByPseudoOrEmail(pseudo));
			return "nouvel_article";
		} else {
			return "nouvel_article";
		}
	}

//	@PostMapping("/nouvel_article")
//	public String ajoutArticle(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu, @ModelAttribute("retrait")Retrait retrait, BindingResult bindingResult, Principal principal, @RequestParam("categorie") int noCategorie) {
//		if(!bindingResult.hasErrors()) {
//				System.out.println("Bien vu !");
//				System.out.println(noCategorie);
//				String pseudoUtil = principal.getName();
//				Utilisateur util = utilisateurService.findByPseudo(pseudoUtil);
//	            articleVendu.setNoUtilisateur(util);
//	            articleVendu.setNoCategorie(noCategorie);
//	            System.out.println("Méthode ajoutArticle appelée");
//				articleVenduService.ajoutArticle(articleVendu, retrait, principal);
//				enchereService.ajouterVente(articleVendu);
//				return "redirect:/accueil";
//		}else {
	@PostMapping("/nouvel_article")
	public String ajoutArticle(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu, @ModelAttribute("categorie") int categorie,
			BindingResult bindingResult, Retrait retrait, Principal principal) {
		// ,@RequestParam("categorie") int noCategorie
		if (!bindingResult.hasErrors()) {
			System.out.println("Bien vu !");
			// System.out.println(noCategorie);
			String pseudoUtil = principal.getName();
			Utilisateur util = utilisateurService.findByPseudoOrEmail(pseudoUtil);
			articleVendu.setNoUtilisateur(util);
			articleVendu.setNoCategorie(categorie);
			System.out.println("Méthode ajoutArticle appelée");
			articleVenduService.ajoutArticle(articleVendu, retrait, principal);
			enchereService.ajouterVente(articleVendu);
			return "redirect:/accueil";
		} else {

			System.out.println("Formulaire de création non conforme");
			System.out.println(bindingResult.getErrorCount());
			for (ObjectError err : bindingResult.getAllErrors()) {
				System.out.println(err.toString());
			}
			return "redirect:/nouvel_article";
		}
	}
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();//SpringSecurity
//        boolean utilisateurConnecte = auth.isAuthenticated();
//        model.addAttribute("utilisateurConnecte", utilisateurConnecte);
//        return "nom-de-votre-page";

	@GetMapping("/detail_vente")
	public String detailVente(@RequestParam("articleVendu") int noArticle, Model model) {

		ArticleVendu cible = articleVenduService.findById(noArticle);
		Enchere enchere = enchereService.findById(noArticle);
		Integer idCat = cible.getNoCategorie();
		Categorie cat = categorieService.findById(idCat);
		model.addAttribute("categorie", cat);
		model.addAttribute("enchere", enchere);
		model.addAttribute("articleVendu", cible);
		
		System.out.println(cible);
		return "detail_vente";
	}
	@PostMapping("/detail_vente")
	public String encherir(@RequestParam("articleVendu") int noArticle, @RequestParam("montant") int montant, @ModelAttribute("enchere") Enchere enchere, Principal principal, Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        String pseudo = authentication.getName();
	        Utilisateur util = utilisateurService.findByPseudoOrEmail(pseudo);
	        
	        Calendar calendar = Calendar.getInstance();
	        Date now = calendar.getTime();
	        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
	        enchere.setNoUtil(util.getNoUtilisateur());
	        enchere.setNoArticleVendu(noArticle);
	        enchere.setDateEnchere(sqlDate);
	        enchere.setMontant_enchere(montant);
	        System.out.println("encherir");
	        enchereService.encherir(enchere);
	        model.addAttribute("articleVendu", noArticle);
	        return "redirect:/accueil";
	    } else {
	        return "redirect:/detail_vente";
	    }
	}


//	@PostMapping("/detail_vente")
//	public String encherir(@RequestParam("encherir") int montant, @ModelAttribute("articleVendu") ArticleVendu articleVendu, @ModelAttribute("enchere") Enchere cible, Principal principal) {
////		if(utilisateurService.findByPseudoOrEmail(principal.getName()) != null){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null && authentication.isAuthenticated()) {
//			String pseudo = authentication.getName();
//			Utilisateur util = utilisateurService.findByPseudoOrEmail(pseudo);
//			int no = articleVendu.getNo_article();
//			cible.setNoUtil(util.getNoUtilisateur());
//			cible.setNoArticleVendu(no);
//			cible.setMontant_enchere(montant);
//			Calendar calendar = Calendar.getInstance();
//			Date now = calendar.getTime();
//			java.sql.Date sqlDate = new java.sql.Date(now.getTime());
//			cible.setDateEnchere(sqlDate);
//			System.out.println("encherir");
//			enchereService.encherir(cible);
//			return "accueil";
//		} else {
//			return "redirect:/";
//		}
//	}
}