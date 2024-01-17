package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.model.OrderProcessing;

public class OrderProcessingManagement {

	// Helper method to add new Orders details
	public boolean addOrder(OrderProcessing order) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("INSERT INTO orders VALUES(?, ?, ?, ?, ?)");
			){
			
			st.setString(1, order.getOrderId());
			st.setString(2, order.getCustomerId());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            st.setTimestamp(3, timestamp);
			st.setDouble(4, order.getTotalAmount());
			st.setString(5, order.getStatus());
			
			return st.executeUpdate()>0;
			
		}
	}
	
	// Helper method to add new Order products details
	public boolean addOrderDetails(String orderId, String productId, int quantity) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("INSERT INTO order_details VALUES(?, ?, ?)");
			){
			
			st.setString(1, orderId);
			st.setString(2, productId);
			st.setInt(3, quantity);
			
			return st.executeUpdate()>0;
			
		}
	}
	
	// Helper method to delete Orders details
	public boolean deleteOrder(String orderId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("DELETE FROM orders WHERE LOWER(orderId) = LOWER(?)");
			){
			
			st.setString(1, orderId);
			
			return st.executeUpdate()>0;
			
		}
	}
	
	// Helper method to delete Order products details
	public boolean deleteOrderDetails(String orderId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("DELETE FROM order_details WHERE LOWER(orderId) = LOWER(?)");
			){
			
			st.setString(1, orderId);
			
			return st.executeUpdate()>0; 
		}
	}
	
	// Helper method to update Orders status
	public boolean updateOrder(OrderProcessing order) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("UPDATE orders SET orderStatus = ? WHERE LOWER(orderId) = LOWER(?)");
			){
			
			st.setString(1, order.getStatus());
			st.setString(2, order.getOrderId());
			
			return st.executeUpdate()>0;
			
		}
	}
	
	// Helper method to retrieve Orders details
	public ArrayList<OrderProcessing> viewOrders() throws ClassNotFoundException, SQLException{
		ArrayList<OrderProcessing> list = new ArrayList<OrderProcessing>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM orders");
			){
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(new OrderProcessing(rs.getString("orderId"), rs.getString("customerId"), rs.getTimestamp("orderDate"), rs.getDouble("totalAmount"), rs.getString("orderStatus")));
			}
			return list;			
		}
	}
	
	// Helper method to retrieve Orders details specific orderId
	public ArrayList<OrderProcessing> searchOrdersByOrderId(String orderId) throws ClassNotFoundException, SQLException{
		ArrayList<OrderProcessing> list = new ArrayList<OrderProcessing>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM orders WHERE LOWER(orderId) = LOWER(?)")
			){
			
			st.setString(1, orderId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(new OrderProcessing(rs.getString("orderId"), rs.getString("customerId"), rs.getTimestamp("orderDate"), rs.getDouble("totalAmount"), rs.getString("orderStatus")));
			}
			return list;
		}
	}
	
	// Helper method to retrieve Orders product details specific order
	public ArrayList<String> searchProductsByOrderId(String orderId) throws ClassNotFoundException, SQLException{
		ArrayList<String> list = new ArrayList<String>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT p.productId, p.productName, p.unitPrice, od.quantity \r\n"
						+ "		FROM orders o\r\n"
						+ "		JOIN customer c ON o.customerId = c.customerId\r\n"
						+ "		JOIN order_details od ON o.orderId = od.orderId\r\n"
						+ "		JOIN products p ON od.productId = p.productId\r\n"
						+ "		WHERE LOWER(o.orderId) = LOWER(?)")
			){
			
			st.setString(1, orderId);
			
			ResultSet rs = st.executeQuery();
			String OrderDetails = "%-30s%-30s%-15.2f%-15d%-15.2f";
			
			while(rs.next()) {
				double price = rs.getDouble("unitPrice");
				int quantity = rs.getInt("quantity");
				double totalAmount = price*quantity;
//				String OrderDetails = "%-30s%-30s%-15.2f%-15d%-15.2f"+":"+rs.getString("productId")+":"+rs.getString("productName")+":"+price+":"+quantity+":"+totalAmount;
				list.add(String.format(OrderDetails, rs.getString("productId"), rs.getString("productName"), price, quantity, totalAmount));
			}
			return list;
		}
		
		
	}
	
	// Helper method to retrieve Orders details specific customer
	public ArrayList<OrderProcessing> searchOrdersByCustomerId(String customerId) throws ClassNotFoundException, SQLException{
		ArrayList<OrderProcessing> list = new ArrayList<OrderProcessing>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM orders WHERE LOWER(customerId) = LOWER(?)")
			){
			
			st.setString(1, customerId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
				list.add(new OrderProcessing(rs.getString("orderId"), rs.getString("customerId"), rs.getTimestamp("orderDate"), rs.getDouble("totalAmount"), rs.getString("orderStatus")));
			}
			return list;
		}
	}
	
	// Helper method to retrieve Orders details specific ProductId
	public ArrayList<String> searchOrdersByProductId(String ProductId) throws ClassNotFoundException, SQLException{
		ArrayList<String> list = new ArrayList<String>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT o.orderId, o.customerId, o.orderDate, p.productName, od.quantity, p.unitPrice , o.orderStatus\r\n"
						+ "FROM orders o\r\n"
						+ "JOIN order_details od ON o.orderId = od.orderId\r\n"
						+ "JOIN products p ON od.productId = p.productId\r\n"
						+ "WHERE LOWER(p.productId) = LOWER(?)\r\n"
						+ "");
			){
			
			st.setString(1, ProductId);
			
			ResultSet rs = st.executeQuery();
			String OrderDetails = "%-25s%-25s%-30s%-30s%-15d%-20.2f%-20s";
			
			while(rs.next()) {
				
				list.add(String.format(OrderDetails, rs.getString("orderId"), rs.getString("customerId"), rs.getTimestamp("orderDate"), rs.getString("productName"), rs.getInt("quantity"), rs.getDouble("unitPrice"), rs.getString("orderStatus")));
			}
			return list;			
		}
	}
	
	// Helper method to retrieve Orders details specific Product Name
	public ArrayList<String> searchOrdersByProductName(String ProductName) throws ClassNotFoundException, SQLException{
		ArrayList<String> list = new ArrayList<String>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT o.orderId, o.customerId, o.orderDate, p.productName, od.quantity, p.unitPrice , o.orderStatus\r\n"
						+ "FROM orders o\r\n"
						+ "JOIN order_details od ON o.orderId = od.orderId\r\n"
						+ "JOIN products p ON od.productId = p.productId\r\n"
						+ "WHERE p.productName LIKE ?\r\n"
						+ "");
			){
			
			st.setString(1, "%"+ProductName+"%");
			
			ResultSet rs = st.executeQuery();
			String OrderDetails = "%-25s%-25s%-30s%-30s%-15d%-20.2f%-20s";
			
			while(rs.next()) {
				list.add(String.format(OrderDetails, rs.getString("orderId"), rs.getString("customerId"), rs.getTimestamp("orderDate"), rs.getString("productName"), rs.getInt("quantity"), rs.getDouble("unitPrice"), rs.getString("orderStatus")));

			}
			return list;
		}
	}
	
	// Helper method to customer exists or not checking
	public boolean ExistCustomerId(String customerId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM customer WHERE LOWER(customerId) = LOWER(?)");
			){
			
			st.setString(1, customerId);
			
			return st.executeQuery().next();
		}
	}
	
	// Helper method to exists orders checking order details specific orderId
	public boolean ExistOrdersInOrderDetails(String orderId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM order_details WHERE LOWER(orderId) = LOWER(?)");
			){
			
			st.setString(1, orderId);
			
			return st.executeQuery().next();
		}
	}
	
	
}
