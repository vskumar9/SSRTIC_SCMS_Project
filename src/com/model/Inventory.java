package com.model;
import java.sql.Timestamp;

public class Inventory {

	private String inventoryId;
	private String productId;
	private long quantityInStock;
	private Timestamp lastStockUpdate;
	public Inventory(String inventoryId, String productId, long quantityInStock, Timestamp lastStockUpdate) {
		super();
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public long getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public Timestamp getLastStockUpdate() {
		return lastStockUpdate;
	}	
	public void setLastStockUpdate(Timestamp lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}
	
	@Override
	public String toString() {
		System.out.printf("%-25s%-50s%-15s%-15s", inventoryId, productId, quantityInStock, lastStockUpdate);
		return "";
	}


}
