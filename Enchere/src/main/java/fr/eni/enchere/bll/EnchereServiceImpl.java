package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.EnchereDAO;
import jakarta.validation.Valid;

public class EnchereServiceImpl implements EnchereService{

	private EnchereDAO enchereDAO;
	
	@Override
	public Enchere findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterVente(@Valid Enchere enchere) {
		// TODO Auto-generated method stub
		
	}

}
