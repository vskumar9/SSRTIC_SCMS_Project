package com.model;


public class Product {

	private String productId;
	private String productName;
	private String description;
	private double unitPrice;
	private String supplierInfo;
	
	public Product(String productId, String productName, String description, double unitPrice, String supplierInfo) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.unitPrice = unitPrice;
		this.supplierInfo = supplierInfo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSupplierInfo() {
		return supplierInfo;
	}
	public void setSupplierInfo(String supplierInfo) {
		this.supplierInfo = supplierInfo;
	}
	
	@Override 
	public String toString() {
		System.out.printf("%-15s%-15s%-15s%-15.2f%-15s%-30s", productId, productName, description, unitPrice, supplierInfo);
		return "";
	}
	


}
