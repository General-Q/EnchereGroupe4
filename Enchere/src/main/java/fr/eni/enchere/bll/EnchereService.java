package fr.eni.enchere.bll;

import java.sql.Date;

import fr.eni.enchere.bo.Enchere;
import jakarta.validation.Valid;

public interface EnchereService {

	
Enchere findById(Integer id);
void debutEnchere(@Valid Enchere enchere, java.util.Date date);
}
