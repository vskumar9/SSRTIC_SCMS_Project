package com.model;


public class IndustrialGoods extends Product{
	
	private String industryId;
	private String industry;
	private String industrialDescription;
	

	public IndustrialGoods(String productId, String productName, String description, double unitPrice,
			String supplierInfo, String industryId, String industry, String industrialDescription) {
		super(productId, productName, description, unitPrice, supplierInfo);
		this.industryId = industryId;
		this.industry = industry;
		this.industrialDescription = industrialDescription;
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
	public String getIndustrialDescription() {
		return industrialDescription;
	}
	public void setIndustrialDescription(String industrialDescription) {
		this.industrialDescription = industrialDescription;
	}
	@Override
	public String toString() {
		super.toString();
		System.out.printf("%30s%30s%30s",industryId, industry, industrialDescription);
		return "";
	}
	
	
	
}
