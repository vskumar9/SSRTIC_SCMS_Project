package com.model;


public class Product {

	private String productId;
	private String productName;
	private String description;
	private double unitPrice;
	private String supplierName;
	private String supplierAddress;
	
	public Product(String productId, String productName, String description, double unitPrice,
			String supplierName, String supplierAddress) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.unitPrice = unitPrice;
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
	}
	public String getProductId() {
		return productId;
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
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	@Override
	public String toString() {
		System.out.printf("%-15s%-15s%-15s%-15.2f%-15s%-30s", productId, productName, description, unitPrice, supplierName, supplierAddress);
		return "";
	}
	


}
