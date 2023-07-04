package fr.eni.enchere.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bo.ArticleVendu;

@Component
public class StringToArticleVenduConverter implements Converter<String, ArticleVendu>{
	private ArticleVenduService articleVenduService;
	
	@Override
	public ArticleVendu convert(String artId) {
		Integer theId = Integer.parseInt(artId);
		System.out.println("convert");
		return articleVenduService.findById(theId);
	}
}
