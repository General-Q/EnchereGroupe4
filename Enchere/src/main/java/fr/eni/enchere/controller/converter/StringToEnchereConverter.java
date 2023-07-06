package fr.eni.enchere.controller.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bo.Enchere;

@Component
public class StringToEnchereConverter implements Converter<String, Enchere>{
	private EnchereService enchereService;
	
	@Autowired
    public StringToEnchereConverter(EnchereService enchereService) {
        this.enchereService = enchereService;
    }

	@Override
	public Enchere convert(String noArticle) {
		Integer theId = Integer.parseInt(noArticle);
		System.out.println("convert");
		return enchereService.findById(theId);
	}
}
