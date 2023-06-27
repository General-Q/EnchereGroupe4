package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.EnchereDAO;
import fr.eni.enchere.execptions.EnchereNotFoundException;
import jakarta.validation.Valid;

public class EnchereServiceImpl implements EnchereService{

	private EnchereDAO enchereDAO;
	/*
	@Override
	public Enchere findById(Integer id) {
	Enchere enchere = null;
	try {
		enchere = enchereDAO.read(id);
	}catch (EnchereNotFoundException e) {
		
	}
		return enchere;
	}
*/
	@Override
	public void ajouterVente(@Valid Enchere enchere) {
		
		
	}
	@Override
	public Enchere findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
