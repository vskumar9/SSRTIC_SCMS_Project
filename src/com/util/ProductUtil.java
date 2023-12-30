package com.util;

import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;

public class ProductUtil {
	
	public Product parseProductDetails(String productDetails) {
		
		String[] productArray = productDetails.split(":");
		if("IndustrialGoods".equals(productArray[0])) {
			IndustrialGoods product = new IndustrialGoods("PROD"+new ApplicationUtil().generateUniqueId(), productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6]);
			return product;
		} else if("ConsumerGoods".equals(productArray[0])) {
			ConsumerGoods product = new ConsumerGoods("PROD"+new ApplicationUtil().generateUniqueId(), productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6]);
			return product;
		}
		
		return null;
	}

}
