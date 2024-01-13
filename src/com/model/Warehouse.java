package com.model;

public class Warehouse {

	private String warehouseId;
	private String warehouseName;
	private String location;
	private int capacity;
	private int currentCapacity;
	
	public Warehouse(String warehouseId, String warehouseName, String location, int capacity) {
		super();
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.location = location;
		this.capacity = capacity;
		this.currentCapacity = 0;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCurrentCapacity() {
		return currentCapacity;
	}
	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}
	
	
	@Override
	public String toString() {
		return "WarehouseManagement [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", location="
				+ location + ", capacity=" + capacity + "]";
	}
	

}
