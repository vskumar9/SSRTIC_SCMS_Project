package com.model;

public class Transportation {

	private String shipmentId;
	private String orderId;
	private String carrierId;
	private String shipmentStatus;
	public Transportation(String shipmentId, String orderId, String carrierId, String shipmentStatus) {
		super();
		this.shipmentId = shipmentId;
		this.orderId = orderId;
		this.carrierId = carrierId;
		this.shipmentStatus = shipmentStatus;
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
		System.out.printf("%-25s%-25s%-25s%-25s", shipmentId, orderId, carrierId, shipmentStatus);
		return "";
	}
	

}
