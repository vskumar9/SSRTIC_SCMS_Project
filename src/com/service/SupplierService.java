package com.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidEmailId;
import com.exception.InvalidPhoneNumber;
import com.exception.InvalidSupplierId;
import com.management.SupplierManagement;
import com.model.Supplier;
import com.util.ApplicationUtil;

public class SupplierService {
	
	// Create ApplicationUtil class. it's help's to using ApplicationUtil class methods
	ApplicationUtil util = new ApplicationUtil();	
	
	// Helper method to add new supplier details
	public String addSupplier(String supplierDetails) {
		try {
			
			Supplier supplier = parseSupplierDetails(generateUniqueId()+":"+supplierDetails, "ADD");
			if( supplier == null) return null;
			new SupplierManagement().addSupplier(supplier);
			return supplier.getSupplierName()+" Id is: "+supplier.getSupplierId();			
		} catch(InvalidEmailId | InvalidPhoneNumber e) {
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to delete exists supplier details
	public boolean deleteSupplier(String id) {
		try {
			if(util.validateSupplierId(id)) {
				return new SupplierManagement().deleteSupplier(id);			
			}			
		} catch(InvalidSupplierId e) {
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	// Helper method to already exists update supplier details
	public boolean updateSupplier(String supplierDetails) {
		try {
			if(util.validateSupplierId(supplierDetails)) return new SupplierManagement().updateSupplier(parseSupplierDetails(supplierDetails, "UPDATE"));
		} catch(InvalidEmailId | InvalidPhoneNumber | InvalidSupplierId e) {
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	// Helper method to retrieve all supplier details
	public ArrayList<Supplier> viewSuppliers() {
		try {
			return new SupplierManagement().viewSuppliers();						
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to retrieve the supplier details specific supplier id  
	public ArrayList<Supplier> searchBySupplierId(String supplierId) {
		try{
			if(util.validateSupplierId(supplierId))return new SupplierManagement().searchSupplierById(supplierId);
		} catch(InvalidSupplierId e) {
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to retrieve the supplier details specific supplier name 
	public ArrayList<Supplier> searchBySupplierName(String supplierName){
		try{
			return new SupplierManagement().searchSupplierByName(supplierName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to create supplier object
	public Supplier parseSupplierDetails(String supplierDetails, String method) throws ClassNotFoundException, SQLException, InvalidEmailId, InvalidPhoneNumber {
		
		String[] supplier = supplierDetails.split(":");
		if(supplier.length == 5) {
			if(util.isValidPhoneNumber(supplier[4]) && util.isValidEmail(supplier[3])) {
				if("ADD".equals(method)) {
					if(util.emailValidate(supplier[3]) && util.phoneNumberValidate(Long.valueOf(supplier[4]))) {
						return new Supplier(supplier[0], supplier[1], supplier[2], supplier[3], Long.valueOf(supplier[4]));
					}									
				}
				else if("UPDATE".equals(method)) {
					return new Supplier(supplier[0], supplier[1], supplier[2], supplier[3], Long.valueOf(supplier[4]));					
				}
			}
		}
		return null;
	}

	// Helper method to generate supplier unique id
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
