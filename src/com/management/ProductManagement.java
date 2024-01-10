package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.exception.InvalidProduct;
import com.model.Product;
import com.service.ProductService;

public class ProductManagement {
	
	public boolean addProduct(String id, String name, String description, double unitPrice) throws SQLException, ClassNotFoundException {

		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?)");
			){
					pro.setString(1, id);
					pro.setString(2, name);
					pro.setString(3, description);
					pro.setDouble(4, unitPrice);
					
					return pro.executeUpdate()>0;
					
		}
			
	}
	
	public boolean addIndustry(String id, String industry, String description) throws SQLException, ClassNotFoundException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("INSERT INTO industrial_goods VALUES(?, ?, ?)");
			){
				
				pro.setString(1, id);
				pro.setString(2, industry);
				pro.setString(3, description);
				
				return pro.executeUpdate()>0;
		}		
		
	}
	
	public boolean addConsumer(String id, String consumer, String description) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("INSERT INTO consumer_goods VALUES(?, ?, ?");
			){
			
			pro.setString(1, id);
			pro.setString(2, consumer);
			pro.setString(3, description);
			return pro.executeUpdate()>0;
		}
	}
	
	public boolean deleteProduct(String productId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("DELETE FROM products WHERE LOWER(productId) = LOWER(?)");
			){
			
			pro.setString(1, productId);
			return pro.executeUpdate() > 0;
		}
	}
	
	public boolean deleteIndustry(String industryId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("DELETE FROM industrial_goods WHERE LOWER(industryId) = LOWER(?)");
			){
			
			pro.setString(1, industryId);
			return pro.executeUpdate() > 0;
		}
	}
	
	public boolean deleteConsumer(String ConsumerId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("DELETE FROM consumer_goods WHERE LOWER(consumerId) = LOWER(?)");
			){
			
			pro.setString(1, ConsumerId);
			return pro.executeUpdate() > 0;
		}
	}
	
	public boolean updateProduct(String id, String name, String description, double unitPrice) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("UPDATE products SET productName = ?, description = ?, unitPrice = ? WHERE LOWER(productId) = LOWER(?)")
			){
			
			pro.setString(1, name);
			pro.setString(2, description);
			pro.setDouble(3, unitPrice);
			pro.setString(6, id);
			
			return pro.executeUpdate()>0;
		}
	}
	
	public boolean updateIndustry(String id, String industry, String description) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("UPDATE industrial_goods SET industry = ?, industry_description = ? WHERE LOWER(industryId) = LOWER(?)")
			){
			
			pro.setString(1, industry);
			pro.setString(2, description);
			pro.setString(3, id);
			
			return pro.executeUpdate()>0;
		}
	}
	
	public boolean updateConsumer(String id, String cosumer, String description) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("UPDATE consumer_goods SET consumerGoods = ?, consumer_description = ? WHERE LOWER(consumerId) = LOWER(?)")
			){
			
			pro.setString(1, cosumer);
			pro.setString(2, description);
			pro.setString(3, id);
			
			return pro.executeUpdate()>0;
		}
	}
	
	public String searchProduct(String productName, double unitPrice) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("SELECT productId FROM products WHERE LOWER(productName) = LOWER(?) and unitPrice = ?")
			){
			pro.setString(1, productName);
			pro.setDouble(2, unitPrice);
			ResultSet rs = pro.executeQuery();
			if(rs.next())
				return rs.getString(1);
			return null;
		}
	}
	
	public String searchIndustry(String industry) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("SELECT industryId FROM industrial_goods WHERE LOWER(industry) = LOWER(?)")
			){
			pro.setString(1, industry);
			ResultSet rs = pro.executeQuery();
			if(rs.next())
				return rs.getString(1);
			return null;
		}
	}

	public String searchConsumer(String consumer) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("SELECT consumerId FROM consumer_goods WHERE LOWER(consumerGoods) = LOWER(?)")
			){
			pro.setString(1, consumer);
			ResultSet rs = pro.executeQuery();
			if(rs.next())
				return rs.getString(1);
			return null;
		}
	}
	
	public ArrayList<String> viewProduct() throws ClassNotFoundException, SQLException{
		ArrayList<String> list = new ArrayList<String>();
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("SELECT * FROM products");
			){
			ResultSet rs = pro.executeQuery();
			while(rs.next()) {
				String product = "%-30s%-30s%-30s%-30.2f%"+":"+rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getString(4);
				list.add(product);
			}
		}
		return list;
	}
	
	public ArrayList<String> viewIndustry() throws ClassNotFoundException, SQLException{
		ArrayList<String> list = new ArrayList<String>();
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement in = con.prepareStatement("SELECT * FROM industrial_goods");
			){
			ResultSet rs = in.executeQuery();
			while(rs.next()) {
				String industry = "%-30s%-30s%-30s"+":"+rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3);
				list.add(industry);
			}
		}
		return list;
		
	}
	
	public ArrayList<String> viewConsumer() throws ClassNotFoundException, SQLException{
		ArrayList<String> list = new ArrayList<String>();
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement in = con.prepareStatement("SELECT * FROM consumer_goods");
			){
			ResultSet rs = in.executeQuery();
			while(rs.next()) {
				String industry = "%-30s%-30s%-30s"+":"+rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3);
				list.add(industry);
			}
		}
		return list;
		
	}
	
	public boolean addProductInfo(String productInfoId, String productId, String supplierId, String industryId, String consumerId) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement proInfo = con.prepareStatement("INSERT INTO products_information (productInfoId, productId, supplierId, industryId, consumerId) VALUES (?, ?, ?, ?, ?)");
			){
			proInfo.setString(1, productInfoId);
			proInfo.setString(2, productId);
			proInfo.setString(3, supplierId);
			proInfo.setString(4, industryId);
			proInfo.setString(5, consumerId);
			
			return proInfo.executeUpdate()>0;	
		}
		
	}
	
	public boolean deleteProductInfo(String productInfoId) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement proInfo = con.prepareStatement("DELETE FROM products_information WHERE LOWER(productInfoId) = LOWER(?)");
			){
			proInfo.setString(1, productInfoId);
			return proInfo.executeUpdate()>0;
		}
	}
	
	public boolean updateProductInfo(String productInfoId, String  productId, String supplierId, String industryId, String consumerId) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement proInfo = con.prepareStatement("UPDATE products_information SET productId = ?, supplierId = ?, industryId = ?, consumerId = ? WHERE LOWER(productInfoId) = LOWER(?)");
			){
			
			proInfo.setString(1, productId);
			proInfo.setString(2, supplierId);
			proInfo.setString(3, industryId);
			proInfo.setString(4, consumerId);
			proInfo.setString(5, productInfoId);
			return proInfo.executeUpdate()>0;
		}
		
	}
	
	public ArrayList<Product> viewProductInfo(String goodsType) throws ClassNotFoundException, SQLException, InvalidProduct{
		
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		if("All".equals(goodsType)) {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT productInfoId, productName, productDescription, unitPrice, supplierName, email, phone, industryId, industry, industry_description FROM products_information NATURAL JOIN products NATURAL JOIN supplier NATURAL JOIN industrial_goods");
				){
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+" | "+rs.getString("email")+" | "+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industryId")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
				
				
			}
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT productInfoId, productName, productDescription, unitPrice, supplierName, email, phone, consumerId, consumerGoods, consumer_description FROM products_information NATURAL JOIN products NATURAL JOIN supplier NATURAL JOIN consumer_goods");
				){
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+" | "+rs.getString("email")+" | "+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerId")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
			}
		}
		
		else if("IndustrialGoods".equals(goodsType)) {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT productInfoId, productName, productDescription, unitPrice, supplierName, email, phone, industryId, industry, industry_description FROM products_information NATURAL JOIN products NATURAL JOIN supplier NATURAL JOIN industrial_goods");

				){
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+" | "+rs.getString("email")+" | "+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industryId")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
				
				
			}
		}
		
		else if("ConsumerGoods".equals(goodsType)) {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT \r\n"
							+ "    productInfoId,\r\n"
							+ "    productName,\r\n"
							+ "    productDescription,\r\n"
							+ "    unitPrice,\r\n"
							+ "    supplierName,\r\n"
							+ "    email ,\r\n"
							+ "    phone,\r\n"
							+ "    consumerId,\r\n"
							+ "    consumerGoods,\r\n"
							+ "    consumer_description\r\n"
							+ "FROM \r\n"
							+ "    products_information\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    products\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    supplier\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    consumer_goods;\r\n"
							+ "");
				){
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+" | "+rs.getString("email")+" | "+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerId")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
			}
		}
		
		Collections.sort(list, Comparator.comparing(Product::getProductId));	
		return list;

	}
	
	public ArrayList<Product> searchProductInfoByProductInfoId(String productInfoId) throws ClassNotFoundException, SQLException{

		ArrayList<Product> list = new ArrayList<Product>();
		
		try(Connection con = DBConnection.getConnection();){
		
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    pi.productInfoId,\r\n"
					+ "    p.productName,\r\n"
					+ "    p.productDescription,\r\n"
					+ "    p.unitPrice,\r\n"
					+ "    s.supplierName,\r\n"
					+ "    s.email ,\r\n"
					+ "    s.phone,\r\n"
					+ "    industryId,\r\n"
					+ "    ig.industry,\r\n"
					+ "    ig.industry_description\r\n"
					+ "FROM \r\n"
					+ "    products_information pi\r\n"
					+ "JOIN \r\n"
					+ "    products p ON pi.productId = p.productId\r\n"
					+ "JOIN \r\n"
					+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
					+ "JOIN \r\n"
					+ "    industrial_goods ig ON pi.industryId = ig.industryId\r\n"
					+ "WHERE \r\n"
					+ "    pi.productInfoId = '?'\r\n"
					+ "");)
			{
				st.setString(1, productInfoId);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industryId")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    pi.productInfoId,\r\n"
					+ "    p.productName,\r\n"
					+ "    p.productDescription,\r\n"
					+ "    p.unitPrice,\r\n"
					+ "    s.supplierName,\r\n"
					+ "    s.email ,\r\n"
					+ "    s.phone,\r\n"
					+ "    cg.consumerId,\r\n"
					+ "    cg.consumerGoods,\r\n"
					+ "    cg.consumer_description\r\n"
					+ "FROM \r\n"
					+ "    products_information pi\r\n"
					+ "JOIN \r\n"
					+ "    products p ON pi.productId = p.productId\r\n"
					+ "JOIN \r\n"
					+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
					+ "JOIN \r\n"
					+ "    consumer_goods cg ON pi.consumerId = cg.consumerId\r\n"
					+ "WHERE \r\n"
					+ "    pi.productInfoId = '?'\r\n"
					+ "");)
			{
				st.setString(1, productInfoId);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerId")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
					}
				}
			}
		return list;
		}
			
	public ArrayList<Product> searchProductInfoByProductName(String productName) throws ClassNotFoundException, SQLException {

		ArrayList<Product> list = new ArrayList<Product>();
		
		try(Connection con = DBConnection.getConnection();){
		
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    pi.productInfoId,\r\n"
					+ "    p.productName,\r\n"
					+ "    p.productDescription,\r\n"
					+ "    p.unitPrice,\r\n"
					+ "    s.supplierName,\r\n"
					+ "    s.email,\r\n"
					+ "    s.phone,\r\n"
					+ "    industryId,\r\n"
					+ "    ig.industry,\r\n"
					+ "    ig.industry_description\r\n"
					+ "FROM \r\n"
					+ "    products_information pi\r\n"
					+ "JOIN \r\n"
					+ "    products p ON pi.productId = p.productId\r\n"
					+ "JOIN \r\n"
					+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
					+ "JOIN \r\n"
					+ "    industrial_goods ig ON pi.industryId = ig.industryId\r\n"
					+ "WHERE \r\n"
					+ "    p.productName = '?'\r\n"
					+ "");)
			{
				st.setString(1, productName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industryId")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    pi.productInfoId,\r\n"
					+ "    p.productName,\r\n"
					+ "    p.productDescription,\r\n"
					+ "    p.unitPrice,\r\n"
					+ "    s.supplierName,\r\n"
					+ "    s.email ,\r\n"
					+ "    s.phone,\r\n"
					+ "    cg.consumerId,\r\n"
					+ "    cg.consumerGoods,\r\n"
					+ "    cg.consumer_description\r\n"
					+ "FROM \r\n"
					+ "    products_information pi\r\n"
					+ "JOIN \r\n"
					+ "    products p ON pi.productId = p.productId\r\n"
					+ "JOIN \r\n"
					+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
					+ "JOIN \r\n"
					+ "    consumer_goods cg ON pi.consumerId = cg.consumerId\r\n"
					+ "WHERE \r\n"
					+ "   p.productName = '?'\r\n"
					+ "");)
			{
				st.setString(1, productName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerId")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
					}
				}
			}
		return list;
		}
		
	public ArrayList<Product> searchProductInfoBysupplierName(String supplierName) throws ClassNotFoundException, SQLException {

		ArrayList<Product> list = new ArrayList<Product>();
		
		try(Connection con = DBConnection.getConnection();){
		
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    pi.productInfoId,\r\n"
					+ "    p.productName,\r\n"
					+ "    p.productDescription,\r\n"
					+ "    p.unitPrice,\r\n"
					+ "    s.supplierName,\r\n"
					+ "    s.email,\r\n"
					+ "    s.phone,\r\n"
					+ "    industryId,\r\n"
					+ "    ig.industry,\r\n"
					+ "    ig.industry_description\r\n"
					+ "FROM \r\n"
					+ "    products_information pi\r\n"
					+ "JOIN \r\n"
					+ "    products p ON pi.productId = p.productId\r\n"
					+ "JOIN \r\n"
					+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
					+ "JOIN \r\n"
					+ "    industrial_goods ig ON pi.industryId = ig.industryId\r\n"
					+ "WHERE \r\n"
					+ "    s.supplierName = '?'\r\n"
					+ "");)
			{
				st.setString(1, supplierName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industryId")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    pi.productInfoId,\r\n"
					+ "    p.productName,\r\n"
					+ "    p.productDescription,\r\n"
					+ "    p.unitPrice,\r\n"
					+ "    s.supplierName,\r\n"
					+ "    s.email ,\r\n"
					+ "    s.phone,\r\n"
					+ "    cg.consumerId,\r\n"
					+ "    cg.consumerGoods,\r\n"
					+ "    cg.consumer_description\r\n"
					+ "FROM \r\n"
					+ "    products_information pi\r\n"
					+ "JOIN \r\n"
					+ "    products p ON pi.productId = p.productId\r\n"
					+ "JOIN \r\n"
					+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
					+ "JOIN \r\n"
					+ "    consumer_goods cg ON pi.consumerId = cg.consumerId\r\n"
					+ "WHERE \r\n"
					+ "    s.supplierName = '?'\r\n"
					+ "");)
			{
				st.setString(1, supplierName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerId")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
					}
				}
			}
		return list;
		}
		
	public ArrayList<Product> searchProductInfoByIndustry(String industry) throws ClassNotFoundException, SQLException {

		ArrayList<Product> list = new ArrayList<Product>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT \r\n"
						+ "    pi.productInfoId,\r\n"
						+ "    p.productName,\r\n"
						+ "    p.productDescription,\r\n"
						+ "    p.unitPrice,\r\n"
						+ "    s.supplierName,\r\n"
						+ "    s.email,\r\n"
						+ "    s.phone,\r\n"
						+ "    ig.industryId,\r\n"
						+ "    ig.industry,\r\n"
						+ "    ig.industry_description\r\n"
						+ "FROM \r\n"
						+ "    products_information pi\r\n"
						+ "JOIN \r\n"
						+ "    products p ON pi.productId = p.productId\r\n"
						+ "JOIN \r\n"
						+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
						+ "JOIN \r\n"
						+ "    industrial_goods ig ON pi.industryId = ig.industryId\r\n"
						+ "WHERE \r\n"
						+ "     ig.industry = '?'\r\n"
						+ "");
				){

				st.setString(1, industry);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industryId")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
				return list;
			}
		}	
	
	public ArrayList<Product> searchProductInfoByConsumer(String consumer) throws ClassNotFoundException, SQLException {

		ArrayList<Product> list = new ArrayList<Product>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT \r\n"
						+ "    pi.productInfoId,\r\n"
						+ "    p.productName,\r\n"
						+ "    p.productDescription,\r\n"
						+ "    p.unitPrice,\r\n"
						+ "    s.supplierName,\r\n"
						+ "    s.email ,\r\n"
						+ "    s.phone,\r\n"
						+ "    cg.consumerId,\r\n"
						+ "    cg.consumerGoods,\r\n"
						+ "    cg.consumer_description\r\n"
						+ "FROM \r\n"
						+ "    products_information pi\r\n"
						+ "JOIN \r\n"
						+ "    products p ON pi.productId = p.productId\r\n"
						+ "JOIN \r\n"
						+ "    supplier s ON pi.supplierId = s.supplierId\r\n"
						+ "JOIN \r\n"
						+ "    consumer_goods cg ON pi.consumerId = cg.consumerId\r\n"
						+ "WHERE \r\n"
						+ "     ig.industry = '?'\r\n"
						+ "");
				){

			st.setString(1, consumer);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String supplier = rs.getString("supplierName")+"|"+rs.getString("email")+"|"+rs.getString("phone");
				String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerId")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
				list.add(new ProductService().parseProductDetails(productInfo));
				}
				return list;
			}
		}	
	
	public boolean searchProductInfoByProductId(String productId) throws ClassNotFoundException, SQLException {

		try(Connection con = DBConnection.getConnection();){
		
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    *\r\n"
					+ "FROM \r\n"
					+ "    products_information \r\n"
					+ "WHERE \r\n"
					+ "    productId= '?'\r\n"
					+ "");)
			{
				st.setString(1, productId);
				ResultSet rs = st.executeQuery();
				return rs.next();
			}
		}
	}
	
	public boolean searchProductInfoByIndustryId(String industryId) throws ClassNotFoundException, SQLException {

		try(Connection con = DBConnection.getConnection();){
		
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    *\r\n"
					+ "FROM \r\n"
					+ "    products_information \r\n"
					+ "WHERE \r\n"
					+ "    industryId= '?'\r\n"
					+ "");)
			{
				st.setString(1, industryId);
				ResultSet rs = st.executeQuery();
				return rs.next();
			}
		}
	}
		
	public boolean searchProductInfoByConsumerId(String consumerId) throws ClassNotFoundException, SQLException {

		try(Connection con = DBConnection.getConnection();){
		
			try(PreparedStatement st = con.prepareStatement("SELECT \r\n"
					+ "    *\r\n"
					+ "FROM \r\n"
					+ "    products_information \r\n"
					+ "WHERE \r\n"
					+ "    consumerId= '?'\r\n"
					+ "");)
			{
				st.setString(1, consumerId);
				ResultSet rs = st.executeQuery();
				return rs.next();
			}
		}
	}
	
	public boolean checkProductInfoData(String productId, String supplierId, String industryId, String consumerId ) throws ClassNotFoundException, SQLException {
		try (Connection con = DBConnection.getConnection();) {
		if("NO".equals(consumerId)) {
			    try (PreparedStatement st = con.prepareStatement("SELECT * FROM products_information WHERE productId = ? AND supplierId = ? AND industryId = ?");) {
			        System.out.println("yes");
			        st.setString(1, productId);
			        st.setString(2, supplierId);
			        st.setString(3, industryId);
			        ResultSet rs = st.executeQuery();
			        boolean one = rs.next();
			        System.out.println(one);
			        return one;
			    }
			}
		else if("NO".equals(industryId)) {
				
				try(PreparedStatement st = con.prepareStatement("SELECT * FROM products_information WHERE productId = ? AND supplierId = ? AND consumerId = ?");)
				{
					st.setString(1, productId);
					st.setString(2, supplierId);
					st.setString(3, consumerId);
					ResultSet rs = st.executeQuery();
					return rs.next();
				}
			}
		return false;
		}
	}
	
 
}


/*

.....SUPPLIER IS EXISTS OR NOT CHECKING SQL PROCEDURE STORAGE.....
DELIMITER //

-- Drop the existing function if it exists
DROP FUNCTION IF EXISTS check_supplier_exists //

CREATE FUNCTION check_supplier_exists(supplierName VARCHAR(255)) RETURNS BOOLEAN
BEGIN
    DECLARE supplierCount INT;

    -- Check if the supplier with the given name exists (case-insensitive)
    SELECT COUNT(*) INTO supplierCount
    FROM suppliers
    WHERE LOWER(supplier_name) = LOWER(supplierName);

    -- Return true if the supplier exists, false otherwise
    RETURN supplierCount > 0;
END //

DELIMITER ;
 

*/