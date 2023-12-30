package com.model;

public class ConsumerGoods extends Product{
	
	private String category;

	public ConsumerGoods(String productId, String productName, String description, double unitPrice,
			String supplierName, String supplierAddress, String category) {
		super(productId, productName, description, unitPrice, supplierName, supplierAddress);
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
