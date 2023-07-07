package fr.eni.enchere.bll;

import java.sql.Date;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import jakarta.validation.Valid;

public interface EnchereService {

void ajouterVente(@Valid ArticleVendu articleVendu);
Enchere findById(Integer noE);
List<Enchere> rechercherEncheresParCategorie(List<ArticleVendu> lstA);
void encherir(Enchere cible);
}
