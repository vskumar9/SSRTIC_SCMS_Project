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
	
	public String searchProduct(String productName) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("SELECT productId FROM products WHERE LOWER(productName) = LOWER(?)")
			){
			pro.setString(1, productName);
			ResultSet rs = pro.executeQuery();
			if(rs.next())
				return rs.getString(1);
			return null;
		}
	}
	
	public String searchIndustry(String industry) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pro = con.prepareStatement("SELECT industryId FROM products WHERE LOWER(industry) = LOWER(?)")
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
				PreparedStatement pro = con.prepareStatement("SELECT consumerId FROM products WHERE LOWER(consumerGoods) = LOWER(?)")
			){
			pro.setString(1, consumer);
			ResultSet rs = pro.executeQuery();
			if(rs.next())
				return rs.getString(1);
			return null;
		}
	}
	
	
	
	
	public boolean addProductInfo(String productInfoId, String productId, String supplierId, String industryId, String consumerId) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement proInfo = con.prepareStatement("INSTER INTO products_information VALUES(?, ?, ?, ?, ?)");
			){
			
			proInfo.setString(5, productInfoId);
			proInfo.setString(1, productId);
			proInfo.setString(2, supplierId);
			proInfo.setString(3, industryId);
			proInfo.setString(4, consumerId);
			
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
					PreparedStatement st = con.prepareStatement("SELECT \r\n"
							+ "    productInfoId,\r\n"
							+ "    productName,\r\n"
							+ "    productDescription,\r\n"
							+ "    unitPrice,\r\n"
							+ "    supplierName,\r\n"
							+ "    industry,\r\n"
							+ "    industry_description\r\n"
							+ "FROM \r\n"
							+ "    products_information\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    products\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    supplier\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    industrial_goods\r\n"
							+ "");
				){
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+rs.getString("supplierName")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
				
				
			}
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT \r\n"
							+ "    productInfoId,\r\n"
							+ "    productName,\r\n"
							+ "    productDescription,\r\n"
							+ "    unitPrice,\r\n"
							+ "    supplierName,\r\n"
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
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+rs.getString("supplierName")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
			}
		}
		
		else if("industrial".equals(goodsType)) {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT \r\n"
							+ "    productInfoId,\r\n"
							+ "    productName,\r\n"
							+ "    productDescription,\r\n"
							+ "    unitPrice,\r\n"
							+ "    supplierName,\r\n"
							+ "    industry,\r\n"
							+ "    industry_description\r\n"
							+ "FROM \r\n"
							+ "    products_information\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    products\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    supplier\r\n"
							+ "NATURAL JOIN \r\n"
							+ "    industrial_goods\r\n"
							+ "");
				){
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+rs.getString("supplierName")+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
				
				
			}
		}
		
		else if("consumer".equals(goodsType)) {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT \r\n"
							+ "    productInfoId,\r\n"
							+ "    productName,\r\n"
							+ "    productDescription,\r\n"
							+ "    unitPrice,\r\n"
							+ "    supplierName,\r\n"
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
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+rs.getString("supplierName")+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
			}
		}
		
		Collections.sort(list, Comparator.comparing(Product::getProductId));	
		return list;

	}
	
	
	public ArrayList<Product> searchByProductIdInfo(String productInfoId) throws ClassNotFoundException, SQLException, InvalidProduct{

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
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
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
					String productDetails = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
					}
				}
			}
		return list;
		}
		
		
	public ArrayList<Product> searchProductInfoByProductName(String productName) throws ClassNotFoundException, SQLException, InvalidProduct{

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
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
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
					String productDetails = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
					}
				}
			}
		return list;
		}
		
	public ArrayList<Product> searchProductInfoBysupplierName(String supplierName) throws ClassNotFoundException, SQLException, InvalidProduct{

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
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
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
					String productDetails = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
					}
				}
			}
		return list;
		}
		
	public ArrayList<Product> searchProductInfoByIndustry(String industry) throws ClassNotFoundException, SQLException, InvalidProduct{

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
					String productInfo = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("industry")+":"+rs.getString("industry_description")+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productInfo));
				}
				return list;
			}
		}	

	
	public ArrayList<Product> searchProductInfoByConsumer(String consumer) throws ClassNotFoundException, SQLException, InvalidProduct{

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
				String productDetails = rs.getString("productInfoId")+":"+rs.getString("productName")+":"+rs.getString("productDescription")+":"+rs.getDouble("unitPrice")+":"+supplier+":"+rs.getString("consumerGoods")+":"+rs.getString("consumer_description")+":"+"ConsumerGoods";
				list.add(new ProductService().parseProductDetails(productDetails));
				}
				return list;
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