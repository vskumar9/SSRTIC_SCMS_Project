package com.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidException;
import com.exception.InvalidProduct;
import com.exception.InvalidSupplierId;
import com.management.ProductManagement;
import com.management.SupplierManagement;
import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;
import com.util.ApplicationUtil;

public class ProductService {
	
	ApplicationUtil util = new ApplicationUtil();
	ProductManagement pm = new ProductManagement();
	SupplierManagement sm = new SupplierManagement();
	
	
	public String addProductInfo(String productDetails) {
		try {
			String[] productInfo = productDetails.split(":");
			if(productInfo.length == 6) {
				String productId = pm.searchProduct(productInfo[0], Double.valueOf(productInfo[2]));
				if(productId.isEmpty()) {
					productId = "PROD"+generateUniqueId();
					pm.addProduct(productId, productInfo[0], productInfo[1], Double.valueOf(productInfo[2]));
				}
				if(util.validateSupplierId(productInfo[3]) && sm.searchSupplierByName(productInfo[3]).isEmpty()) {
					return "Supplier id: "+productInfo[3]+" not exists.";
				}
				String productInfoId = "PROI"+generateUniqueId();
				if("IndustrialGoods".equals(productInfo[5]))
					if(pm.addProductInfo(productInfoId, productId, productInfo[3], productInfo[4], null))
						return "The Product Id: "+productId+", Industry id:"+productInfo[4]+" and product inforamtion id(product with supplier and goods): "+productInfoId;				
						
				else if("ConsumerGoods".equals(productInfo[5]))
					if(pm.addProductInfo(productInfoId, productId, productInfo[3], null, productInfo[4]))
						return "The Product Id: "+productId+", Consumer id:"+productInfo[4]+" and product inforamtion id(product with supplier and goods): "+productInfoId;				
				return null;
			}
			else {
				System.out.println("Invalid data "+productInfo);
				return null;
			}
			
		} catch(InvalidSupplierId e) {
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String deleteProductInfo(String productInfoId) {
		try {
			if(pm.deleteProductInfo(productInfoId) && util.validProductInfoId(productInfoId))
				return productInfoId+" is successfully deleted.";			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean updateProductInfo(String productInfoDetails) {
		try {
			String[] productInfo = productInfoDetails.split(":");
			if(productInfo.length == 7) {
				String productId = pm.searchProduct(productInfo[0], Double.valueOf(productInfo[2]));
				if(productId.isEmpty()) {
					productId = "PROD"+generateUniqueId();
					pm.addProduct(productId, productInfo[0], productInfo[1], Double.valueOf(productInfo[2]));
				}
				if(util.validProductInfoId(productInfo[0]) && sm.searchSupplierByName(productInfo[4]).isEmpty()) {
					System.out.println("Supplier id: "+productInfo[4]+" not exists.");
					return false;
				}
				if("IndustrialGoods".equals(productInfo[6]))
					if(pm.addProductInfo(productInfo[0], productId, productInfo[4], productInfo[5], null)) {
						System.out.println("The Product Id: "+productId+", Industry id:"+productInfo[4]+" and product inforamtion id(product with supplier and goods): "+productInfo[0]);
						return true;
					}
				else if("ConsumerGoods".equals(productInfo[6]))
					if(pm.addProductInfo(productInfo[0], productId, productInfo[4], null, productInfo[5])) {
						System.out.println("The Product Id: "+productId+", Consumer id:"+productInfo[4]+" and product inforamtion id(product with supplier and goods): "+productInfo[0]);
						return true;
					}
				return false;
			}
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<Product> viewProductInfo(String goodsType) throws ClassNotFoundException, SQLException, InvalidProduct{
		return pm.viewProductInfo(goodsType);
	}
	
	public ArrayList<Product> searchProductInfoByProductInfoId(String productInfoId) throws ClassNotFoundException, SQLException{
		try {
			return pm.searchProductInfoByProductInfoId(productInfoId);			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Product> searchProductInfoByProductName(String productName) {
		try {
			return pm.searchProductInfoByProductName(productName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Product> searchProductInfoBysupplierName(String supplierName){
		try {
			return pm.searchProductInfoBysupplierName(supplierName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Product> searchProductInfoByIndustry(String industry){
		try {
			return pm.searchProductInfoByIndustry(industry);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Product> searchProductInfoByConsumer(String consumer){
		try {
			return pm.searchProductInfoByConsumer(consumer);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	
	
	
 	public Product parseProductDetails(String productDetails) throws ClassNotFoundException, SQLException {
		
		String[] productArray = productDetails.split(":");
			if(productArray.length == 9) {
					if("IndustrialGoods".equals(productArray[8])) {
						
						IndustrialGoods product = new IndustrialGoods(productArray[0], productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6], productArray[6]);
						return product;
					} 
					else if("ConsumerGoods".equals(productArray[8])) {
						ConsumerGoods product = new ConsumerGoods(productArray[0] , productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6], productArray[6]);
						return product;
						}
					}
			return null;
	}
	
	public String addIndustry(String industry, String desc) {
		try {
			String industryId = "INST"+generateUniqueId();
			if(new ProductManagement().addIndustry(industryId, industry, desc)) {
				return industryId;
			}			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String addConsumer(String consumer, String desc) {
		try {
			String consumerId = "INST"+generateUniqueId();
			if(new ProductManagement().addConsumer(consumerId, consumer, desc)) {
				return consumerId;
			}			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	public String checkingIndustry(String industry) throws ClassNotFoundException, SQLException {
		return new ProductManagement().searchIndustry(industry);
	}
	
	public String checkingConsumer(String consumer) throws ClassNotFoundException, SQLException {
		return new ProductManagement().searchConsumer(consumer);
	}
	
	
	public String generateUniqueId() {
	       return generateSCMId();
	    }
	private String generateSCMId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
        String timestamp = dateFormat.format(new Date());
	    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
	    return timestamp + String.format("%03d", randomSuffix);
	    }
	
	
	


}
