package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.OrderProcessing;

public class OrderProcessingManagement {

	public boolean addOrder(OrderProcessing order) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("")
			){
			
		}
		return false;
	}
	
	public boolean deleteOrder(String orderId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("")
			){
			
		}
		return false;
	}
	
	public boolean updateOrder(OrderProcessing order) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("")
			){
			
		}
		return false;
	}
	
	public ArrayList<OrderProcessing> viewOrders() throws ClassNotFoundException, SQLException{
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("")
			){
			
		}
		return null;
	}
	
	public ArrayList<OrderProcessing> searchOrdersByOrderId(String orderId) throws ClassNotFoundException, SQLException{
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("")
			){
			
		}
		return null;
	}
	
	public ArrayList<OrderProcessing> searchOrdersByCustomerId(String customerId) throws ClassNotFoundException, SQLException{
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("")
			){
			
		}
		return null;
	}

	public ArrayList<OrderProcessing> searchOrdersByProductId(String productId) throws ClassNotFoundException, SQLException{
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("")
			){
			
		}
		return null;
	}
	
}
