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
	
	@GetMapping({"/inscription","/profil"})
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
		return"redirect:/accueil";
	}
	
	/*@PostMapping("/enregistrerProfil")
	public String EnregistrerProfil(Utilisateur utilisateur) {
		System.out.println("postmapping enregistrerProfil OK");
		utilisateurService.saveProfil(utilisateur);
		return"redirect:/accueil";
	}*/
	
	@PostMapping("/enregistrerProfil")
	public String EnregistrerProfil(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult, Principal principal) {
	
		// Modification d'un profil existant
		try {
			// Vérification si l'utilisateur est connecté
			String username = principal.getName();
			System.out.println("ouverture du process de modification");
			String pseudo = utilisateur.getPseudo();
			String email = utilisateur.getEmail();
			System.out.println("username = " + username);
			System.out.println("utilisateur.getPseudo() = " + pseudo);
			System.out.println("utilisateur.getEmail() = " + email);
			
			// Vérification si le Pseudo a été modifié (je rentre dans le if si le Pseudo a été modifié)
			if (!username.equals(pseudo) && !utilisateurService.findByPseudoOrEmail(username).getEmail().equals(email)) {
				System.out.println("le pseudo a été modifié");
				System.out.println("utilisateurService.findEmailByPseudo(username).getEmail() = " + utilisateurService.findEmailByPseudo(username).getEmail());
				
				// Vérification si le Pseudo modifié est disponible (je rentre dans le if si le Pseudo modifié n'est pas disponible)
				if (utilisateurService.pseudoUnique(pseudo)) {
					bindingResult.rejectValue("pseudo", "pseudo.alreadyTaken", "Le pseudo modifié est déjà pris");
					return "profil-form";
					}
			}
			// Vérification si l'e-mail a été modifié (je rentre dans le if si l'email a été modifié)
			if(!username.equals(email) && !utilisateurService.findByPseudoOrEmail(username).getPseudo().equals(pseudo)) {
				System.out.println("l'e-mail a été modifié");
				// Vérification si l'email modifié est disponible (je rentre dans le if si l'e-mail modifié n'est pas disponible)
				if (utilisateurService.emailUnique(email)) {
					bindingResult.rejectValue("email", "email.alreadyTaken", "Ce mail modifié est déjà associé à un autre utilisateur");
					return "profil-form";
				}
				// le Pseudo modifié et l'e-mail modifié sont diponibles
				utilisateurService.saveProfil(utilisateur);
				System.out.println("enregistrement du pseudo ou du mail modifié");
				return"redirect:/accueil";
			} else {
			// Ni le Pseudo ni l'e-mail n'ont été modifié donc je save
			utilisateurService.saveProfil(utilisateur);
			System.out.println("enregistrement des nouvelles données");
			return"redirect:/accueil";
			}
		}
		catch (NullPointerException ex) {
		// Inscription d'un nouveau profil
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
					bindingResult.rejectValue("email", "email.alreadyTaken", "Ce mail est déjà associé à un autre utilisateur");
					return "profil-form";
				}
				
				// Ok pour l'inscription
				utilisateurService.saveProfil(utilisateur);
				System.out.println("l'inscription est finalisée");
				return"redirect:/accueil";}
		}
		
		/////////////////////////////////////////////////////////////////////
		/*
		    try {
		        String pseudo = principal.getName();
		        System.out.println("Ouverture du process de modification");

		        // Vérification si le pseudo a été modifié et s'il est disponible
		        if (!utilisateur.getPseudo().equals(utilisateurService.findByPseudoOrEmail(pseudo)) &&
		                utilisateurService.pseudoUnique(utilisateur.getPseudo())) {
		            System.out.println(utilisateur.getPseudo());
		            bindingResult.rejectValue("pseudo", "pseudo.alreadyTaken", "Le pseudo est déjà pris");
		            return "profil-form";
		        }

		        // Vérification si l'e-mail a été modifié et s'il est disponible
		        if (!utilisateur.getEmail().equals(utilisateurService.getEmailFromDatabase(pseudo)) &&
		                utilisateurService.emailUnique(utilisateur.getEmail())) {
		            System.out.println(utilisateur.getEmail());
		            bindingResult.rejectValue("email", "email.alreadyTaken", "Cette adresse e-mail est déjà associée à un autre utilisateur");
		            return "profil-form";
		        }

		        // Enregistrement du profil
		        System.out.println("PostMapping enregistrerProfil OK");
		        utilisateurService.saveProfil(utilisateur);
		        return "redirect:/accueil";
		    } catch (NullPointerException ex) {
		        // Traitement de l'exception
		        // ...
		    }
		}
		
		
	/*try {
		String pseudo = principal.getName();
		System.out.println("postmapping enregistrerProfil OK");
		utilisateurService.saveProfil(utilisateur);
		redirectAttributes.addFlashAttribute("successMessage", "L'inscription est finalisée. Vous pouvez dorénavant vous connecter.");
		return"redirect:/accueil";
	} catch (NullPointerException ex) {*/

		
		/*if (bindingResult.hasErrors()) {
			return "profil-form";
		}*/
		
		/*System.out.println("postmapping enregistrerProfil OK");
		utilisateurService.saveProfil(utilisateur);
		redirectAttributes.addFlashAttribute("successMessage", "L'inscription est finalisée. Vous pouvez dorénavant vous connecter.");
		return"redirect:/accueil";*/

	/*
	@PostMapping("/enregistrerProfil")
	public String EnregistrerProfil(@Valid Utilisateur utilisateur, BindingResult bindingResult, @RequestParam("motDePasseConfirmation") String motDePasseConfirmation) {
	    if (!utilisateur.getMotDePasse().equals(motDePasseConfirmation)) {
	        bindingResult.rejectValue("motDePasseConfirmation", "error.utilisateur", "Le mot de passe et la confirmation du mot de passe ne correspondent pas");
	    }

	    if (bindingResult.hasErrors()) {
	        return "profil-form";
	    }

	    utilisateurService.saveProfil(utilisateur);
	    return "redirect:/accueil";
	}*/

	
	@GetMapping("/modifierProfil")
	public String modifierProfil(Model modele) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
	        if (authentication != null && authentication.isAuthenticated()) {
	            // L'utilisateur est connecté, récupérez ses informations et passez-les au modèle
	            String pseudo = authentication.getName();
	            System.out.println(pseudo);
	            Utilisateur utilisateur = null;
	            
	            // accès au profil selon le pseudo ou selon l'email (l'information rentrée au moment de la connexion)
	            utilisateur = utilisateurService.findByPseudoOrEmail(pseudo);
	            modele.addAttribute("utilisateur",utilisateur);
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
		modele.addAttribute("utilisateurs",utilisateurService.findAllUsers());
		return"users-list";
	}
	
	@GetMapping("/afficherUtilisateur")
	public String afficherUtilisateur(@RequestParam String pseudo, Model modele) {
		modele.addAttribute("utilisateur",utilisateurService.findByPseudoOrEmail(pseudo));
		return "users-details";
	}
	
	@GetMapping("/loginCustom")
	public String connexion(Principal principal) {
		try {
        	String pseudo = principal.getName();
        	return "redirect:/accueil";
		} catch (NullPointerException ex) {
			return "redirect:/login";
		}
	}
	
}
