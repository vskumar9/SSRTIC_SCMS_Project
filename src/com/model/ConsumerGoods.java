package com.model;

public class ConsumerGoods extends Product{
	
	private String consumerId;
	private String category;
	private String categoryDescription;

	public ConsumerGoods(String productId, String productName, String description, double unitPrice,
			String supplierInfo, String consumerId, String category, String categoryDescription) {
		super(productId, productName, description, unitPrice, supplierInfo);
		this.consumerId = consumerId;
		this.category = category;
		this.setCategoryDescription(categoryDescription);
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	@Override
	public String toString() {
		super.toString();
		System.out.printf("%30s%30s",consumerId, category);
		return "";
	}

}
