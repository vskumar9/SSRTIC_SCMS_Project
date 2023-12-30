package com.service;

import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;

public class ProductService {
	
	public Product parseProductDetails(String productDetails) {
		
		String[] productArray = productDetails.split(":");
		if("IndustrialGoods".equals(productArray[7])) {
			IndustrialGoods product = new IndustrialGoods(productArray[0], productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6]);
			return product;
		} else if("ConsumerGoods".equals(productArray[7])) {
			ConsumerGoods product = new ConsumerGoods(productArray[0] , productArray[1], productArray[2], Double.parseDouble(productArray[3]), productArray[4],productArray[5], productArray[6]);
			return product;
		}
		
		return null;
	}
	
	
	



}
