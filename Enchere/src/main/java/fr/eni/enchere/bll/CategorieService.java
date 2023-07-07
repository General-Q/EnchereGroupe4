package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;

public interface CategorieService {

	Categorie findById(Integer id);

	List<Categorie> findAll();
}
