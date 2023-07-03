package fr.eni.enchere.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	@GetMapping({"/inscription","/profil"})
	public String inscription(@ModelAttribute Utilisateur utilisateur) {
		System.out.println("vers profil-form OK");
		return "profil-form";
	}
	
	@GetMapping("/layout.css")
	public String RenvoiApresConnexion() {
		return"redirect:/accueil";
	}
	
	@PostMapping("/enregistrerProfil")
	public String EnregistrerProfil(Utilisateur utilisateur) {
		System.out.println("postmapping enregistrerProfil OK");
		utilisateurService.saveProfil(utilisateur);
		return"redirect:/accueil";
	}
	
	@GetMapping("/modifierProfil")
	public String modifierProfil(Model modele) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
	        if (authentication != null && authentication.isAuthenticated()) {
	            // L'utilisateur est connecté, récupérez ses informations et passez-les au modèle
	            String pseudo = authentication.getName();
	            System.out.println(pseudo);
	            Utilisateur utilisateur = null;
	            
	            // accès au profil selon le pseudo ou selon l'email (l'information rentrée au moment de la connexion)
	            try {
	            	utilisateur = utilisateurService.findByPseudo(pseudo);
	            } catch (EmptyResultDataAccessException ex){
	            	utilisateur = utilisateurService.findByEmail(pseudo);
	            }
	            modele.addAttribute("utilisateur",utilisateur);
	            return "profil-form";
	            
	            /*Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
	            System.out.println("accès au profil utilisateur" + utilisateur);
	            if (utilisateur==null) {
	            	utilisateur = utilisateurService.findByEmail(pseudo);
	            	System.out.println("résultat findByEmail" + utilisateur);
	            } 
	            	modele.addAttribute("utilisateur",utilisateur);
		            return "profil-form";*/
	            
	        } else {
	            // Aucun utilisateur n'est connecté, redirigez vers le formulaire d'inscription ou une autre page appropriée
	        	return "profil-form";
	        }
	}
	
	@GetMapping("/supprimerProfil")
	public String supprimerProfil() {
		String pseudo = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("postmapping supprimerProfil sur " + pseudo);
		utilisateurService.supprimerProfil(pseudo);
		return "redirect:/logout";
	}
	
	@GetMapping("/usersList")
	public String afficherListeUtilisateurs(Model modele) {
		modele.addAttribute("utilisateurs",utilisateurService.findAllUsers());
		return"users-list";
	}
	
	@GetMapping("/afficherUtilisateur")
	public String afficherUtilisateur(@RequestParam String pseudo, Model modele) {
		modele.addAttribute("utilisateur",utilisateurService.findByPseudo(pseudo));
		return "users-details";
	}
	
}
