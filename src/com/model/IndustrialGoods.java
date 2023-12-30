package com.model;


public class IndustrialGoods extends Product{
	private String industry;

	public IndustrialGoods(String productId, String productName, String description, double unitPrice,
			String supplierName, String supplierAddress, String industry) {
		super(productId, productName, description, unitPrice, supplierName, supplierAddress);
		this.industry = industry;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}
