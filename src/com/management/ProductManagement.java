package com.management;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.exception.InvalidProduct;
import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;
import com.service.ProductService;

public class ProductManagement {
	
	public boolean addProduct(Product productObj) throws ClassNotFoundException, SQLException {
		
		try(Connection con = DBConnection.getConnection();){
			if(productObj instanceof IndustrialGoods) {
				try(
						PreparedStatement pro = con.prepareStatement("INSERT INTO products(productId, productName, productDescription, unitPrice, supplierId, industryId) VALUES ( ?, ?, ?, ?, ?, ?)");
					){
					
					IndustrialGoods ig = (IndustrialGoods)productObj;
					
					pro.setString(1, ig.getProductId());
					pro.setString(2, ig.getProductName());
					pro.setString(3, ig.getDescription());
					pro.setDouble(4, ig.getUnitPrice());
					pro.setString(5, ig.getSupplierInfo());
					pro.setString(6, ig.getIndustryId());
					
					return pro.executeUpdate() > 0;
					
				}
				
			}
			else if(productObj instanceof ConsumerGoods) {
				try(
						PreparedStatement pro = con.prepareStatement("INSERT INTO products(productId, productName, productDescription, unitPrice, supplierId, consumerId) VALUES ( ?, ?, ?, ?, ?, ?)");
					){
					
					ConsumerGoods cg = (ConsumerGoods)productObj;
					
					pro.setString(1, cg.getProductId());
					pro.setString(2, cg.getProductName());
					pro.setString(3, cg.getDescription());
					pro.setDouble(4, cg.getUnitPrice());
					pro.setString(5, cg.getSupplierInfo());
					pro.setString(6, cg.getConsumerId());
					
					return pro.executeUpdate() > 0;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean deleteProduct(String id, String goodsType) throws ClassNotFoundException, SQLException {
		
		try(Connection con = DBConnection.getConnection();){
			if("IndustrialGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("DELETE FROM products WHERE LOWER(productId) = LOWER(?)"))
				{
					st.setString(1, id);
					return st.executeUpdate() > 0;
				}
				
				
			} else if("ConsumerGoods".equalsIgnoreCase(goodsType)) {
				
				try(PreparedStatement st = con.prepareStatement("DELETE FROM products WHERE LOWER(productId) = LOWER(?)"))
				{
					st.setString(1, id);
					return st.executeUpdate() > 0;
				}
				
			}
			
		}
				
		return false;
	}
	
	public boolean updateProduct(Product productObj) throws ClassNotFoundException, SQLException {
		
		try(Connection con = DBConnection.getConnection();){
			if(productObj instanceof IndustrialGoods) {
				
				try(PreparedStatement pro = con.prepareStatement("UPDATE products SET productName = ?, description = ?, unitPrice = ?, supplierId = ?, industryId = ? WHERE LOWER(productId) = LOWER(?)"))
				{
					IndustrialGoods ig = (IndustrialGoods) productObj;
					
					pro.setString(1, ig.getProductName());
					pro.setString(2, ig.getDescription());
					pro.setDouble(3, ig.getUnitPrice());
					pro.setString(4, ig.getSupplierInfo());
					pro.setString(5, ig.getIndustryId());
					pro.setString(6, ig.getProductId());
					
					return pro.executeUpdate() > 0;
				}
				
				
			} else if(productObj instanceof ConsumerGoods) {
				
				try(PreparedStatement pro = con.prepareStatement("UPDATE products SET productName = ?, description = ?, unitPrice = ?, supplierId = ?, consumerId = ? WHERE LOWER(productId) = LOWER(?)"))
				{
					ConsumerGoods cg = (ConsumerGoods) productObj;
					
					pro.setString(1, cg.getProductName());
					pro.setString(2, cg.getDescription());
					pro.setDouble(3, cg.getUnitPrice());
					pro.setString(4, cg.getSupplierInfo());
					pro.setString(5, cg.getConsumerId());
					pro.setString(6, cg.getProductId());
					return pro.executeUpdate() > 0;
				}
				
			}
			
		}
		
		return false;
	}
		
	public ArrayList<Product> viewProducts(String goodsType) throws ClassNotFoundException, SQLException, InvalidProduct{
		
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
	
	public ArrayList<Product> serachByProductId(String prouctId) throws ClassNotFoundException, SQLException, InvalidProduct{

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
	
	public ArrayList<Product> serachByProductName(String prouctName) throws ClassNotFoundException, SQLException, InvalidProduct{

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
	
	public ArrayList<Product> serachBySupplierName(String supplierName) throws ClassNotFoundException, SQLException, InvalidProduct{

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