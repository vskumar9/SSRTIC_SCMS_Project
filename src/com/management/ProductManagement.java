package com.management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;
import com.service.ProductService;

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
					
					return st.executeUpdate() > 0;
					
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
					
					return st.executeUpdate() > 0;
					
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
					return st.executeUpdate() > 0;
				}
				
				
			} else if("ConsumerGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("DELETE FROM consumer_goods WHERE productId = ?"))
				{
					st.setString(1, id);
					return st.executeUpdate() > 0;
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
					
					return st.executeUpdate() > 0;
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
					return st.executeUpdate() > 0;
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
						String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"IndustrialGoods";
						list.add(new ProductService().parseProductDetails(productDetails));
//						list.add(new IndustrialGoods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7)));
					}
				}
				
			} else if("ConsumerGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods"))
				{
					ResultSet rs = st.executeQuery();
					while(rs.next()) {
						String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"ConsumerGoods";
						list.add(new ProductService().parseProductDetails(productDetails));
//						list.add(new ConsumerGoods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7)));
					}
				}
				
			} else if("All".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods"))
				{
					ResultSet rs = st.executeQuery();
					while(rs.next()) {
						String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"IndustrialGoods";
						list.add(new ProductService().parseProductDetails(productDetails));
					}
				}
				try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods"))
				{
					ResultSet rs = st.executeQuery();
					while(rs.next()) {
						String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"ConsumerGoods";
						list.add(new ProductService().parseProductDetails(productDetails));
					}
				}
				
			}
		}
		
		Collections.sort(list, Comparator.comparing(Product::getProductId));	
		return list;
	}

	
	public ArrayList<Product> serachByProductId(String prouctId) throws ClassNotFoundException, SQLException{

		ArrayList<Product> list = new ArrayList<Product>();

		
		
		try(Connection con = DBConnection.getConnection();){
			
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods WHERE productId = ?"))
			{
				st.setString(1, prouctId);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods WHERE productId = ?"))
			{
				st.setString(1, prouctId);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
		}
		
		return list;
		
	}
	
	public ArrayList<Product> serachByProductName(String prouctName) throws ClassNotFoundException, SQLException{

		ArrayList<Product> list = new ArrayList<Product>();

		
		
		try(Connection con = DBConnection.getConnection();){
			
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods WHERE LOWER(productName) = LOWER(?)"))
			{
				st.setString(1, prouctName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods WHERE LOWER(productName) = LOWER(?)"))
			{
				st.setString(1, prouctName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
		}
		
		return list;
		
	}
	
	
	public ArrayList<Product> serachBySupplierName(String supplierName) throws ClassNotFoundException, SQLException{

		ArrayList<Product> list = new ArrayList<Product>();

		
		
		try(Connection con = DBConnection.getConnection();){
			
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods WHERE LOWER(supplierName) = LOWER(?)"))
			{
				st.setString(1, supplierName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods WHERE LOWER(supplierName) = LOWER(?)"))
			{
				st.setString(1, supplierName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
		}
		
		Collections.sort(list, Comparator.comparing(Product::getProductId));
		
		return list;
		
	}
	

}
