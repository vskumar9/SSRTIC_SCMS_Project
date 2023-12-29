package com.model;
import java.util.Date;

public class Inventory {

	private String productId;
	private int quantityInStock;
	private Date lastStockUpdate;
	public Inventory(String productId, int quantityInStock, Date lastStockUpdate) {
		super();
		this.productId = productId;
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
	}
	public String getProductId() {
		return productId;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public Date getLastStockUpdate() {
		return lastStockUpdate;
	}
	
	public void updateStock(int quantity) {
		this.quantityInStock += quantity;
		this.lastStockUpdate = new Date();
	}
	@Override
	public String toString() {
		return "InventoryManagement [productId=" + productId + ", quantityInStock=" + quantityInStock
				+ ", lastStockUpdate=" + lastStockUpdate + "]";
	}


}
