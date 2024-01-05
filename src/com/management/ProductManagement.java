package com.management;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.exception.InvalidProduct;
import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;
import com.model.Supplier;
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
	
	
	public ArrayList<String> viewProductInfo() throws ClassNotFoundException, SQLException{
		
		ArrayList<String> list = new ArrayList<String>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM products_information");
			){
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String productInfo = rs.getString(1)+":"+rs.getString(1)+":"+rs.getString(1)+":"+rs.getString(1)+":"+rs.getString(1);
				list.add(productInfo);
			}
			return list;
		}

	}
	
	
	
	
	public ArrayList<Product> viewProducts_info(String goodsType) throws ClassNotFoundException, SQLException, InvalidProduct{
		
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
	
	public ArrayList<Product> serachByProductId_info(String prouctId) throws ClassNotFoundException, SQLException, InvalidProduct{

		ArrayList<Product> list = new ArrayList<Product>();

		
		
		try(Connection con = DBConnection.getConnection();){
			
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods WHERE LOWER(productId) = LOWER(?)"))
			{
				st.setString(1, prouctId);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods WHERE LOWER(productId) = LOWER(?)"))
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
	
	public ArrayList<Product> serachByProductName_info(String prouctName) throws ClassNotFoundException, SQLException, InvalidProduct{

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
	
	public ArrayList<Product> serachBySupplierName_info(String supplierName) throws ClassNotFoundException, SQLException, InvalidProduct{

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
	
	public boolean searchSupplierByNameInProductAdd(String supplierName) throws SQLException, ClassNotFoundException{
		
		try(
				Connection con = DBConnection.getConnection();
				CallableStatement callableStatement = con.prepareCall("{ ? = call check_supplier_exists(?) }");			
			){
			
			callableStatement.registerOutParameter(1, Types.BOOLEAN);
            callableStatement.setString(2, supplierName);
            callableStatement.execute();

            return callableStatement.getBoolean(1);	
				
		}
	}
	
	public ArrayList<Product> serachByIndustryOrCategory(String industryOrCategory) throws ClassNotFoundException, SQLException, InvalidProduct{

		ArrayList<Product> list = new ArrayList<Product>();

		
		
		try(Connection con = DBConnection.getConnection();){
			
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM industrial_goods WHERE LOWER(productId) = LOWER(?)"))
			{
				st.setString(1, industryOrCategory);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"IndustrialGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
			try(PreparedStatement st = con.prepareStatement("SELECT * FROM consumer_goods WHERE LOWER(productId) = LOWER(?)"))
			{
				st.setString(1, industryOrCategory);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String productDetails = rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getDouble(4)+":"+rs.getString(5)+":"+rs.getString(6)+":"+rs.getString(7)+":"+"ConsumerGoods";
					list.add(new ProductService().parseProductDetails(productDetails));
				}
			}
		}
		
		return list;
		
	}
	
//	public String checkingIndustry(String industry) {
//		try(Connection con = DBConnection.getConnection();)
//	}
	
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