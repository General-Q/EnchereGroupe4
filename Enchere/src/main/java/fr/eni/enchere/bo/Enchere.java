package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate dateEnchere;
	private Integer montant_enchere;
	
	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	public Enchere(LocalDate dateEnchere, Integer montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
	
}
