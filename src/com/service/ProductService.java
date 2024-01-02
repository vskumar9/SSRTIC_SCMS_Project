package com.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidProduct;
import com.exception.InvaliedSupplierName;
import com.management.ProductManagement;
import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;
import com.util.ApplicationUtil;

public class ProductService {
	
	ApplicationUtil util = new ApplicationUtil();
	
	public boolean addProduct(String productDetails) throws ClassNotFoundException, SQLException {
		try {
			Product product = parseProductDetails((generateUniqueId())+":"+productDetails);
			if( product == null) return false;
			return new ProductManagement().addProduct(product);			
		} catch(InvalidProduct e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteProduct(String id, String goodsType) throws ClassNotFoundException, SQLException {
		try {
			if(util.validateProductId(id)) {
				return new ProductManagement().deleteProduct(id, goodsType);			
			}			
		} catch(InvalidProduct e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public boolean updateProduct(String productDetails) throws ClassNotFoundException, SQLException {
		try {
			if(util.validateProductId(productDetails)) return new ProductManagement().updateProduct(parseProductDetails(productDetails));
		} catch(InvalidProduct e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public ArrayList<Product> viewProducts(String goodsType) throws ClassNotFoundException, SQLException, InvalidProduct{
		return new ProductManagement().viewProducts(goodsType);			
	}
	
	public ArrayList<Product> searchByProductId(String prouctId) throws ClassNotFoundException, SQLException, InvalidProduct{
		return new ProductManagement().serachByProductId(prouctId);
	}
	
	public ArrayList<Product> searchByProductName(String prouctName) throws ClassNotFoundException, SQLException, InvalidProduct{
		return new ProductManagement().serachByProductName(prouctName);
	}
	
	public ArrayList<Product> searchBySupplierName(String supplierName) throws ClassNotFoundException, SQLException, InvalidProduct{
		return new ProductManagement().serachBySupplierName(supplierName);
	}
	
	public Product parseProductDetails(String productDetails) throws ClassNotFoundException, SQLException, InvalidProduct {
		
		String[] productArray = productDetails.split(":");
		try {
			if(productArray.length == 8) {
				if(util.supplierValidateName(productArray[4])) {
					if("IndustrialGoods".equals(productArray[7])) {
						IndustrialGoods product = new IndustrialGoods(productArray[0], productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6]);
						return product;
					} 
					else if("ConsumerGoods".equals(productArray[7])) {
						ConsumerGoods product = new ConsumerGoods(productArray[0] , productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6]);
						return product;
						}
					}
				}
			throw new InvalidProduct("Invaid Product is: "+productDetails);
			} catch(InvaliedSupplierName e) {
				System.out.println(e.getMessage());					
			}
		return null;
	}
	
	public String generateUniqueId() {
	       return "PROD"+generateSCMId();
	    }
	private String generateSCMId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
        String timestamp = dateFormat.format(new Date());
	    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
	    return timestamp + String.format("%03d", randomSuffix);
	    }



}
