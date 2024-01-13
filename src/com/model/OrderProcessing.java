package com.model;

import java.sql.Timestamp;

public class OrderProcessing {

	private String orderId;
	private String customerId;
	private Timestamp orderDate;
	private double totalAmount;
	private String status;
	public OrderProcessing(String orderId, String customerId, double totalAmount) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = new Timestamp(0);
		this.totalAmount = totalAmount;
		this.status = "Processing";
	}
	public String getOrderId() {
		return orderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void processOrder() {
		this.status = "Processed";
	}
	
	public void cancelOrder() {
		this.status = "Cancelled";
	}
	
	public void updateOrderStatus(String newStatus) {
		this.status = newStatus;
	}
	@Override
	public String toString() {
		return "OrderProcessing [orderId=" + orderId + ", customerId=" + customerId + ", orderDate=" + orderDate
				+ ", totalAmount=" + totalAmount + ", status=" + status + "]";
	}
	
	


}
