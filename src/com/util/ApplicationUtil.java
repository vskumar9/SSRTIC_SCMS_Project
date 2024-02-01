package com.util;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public boolean validateShipmentId(String orderId) throws InvalidException {
		if(orderId.matches("^SHIP\\d{13,15}")) return true;
		throw new InvalidException("Order Id: "+orderId+" is invalid");
	}
	
	public boolean validateCarrierId(String orderId) throws InvalidException {
		if(orderId.matches("^CARR\\d{13,15}")) return true;
		throw new InvalidException("Order Id: "+orderId+" is invalid");
	}
	
	public boolean isValidCardNumber(String cardNumber) throws InvalidException {
        // Use a regex pattern for common credit card number formats
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                       "(?<mastercard>5[1-5][0-9]{14})|" +
                       "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
                       "(?<amex>3[47][0-9]{13})|" +
                       "(?<dinersclub>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
                       "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNumber);

        if(matcher.matches()) return true;
        throw new InvalidException("Invalid card number.");
    }
	
	public boolean isExpired(String expirationDate) {
        // Validate the format of the expiration date (MM/YY)
        if (!expirationDate.matches("\\d{2}/\\d{2}")) {
            return true; // Invalid format
        }

        // Parse the expiration date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        try {
            Date expDate = dateFormat.parse(expirationDate);

            // Check if the card has already expired
            Calendar now = Calendar.getInstance();
            Calendar expCalendar = Calendar.getInstance();
            expCalendar.setTime(expDate);

            return now.after(expCalendar);
        } catch (ParseException e) {
            return true; // Invalid date
        }
    }
	
	public boolean isValidCVV(String cvv) {
        // Validate the CVV format (3 or 4 digits)
        return cvv.matches("\\d{3,4}");
    }
	
	public boolean isValidUPI(String upiId) throws InvalidException {
        // UPI ID pattern (username@upi)
        String upiPattern = "^[a-zA-Z0-9_.+-]+@\\w+$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(upiPattern);

        // Match the input UPI ID with the pattern
        Matcher matcher = pattern.matcher(upiId);

        // Return true if the UPI ID matches the pattern, otherwise false
        if(matcher.matches()) return true;
        throw new InvalidException("Invalid UPI");
    }


}
