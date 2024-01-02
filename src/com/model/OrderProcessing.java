package com.model;

import java.util.Date;

import com.util.ApplicationUtil;

public class OrderProcessing {

	private String orderId;
	private String customerId;
	private Date orderDate;
	private double totalAmount;
	private String status;
	public OrderProcessing(String customerId, double totalAmount) {
		super();
//		this.orderId = "ORD"+new ApplicationUtil().generateUniqueId();
		this.customerId = customerId;
		this.orderDate = new Date();
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
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
