package com.model;

public class ConsumerGoods extends Product{
	
	private String consumerId;
	private String category;

	public ConsumerGoods(String productId, String productName, String description, double unitPrice,
			String supplierInfo, String consumerId, String category) {
		super(productId, productName, description, unitPrice, supplierInfo);
		this.consumerId = consumerId;
		this.category = category;
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
	@Override
	public String toString() {
		super.toString();
		System.out.printf("%15s",category);
		return "";
	}

}
