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
	
	// Create objects. it's help's to accessing class inside methods
	OrderProcessingManagement om = new OrderProcessingManagement();
	ProductManagement pm = new ProductManagement();
	ApplicationUtil util = new ApplicationUtil();
	
	// Helper method to add new orders details and order product details
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
//			Generate New order id
			String orderId = generateUniqueId();
//			Checking new order is create or not if YES condition execute otherwise return null;
			if(om.addOrder(new OrderProcessing(orderId, customerId, new Timestamp(0), totalAmount, "Pending"))) {
//				add new order ordered Products with quantity
				for(int i = 0; i < products.length; i++) {
					om.addOrderDetails(orderId, products[i], quantities[i]);
				}
//				return customer id and order id
				return customerId+" Order Id: "+orderId;
			}
			return null;
			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to delete orders details
	public boolean deleteOrder(String orderId) {
		try {
//			checking order id
			if(util.validateOrderId(orderId)) {
//				checking order product is exists or not
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
	
	// Helper method to delete ordered product details 
	public boolean deleteProducts(String orderId) {
		try {
//			checking order id
			if(util.validateOrderId(orderId)) {
//				delete ordered products
				return om.deleteOrderDetails(orderId);
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	// Helper method to update order status
	public boolean updateOrder(String orderId, String status) {
		try {
//			checking order id
			if(util.validateOrderId(orderId)){
//				update order status return true or false
				return om.updateOrder(new OrderProcessing(orderId, null, null, 0, status));
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	// Helper method to retrieve ordered details 
	public ArrayList<OrderProcessing> viewOrders() {
		try {
			return om.viewOrders();
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to retrieve Orders details specific orderId
	public ArrayList<OrderProcessing> searchOrdersByOrderId(String orderId) {
		try {
//			checking order id
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
	
	// Helper method to retrieve Orders product details specific order id
	public ArrayList<String> searchProductsByOrderId(String orderId) {
		try {
//			checking order id
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
	
	// Helper method to retrieve Orders details specific customer
	public ArrayList<OrderProcessing> searchOrdersByCustomerId(String customerId) {
		try {
//			checking customer id
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
	
	// Helper method to retrieve Orders details specific ProductId
	public ArrayList<String> searchOrdersByProductId(String productId) {
		try {
//			checking product id
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
	
	// Helper method to retrieve Orders details specific Product Name
	public ArrayList<String> searchOrdersByProductName(String ProductName){
		try {
				return om.searchOrdersByProductName(ProductName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to customer exists or not checking
	public boolean ExistCustomerId(String customerId) {
		try {
//			checking customer id
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
	
	// Helper method to generate Order unique id
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
