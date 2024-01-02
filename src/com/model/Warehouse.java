package com.model;
import com.util.ApplicationUtil;

public class Warehouse {

	private String warehouseId;
	private String warehouseName;
	private String location;
	private int capacity;
	public Warehouse(String warehouseName, String location, int capacity) {
		super();
//		this.warehouseId = "WAH"+new ApplicationUtil().generateUniqueId();
		this.warehouseName = warehouseName;
		this.location = location;
		this.capacity = capacity;
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
	@Override
	public String toString() {
		return "WarehouseManagement [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", location="
				+ location + ", capacity=" + capacity + "]";
	}
	

}
