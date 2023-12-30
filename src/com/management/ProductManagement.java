package com.management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;

public class ProductManagement {
	
	public boolean addProduct(Product productObj) throws ClassNotFoundException, SQLException {
		
		try(Connection con = DBConnection.getConnection();){
			if(productObj instanceof IndustrialGoods) {
				try(
						PreparedStatement st = con.prepareStatement("INSERT INTO industrial_goods VALUES ( ?, ?, ?, ?, ?, ?, ?)");
					){
					
					IndustrialGoods ig = (IndustrialGoods)productObj;
					
					st.setString(1, ig.getProductId());
					st.setString(2, ig.getProductName());
					st.setString(3, ig.getDescription());
					st.setDouble(4, ig.getUnitPrice());
					st.setString(5, ig.getSupplierName());
					st.setString(6, ig.getSupplierAddress());
					st.setString(7, ig.getIndustry());
					
					return st.executeUpdate() > 0 ? true : false;
					
				}
				
			}
			else if(productObj instanceof ConsumerGoods) {
				try(
						PreparedStatement st = con.prepareStatement("INSERT INTO consumer_goods VALUES ( ?, ?, ?, ?, ?, ?, ?)");
					){
					
					ConsumerGoods ig = (ConsumerGoods)productObj;
					
					st.setString(1, ig.getProductId());
					st.setString(2, ig.getProductName());
					st.setString(3, ig.getDescription());
					st.setDouble(4, ig.getUnitPrice());
					st.setString(5, ig.getSupplierName());
					st.setString(6, ig.getSupplierAddress());
					st.setString(7, ig.getCategory());
					
					return st.executeUpdate() > 0 ? true : false;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean deleteProduct(String id, String goodsType) throws ClassNotFoundException, SQLException {
		
		try(Connection con = DBConnection.getConnection();){
			if("IndustrialGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("DELETE FROM industrial_goods WHERE productId = ?"))
				{
					st.setString(1, id);
					return st.executeUpdate() > 0 ? true : false;
				}
				
				
			} else if("ConsumerGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("DELETE FROM consumer_goods WHERE productId = ?"))
				{
					st.setString(1, id);
					return st.executeUpdate() > 0 ? true : false;
				}
				
			}
			
		}
				
		return false;
	}
	
	public boolean updateProduct(String goodsType, String id, String productName, String description, double unitPrice,
			String supplierName, String supplierAddress, String industryOrConsumer) throws ClassNotFoundException, SQLException {
		
		try(Connection con = DBConnection.getConnection();){
			if("IndustrialGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("UPDATE industrial_goods SET productName = ?, description = ?, unitPrice = ?, supplierName = ?, supplierAddress = ? industrialGoods = ? WHERE productId = ?"))
				{
					
					st.setString(1, productName);
					st.setString(2, description);
					st.setDouble(3, unitPrice);
					st.setString(4, supplierName);
					st.setString(5, supplierAddress);
					st.setString(6, industryOrConsumer);
					st.setString(7, id);
					
					return st.executeUpdate() > 0 ? true : false;
				}
				
				
			} else if("ConsumerGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("UPDATE consumer_goods SET productName = ?, description = ?, unitPrice = ?, supplierName = ?, supplierAddress = ? consumerGoods = ? WHERE productId = ?"))
				{
					st.setString(1, productName);
					st.setString(2, description);
					st.setDouble(3, unitPrice);
					st.setString(4, supplierName);
					st.setString(5, supplierAddress);
					st.setString(6, industryOrConsumer);
					st.setString(7, id);
					return st.executeUpdate() > 0 ? true : false;
				}
				
			}
			
		}
		
		return false;
	}
	
	
	public ArrayList<Product> viewProducts(String goodsType) throws ClassNotFoundException, SQLException{
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		try(Connection con = DBConnection.getConnection();){
			if("IndustrialGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods"))
				{
					ResultSet rs = st.executeQuery();
					while(rs.next()) {
						list.add(new )
					}
					
					
				}
				
				
			} else if("ConsumerGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods"))
				{
					
					
				}
				
			} else if("All".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods CROSS JOIN consumer_goods"))
				{
					
					
					
					
				}
				
			}
			
		}
		
		return null;
	}
	
	
	

}
