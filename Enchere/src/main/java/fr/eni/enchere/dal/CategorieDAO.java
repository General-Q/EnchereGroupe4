package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.execptions.CategorieNotFoundException;

public interface CategorieDAO {

	List<Categorie>findAll();
	Categorie read (Integer no_categorie) throws CategorieNotFoundException;
	Categorie findById(Integer id);
}
