package com.util;
import java.sql.SQLException;

import com.exception.InvalidException;
import com.management.SupplierManagement;

public class ApplicationUtil {
	
	public boolean isValidEmail(String email) throws InvalidException {
		if(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) return true;
		throw new InvalidException("Invalid Mail ID: "+email);
		
	}
	
	public boolean isValidPhoneNumber(String phoneNumber) throws InvalidException {
		if(phoneNumber.matches("[6-9]{1}[0-9]{9}")) return true;
		throw new InvalidException("Invalid Phone number: "+phoneNumber);
	}
	
	public boolean validProductInfoId(String productInfoId) throws InvalidException {
		if(productInfoId.matches("^PROI\\d{13,15}$")) return true;
		throw new InvalidException("Product Inforamtion Id: "+productInfoId+" is invalid.");
	}
	
	public boolean validIndustryId(String industryId) throws InvalidException {
		if(industryId.matches("^INST\\d{13,15}")) return true;
		throw new InvalidException("Industry Id: "+industryId+" is invalid.");
	}
	
	public boolean validConsumerId(String consumerId) throws InvalidException {
		if(consumerId.matches("^CONS\\d{13,15}")) return true;
		throw new InvalidException("Consumer Id: "+consumerId+" is invalid.");
	}
	
//	public boolean supplierValidateName(String supplierName) throws InvaliedSupplierName, ClassNotFoundException, SQLException {
//		
//		if(new ProductManagement().searchSupplierByNameInProductAdd(supplierName)) {return true;}
//		throw new InvaliedSupplierName(supplierName+"Supplier is not exists");
//	}
	
	public boolean emailValidate(String email) throws ClassNotFoundException, SQLException, InvalidException {
		if(new SupplierManagement().searchEmail(email))return true;
		throw new InvalidException("Already exists email id: "+email);
	}
	
	public boolean phoneNumberValidate(long phone) throws ClassNotFoundException, SQLException, InvalidException {
		if(new SupplierManagement().searchPhoneNumber(phone)) return true;
		throw new InvalidException("Already exists phone number: "+phone);
	}
		
	public boolean validateProductId(String productDetails) throws InvalidException {
		String[] product = productDetails.split(":");
		if(product[0].matches("^PROD\\d{13,15}$")) {return true;};
		throw new InvalidException("Product id: "+product[0]+" is not exists");
	}
	
	public boolean validateSupplierId(String supplierDetails) throws InvalidException {
		String[] supplier = supplierDetails.split(":");
		if(supplier[0].matches("^SUPP\\d{13,15}$")) {return true;};
		throw new InvalidException("Supplier id: "+supplier[0]+" is not exists");
	}
	
	public boolean validateProductinfoId(String productDetails) throws InvalidException {
		String[] product = productDetails.split(":");
		if(product[0].matches("^PROI\\d{13,15}$")) {return true;};
		throw new InvalidException("Product id: "+product[0]+" is not exists");
	}
	
	public boolean validateInventoryId(String inventoryId) throws InvalidException {
		if(inventoryId.matches("^INVT\\d{13,15}$")) return true;
		throw new InvalidException("Inventory id: "+inventoryId+" is not exists");
	}
	
	public boolean validateWarehouseId(String warehouseId) throws InvalidException {
		if(warehouseId.matches("^WRHS\\d{13,15}$")) return true;
		throw new InvalidException("Warehouse Id: "+warehouseId+" is not exists");
	}
	
	public boolean validateCustomerId(String customerId) throws InvalidException {
		if(customerId.matches("^CUMR\\d{13,15}$")) return true;
		throw new InvalidException("Customer Id: "+customerId+" is invalid");
	}
	
	public boolean validateOrderId(String orderId) throws InvalidException {
		if(orderId.matches("^ORDR\\d{13,15}")) return true;
		throw new InvalidException("Order Id: "+orderId+" is invalid");
	}
	


}
