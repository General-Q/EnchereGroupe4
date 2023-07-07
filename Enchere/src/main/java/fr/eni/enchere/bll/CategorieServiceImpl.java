package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDAO;

@Service
public class CategorieServiceImpl implements CategorieService {
	
	private CategorieDAO categorieDAO;
	
	public CategorieServiceImpl(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	//a finir steph
	@Override
	public List<Categorie> findAll() {
		return categorieDAO.findAll();
	}
	
	@Override
	public Categorie findById(Integer id) {
		return categorieDAO.findById(id);
	}
	
}
