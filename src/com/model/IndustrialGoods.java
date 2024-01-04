package com.model;


public class IndustrialGoods extends Product{
	
	private String industryId;
	private String industry;
	

	public IndustrialGoods(String productId, String productName, String description, double unitPrice,
			String supplierInfo, String industryId, String industry) {
		super(productId, productName, description, unitPrice, supplierInfo);
		this.industryId = industryId;
		this.industry = industry;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Override
	public String toString() {
		super.toString();
		System.out.printf("%15s",industry);
		return "";
	}
	
	
	
}
