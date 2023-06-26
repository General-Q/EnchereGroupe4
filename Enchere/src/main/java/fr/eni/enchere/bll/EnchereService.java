package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Enchere;
import jakarta.validation.Valid;

public interface EnchereService {

	
Enchere findById(Integer id);

void ajouterVente(@Valid Enchere enchere);
}
