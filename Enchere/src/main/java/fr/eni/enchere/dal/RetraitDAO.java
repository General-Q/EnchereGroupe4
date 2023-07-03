package fr.eni.enchere.dal;

import java.security.Principal;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;



public interface RetraitDAO {

void adresseRetrait(Principal principal, Retrait retrait);

//Retrait findById(Integer no_article);
}
