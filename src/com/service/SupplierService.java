package com.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidEmailId;
import com.exception.InvalidPhoneNumber;
import com.exception.InvalidProduct;
import com.exception.InvalidSupplierId;
import com.management.SupplierManagement;
import com.model.Supplier;
import com.util.ApplicationUtil;

public class SupplierService {
	
	ApplicationUtil util = new ApplicationUtil();	
	
	public boolean addSupplier(String productDetails) throws ClassNotFoundException, SQLException {
		try {
			Supplier supplier = parseSupplierDetails((generateUniqueId())+":"+productDetails);
			if( supplier == null) return false;
			return new SupplierManagement().addSupplier(supplier);			
		} catch(InvalidEmailId | InvalidPhoneNumber e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteSupplier(String id) throws ClassNotFoundException, SQLException {
		try {
			if(util.validateSupplierId(id)) {
				return new SupplierManagement().deleteSupplier(id);			
			}			
		} catch(InvalidSupplierId e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public boolean updateProduct(String supplierDetails) throws ClassNotFoundException, SQLException {
		try {
			if(util.validateSupplierId(supplierDetails)) return new SupplierManagement().updateSupplier(parseSupplierDetails(supplierDetails));
		} catch(InvalidEmailId | InvalidPhoneNumber | InvalidSupplierId e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public ArrayList<Supplier> viewProducts() throws ClassNotFoundException, SQLException, InvalidProduct{
		return new SupplierManagement().viewSuppliers();			
	}
	
	public ArrayList<Supplier> searchByProductId(String supplierId) throws ClassNotFoundException, SQLException, InvalidProduct{
		return new SupplierManagement().searchSupplierById(supplierId);
	}
	
	public ArrayList<Supplier> searchByProductName(String supplierName) throws ClassNotFoundException, SQLException, InvalidProduct{
		return new SupplierManagement().searchSupplierByName(supplierName);
	}
	
	
	
	public Supplier parseSupplierDetails(String supplierDetails) throws ClassNotFoundException, SQLException, InvalidEmailId, InvalidPhoneNumber {
		
		String[] supplier = supplierDetails.split(":");
		if(supplier.length == 6) {
			if(util.isValidPhoneNumber(supplier[4]) && util.isValidEmail(supplier[3])) {
				return new Supplier(supplier[0], supplier[1], supplier[2], supplier[3], Long.valueOf(supplier[4]));
			}					
		}
		return null;
	}

	public String generateUniqueId() {
	       return "SUPP"+generateSCMId();
	    }
	private String generateSCMId() {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
     String timestamp = dateFormat.format(new Date());
	    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
	    return timestamp + String.format("%03d", randomSuffix);
	    }

}
