package fr.eni.enchere.controller.converter;

import org.springframework.core.convert.converter.Converter;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.Utilisateur;

public class StringToUtilisateurConverter implements Converter<String, Utilisateur>{
	
	private UtilisateurService utilisateurService;

	public StringToUtilisateurConverter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Utilisateur convert(String utilId) {
		Integer theId = Integer.parseInt(utilId);
		return utilisateurService.findById(theId);
	}

}
