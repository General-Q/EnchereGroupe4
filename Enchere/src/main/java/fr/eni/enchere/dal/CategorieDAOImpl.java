package fr.eni.enchere.dal;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.execptions.CategorieNotFoundException;

@Repository
public class CategorieDAOImpl implements CategorieDAO{

	private static final String FIND_ALL = "select no_categorie, libelle FROM CATEGORIES";
	
	@Autowired
	private NamedParameterJdbcTemplate npjt;
	
	@Override
	public List<Categorie> findAll() {
		List<Categorie>categories = npjt.query(FIND_ALL, new BeanPropertyRowMapper<>(Categorie.class));
		
		return categories;
	}

	@Override
	public Categorie read(Integer no_categorie)throws CategorieNotFoundException {
		Map<String, Object> params = new HashMap<>();
		params.put("no_categorie", no_categorie);
		Categorie categorie = null;
		try {
			categorie = npjt.queryForObject(FIND_ALL, params, new BeanPropertyRowMapper<>(Categorie.class));
			}catch(EmptyResultDataAccessException exc) {
				throw new CategorieNotFoundException();
			}
		return categorie;
	}

}
