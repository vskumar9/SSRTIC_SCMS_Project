package com.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.Product;

public class ApplicationUtil {


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
