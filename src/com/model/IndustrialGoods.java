package com.model;


public class IndustrialGoods extends Product{
	private String industry;

	public IndustrialGoods(String productName, String description, double unitPrice,
			String supplierName, String supplierAddress, String industry) {
		super(productName, description, unitPrice, supplierName, supplierAddress);
		this.industry = industry;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}
