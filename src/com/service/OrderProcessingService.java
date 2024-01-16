package com.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidException;
import com.management.OrderProcessingManagement;
import com.management.ProductManagement;
import com.model.OrderProcessing;
import com.util.ApplicationUtil;

public class OrderProcessingService {
	
	OrderProcessingManagement om = new OrderProcessingManagement();
	ProductManagement pm = new ProductManagement();
	ApplicationUtil util = new ApplicationUtil();
	

	public String addOrder(String customerId, String orderDetails) {
		
		try {
			double totalAmount = 0;
			String[] productDetails = orderDetails.split("-");
			String[] products = new String[productDetails.length];
			int[] quantities = new int[productDetails.length];
			for(int i = 0; i < productDetails.length; i++) {
				String[] product = productDetails[i].split(":");
				totalAmount += (pm.productPrice(product[0]) * Integer.valueOf(product[1]));
				products[i] = product[0];
				quantities[i] = Integer.valueOf(product[1]);
			}
			String orderId = generateUniqueId();
			if(om.addOrder(new OrderProcessing(orderId, customerId, new Timestamp(0), totalAmount, "Pending"))) {
				for(int i = 0; i < products.length; i++) {
					om.addOrderDetails(orderId, products[i], quantities[i]);
				}
				return customerId+" Order Id: "+orderId;
			}
			return null;
			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	public boolean deleteOrder(String orderId) {
		try {
			if(util.validateOrderId(orderId)) {
				if(!om.ExistOrdersInOrderDetails(orderId))
					return om.deleteOrder(orderId);
				System.out.println("This order doesn't delete.");
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteProducts(String orderId) {
		try {
			if(util.validateOrderId(orderId)) {
				return om.deleteOrderDetails(orderId);
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateOrder(String orderId, String status) {
		try {
			if(util.validateOrderId(orderId)){
				return om.updateOrder(new OrderProcessing(orderId, null, null, 0, status));
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public ArrayList<OrderProcessing> viewOrders() {
		try {
			return om.viewOrders();
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public ArrayList<OrderProcessing> searchOrdersByOrderId(String orderId) {
		try {
			if(util.validateOrderId(orderId)) {
				return om.searchOrdersByOrderId(orderId);			
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<String> searchProductsByOrderId(String orderId) {
		try {
			if(util.validateOrderId(orderId)) {
				return om.searchProductsByOrderId(orderId);			
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<OrderProcessing> searchOrdersByCustomerId(String customerId) {
		try {
			if(util.validateCustomerId(customerId)) {
				return om.searchOrdersByCustomerId(customerId);
			}
			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public ArrayList<String> searchOrdersByProductId(String productId) {
		try {
			if(util.validateProductId(productId)) {
				return om.searchOrdersByProductId(productId);
			}
			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<String> searchOrdersByProductName(String ProductName){
		try {
				return om.searchOrdersByProductName(ProductName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean ExistCustomerId(String customerId) {
		try {
			if(util.validateCustomerId(customerId)){
				return om.ExistCustomerId(customerId);				
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public String generateUniqueId() {
	       return "ORDR"+generateSCMId();
	    }
	private String generateSCMId() {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
     String timestamp = dateFormat.format(new Date());
	    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
	    return timestamp + String.format("%03d", randomSuffix);
	    }
}
