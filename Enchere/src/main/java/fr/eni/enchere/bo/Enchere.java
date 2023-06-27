package fr.eni.enchere.bo;

import java.sql.Date;

public class Enchere {

	// Propriétés d'une Enchère
	private Date dateEnchere;
	private Integer montant_enchere;
	
	
	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	public Enchere(Date dateEnchere, Integer montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
	
}
