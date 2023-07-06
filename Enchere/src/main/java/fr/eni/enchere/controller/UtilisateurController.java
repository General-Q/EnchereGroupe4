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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping({ "/inscription", "/profil" })
	public String inscription(@ModelAttribute Utilisateur utilisateur, Principal principal) {
		try {
			String pseudo = principal.getName();
			System.out.println("utilisateur identifié");
			return "redirect:/modifierProfil";
		} catch (NullPointerException ex) {
			System.out.println("utilisateur non identifié");
			return "profil-form";
		}
	}

	@GetMapping("/layout.css")
	public String RenvoiApresConnexion() {
		return "redirect:/accueil";
	}

	@PostMapping("/enregistrerProfil")
	public String EnregistrerProfil(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult,
			Principal principal) {

		/*if(bindingResult.hasErrors()) {
			System.out.println("erreurs");
			return"profil-form";
		}*/
		
		// Modification d'un profil existant
		try {
			// Vérification si l'utilisateur est connecté
			String username = principal.getName();
			System.out.println("ouverture du process de modification");
			String pseudo = utilisateur.getPseudo();
			String email = utilisateur.getEmail();

			// Vérification si le Pseudo a été modifié (je rentre dans le if si le Pseudo a été modifié)
			if (!username.equals(pseudo)
					&& !utilisateurService.findByPseudoOrEmail(username).getPseudo().equals(pseudo)) {
				System.out.println("le pseudo a été modifié");

				// Vérification si le Pseudo modifié est disponible (je rentre dans le if si le Pseudo modifié n'est pas disponible)
				if (utilisateurService.pseudoUnique(pseudo)) {
					System.out.println("le pseudo modifié n'est pas disponible");
					bindingResult.rejectValue("pseudo", "Le pseudo modifié est déjà pris");
					return "profil-form";
				}
			}
			// Vérification si l'e-mail a été modifié (je rentre dans le if si l'email a été modifié)
			if (!username.equals(email) && !utilisateurService.findByPseudoOrEmail(username).getEmail().equals(email)) {
				System.out.println("l'email a été modifié");
				// Vérification si l'email modifié est disponible (je rentre dans le if si l'e-mail modifié n'est pas disponible)
				if (utilisateurService.emailUnique(email)) {
					System.out.println("l'email modifié n'est pas disponible");
					bindingResult.rejectValue("email","Ce mail modifié est déjà associé à un autre utilisateur");
					return "profil-form";
				}
				
			}
				// le Pseudo modifié et l'e-mail modifié sont diponibles
				utilisateurService.saveProfil(utilisateur);
				System.out.println("enregistrement des nouvelles données");
				return "redirect:/logout";
			
		// Inscription d'un nouveau profil
		} catch (NullPointerException ex) {
			System.out.println("ouverture du process d'inscription");

			// Vérification si le pseudo est disponible (je rentre dans le if si le pseudo est déjà pris)
			if (utilisateurService.pseudoUnique(utilisateur.getPseudo())) {
				System.out.println(utilisateur.getPseudo());
				bindingResult.rejectValue("pseudo", "pseudo.alreadyTaken", "Le pseudo est déjà pris");
				return "profil-form";
			}

			// Vérification si l'email est disponible (je rentre dans le if si l'e-mail est déjà pris)
			if (utilisateurService.emailUnique(utilisateur.getEmail())) {
				System.out.println(utilisateur.getEmail());
				bindingResult.rejectValue("email", "email.alreadyTaken",
						"Ce mail est déjà associé à un autre utilisateur");
				return "profil-form";
			}

			// Ok pour l'inscription
			utilisateurService.saveProfil(utilisateur);
			System.out.println("l'inscription est finalisée");
			return "redirect:/accueil";
		}
	}

	@GetMapping("/modifierProfil")
	public String modifierProfil(Model modele) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			// L'utilisateur est connecté, récupérez ses informations et passez-les au modèle
			String pseudo = authentication.getName();
			Utilisateur utilisateur = null;

			// accès au profil selon le pseudo ou selon l'email (l'information rentrée au moment de la connexion)
			utilisateur = utilisateurService.findByPseudoOrEmail(pseudo);
			modele.addAttribute("utilisateur", utilisateur);
			return "profil-form";

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
		modele.addAttribute("utilisateurs", utilisateurService.findAllUsers());
		return "users-list";
	}

	@GetMapping("/afficherUtilisateur")
	public String afficherUtilisateur(@RequestParam String pseudo, Model modele) {
		modele.addAttribute("utilisateur", utilisateurService.findByPseudoOrEmail(pseudo));
		return "users-details";
	}
}
