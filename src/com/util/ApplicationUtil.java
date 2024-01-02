package com.util;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.exception.InvalidEmailId;
import com.exception.InvalidPhoneNumber;
import com.exception.InvalidProduct;
import com.exception.InvalidSupplierId;
import com.exception.InvaliedSupplierName;
import com.management.ProductManagement;

public class ApplicationUtil {
	
	public boolean isValidEmail(String email) throws InvalidEmailId {
		if(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) return true;
		throw new InvalidEmailId("Invalid Mail ID: "+email);
		
	}
	
	public boolean isValidPhoneNumber(String phoneNumber) throws InvalidPhoneNumber {
		if(phoneNumber.matches("[6-9]{1}[0-9]{9}")) return true;
		throw new InvalidPhoneNumber("Invalid Phone number: "+phoneNumber);
	}
	
	
	public boolean supplierValidateName(String supplierName) throws InvaliedSupplierName, ClassNotFoundException, SQLException {
		
		if(new ProductManagement().searchSupplierByNameInProductAdd(supplierName)) {return true;}
		throw new InvaliedSupplierName(supplierName+"Supplier is not exits");
	}
		
	public boolean validateProductId(String productDetails) throws ClassNotFoundException, SQLException, InvalidProduct {
		String[] product = productDetails.split(":");
		if(product[0].matches("^PROD\\d{15}$")) {return true;};
		throw new InvalidProduct("Product id: "+product[0]+" is not exits");
	}
	
	public boolean validateSupplierId(String supplierDetails) throws ClassNotFoundException, SQLException, InvalidSupplierId {
		String[] supplier = supplierDetails.split(":");
		if(supplier[0].matches("^SUPP\\d{15}$")) {return true;};
		throw new InvalidSupplierId("Supplier id: "+supplier[0]+" is not exits");
	}
	


}
