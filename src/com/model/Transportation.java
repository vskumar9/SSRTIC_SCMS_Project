package com.model;

public class Transportation {

	private String shipmentId;
	private String orderId;
	private String carrierId;
	private String shipmentStatus;
	public Transportation(String orderId, String carrierId) {
		super();
//		this.shipmentId = "SHP"+new ApplicationUtil().generateUniqueId();
		this.orderId = orderId;
		this.carrierId = carrierId;
		this.shipmentStatus = "Pending...";
	}
	public String getShipmentId() {
		return shipmentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	public String getShipmentStatus() {
		return shipmentStatus;
	}
	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}
	@Override
	public String toString() {
		return "Transportation [shipmentId=" + shipmentId + ", orderId=" + orderId + ", carrierId=" + carrierId
				+ ", shipmentStatus=" + shipmentStatus + "]";
	}
	

}
