package com.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.exception.InvalidProduct;
import com.service.ProductService;
import com.util.ApplicationUtil;

public class ProductUserInterface {
	
	Scanner sc = new Scanner(System.in);
	ProductService service = new ProductService();
	ApplicationUtil util = new ApplicationUtil();
	
	public void productSection() {
		int productSectionChoice;
		do {
			System.out.print("--------------PRODUCT SECTION--------------\nA. Product Information \nB. Product \nC. Industry \nD. Consumer\nE. <- Go to Main Section");
			System.out.print("\nEnter your option: ");
			productSectionChoice = sc.next().charAt(0);
			
			switch(productSectionChoice) {
			case 'A':
			case 'a':
				productInfo();
				break;
			case 'B':
			case 'b':
				product();
				break;
			case 'C':
			case 'c':
				industry();
				break;
			case 'D':
			case 'd':
				consumer();
				break;
			case 'E':
			case 'e':
				System.out.println("-----Complete Product Section-----");
				return;
			default:
				System.out.println("Your choice is wrong. ");
				
			}
			
		}while(productSectionChoice != 'E' || productSectionChoice != 'e');
		
	}
	
	
	
	private void product() {
		
	}
	
	private void industry() {
		
	}
	
	private void consumer() {
		
	}
	
	
	
 	private void productInfo() {
		char productInfoChoice;
		do {
			System.out.print("--------------PRODUCT INFORMATION(PRODUCT, SUPPLIER, AND INDUSTRY/CONSUMER) SECTION--------------\nA. Add Product Information\nB. Delete Product Information\nC. Update Product Information\nD. Show Producs Information\nE. Search Product Information\nF. <- Go to Main Section");
			System.out.print("\nEnter your option: ");
			productInfoChoice = sc.next().charAt(0);
			
			switch(productInfoChoice) {
			case 'A':
			case 'a':
				addProductInfo();
				break;
			case 'B':
			case 'b':
				deleteProductInfo();
				break;
			case 'C':
			case 'c':
				updateProductInfo();
				break;
			case 'D':
			case 'd':
				viewProductInformation();
				break;
			case 'E':
			case 'e':
				searchProductInfo();
				break;
			case 'F':
			case 'f':
				System.out.println("-----Complete Product information Section-----");
				return;
			default:
				System.out.println("Your choice is wrong. ");
				
			}
			
		}while(productInfoChoice != 'F' || productInfoChoice != 'f');
		
		
		
	}
	
	private void searchProductInfo() {
		int search;
		do {
			System.out.print("--------------SEARCH PRODUCT INFORMATION DETAILS--------------\n1. Product Information ID\n2. Product Name\n3. Supplier Name\n4. Industry\5. Consumer Category\6. <- Go Back\nEnter your option: ");
			search = sc.nextInt();
			switch(search) {
			case 1: 
				System.out.print("Enter product Id: ");
				String productId;
				do {
					productId = sc.nextLine();
				}while(productId.isEmpty());
				try {
					if(util.validateProductinfoId(productId)) {
						service.searchProductInfoByProductInfoId(productId).forEach(e -> {
							System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Information", "Industry/Consumer Id", "Industry/Category");
							System.out.println();
							System.out.println("------------------------------------------------------------------------------------------------------------------------------");
							System.out.println(e);
						});						
					}
				}catch(InvalidProduct e) {
					System.out.println(e.getMessage());
				} catch(ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.print("Enter the product Name: ");
				String productName;
				do {
					productName = sc.nextLine();
				}while(productName.isEmpty());
				
				if(service.searchProductInfoByProductName(productName) == null) {
					System.out.println("Not Available "+productName);
				}
				else {
					service.searchProductInfoByProductName(productName).forEach(e->{
						System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Information", "Industry/Consumer Id", "Industry/Category");
						System.out.println();
						System.out.println("------------------------------------------------------------------------------------------------------------------------------");
						System.out.println(e);
					});					
				}
				
				break;
			case 3:
				System.out.print("Enter the Supplier Name: ");
				String supplierName;
				do {
					supplierName = sc.nextLine();
				}while(supplierName.isEmpty());
				
				if(service.searchProductInfoBysupplierName(supplierName) == null) {
					System.out.println("There is no supplier on this name: "+supplierName);
				}
				else {
					service.searchProductInfoBysupplierName(supplierName).forEach(e->{
						System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Information", "Industry/Consumer Id", "Industry/Category");
						System.out.println();
						System.out.println("------------------------------------------------------------------------------------------------------------------------------");
						System.out.println(e);
					});					
				}
				break;
			case 4:
				System.out.print("Enter the industry Name: ");
				String industry;
				do {
					industry = sc.nextLine();
				}while(industry.isEmpty());
				
				if(service.searchProductInfoByIndustry(industry) == null) {
					System.out.println("There is no industry on this name: "+industry);
				}
				else {
					service.searchProductInfoByIndustry(industry).forEach(e->{
						System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Information", "Industry/Consumer Id", "Industry/Category");
						System.out.println();
						System.out.println("------------------------------------------------------------------------------------------------------------------------------");
						System.out.println(e);
					});					
				}
				break;
			case 5:
				System.out.print("Enter the consumer category: ");
				String consumer;
				do {
					consumer = sc.nextLine();
				}while(consumer.isEmpty());
				
				if(service.searchProductInfoByConsumer(consumer) == null) {
					System.out.println("There is no supplier on this name: "+consumer);
				}
				else {
					service.searchProductInfoByConsumer(consumer).forEach(e->{
						System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Information", "Industry/Consumer Id", "Industry/Category");
						System.out.println();
						System.out.println("------------------------------------------------------------------------------------------------------------------------------");
						System.out.println(e);
					});					
				}
				break;
			case 6:
				System.out.println("Welcome Back Product Section......!!!");
				return ;
			default:
				System.out.println("Please select correct option....");
			}
		}while(search != 4);
		
		
	}	
	
	private void viewProductInformation(){
		System.out.println("--------------DISPLAY PRODUCT INFORMATION DETAILS--------------\nSelect Product Type......\n1. IndustrialGoods\n2. ConsumerGoods\n3. All Products Inforamtion\n4. <- Go Back");
		int goods = sc.nextInt();
		String typeOfGoods = null;
		do {
			switch(goods) {
			case 1: 
				typeOfGoods = "IndustrialGoods";
				break;
			case 2:
				typeOfGoods = "ConsumerGoods";
				break;
			case 3:
				typeOfGoods = "All";
				break;
			case 4:
				System.out.println("Welcome Back Product Section......!!!");
				return ;
			default:
				System.out.println("Please select correct option....");
			}
		}while(goods >= 4);
		try {
			
			service.viewProductInfo(typeOfGoods).forEach(e -> {
				System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Information","Industry/Category Id", "Industry/Consumer");
				System.out.println();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(e);
			});	
			
		}catch(ClassNotFoundException | SQLException | InvalidProduct e) {
			System.out.println(e.getMessage());
		}
	}
		
	private void updateProductInfo() {
		
		try {
			
			System.out.println("--------------UPDATE PRODUCT INFORMATION DETAILS--------------\nSelect Product Type......\n1. IndustrialGoods\n2. ConsumerGoods\n3. <- Go Back");
			String typeOfGoods = goodsType();
			if(typeOfGoods == null) {
				return;
			}
			System.out.println("-------"+typeOfGoods+" Products  information-------");
			System.out.print("Enter the number of products: ");
			int noOfProducts = sc.nextInt();
			sc.nextLine();
			while(noOfProducts<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfProducts = sc.nextInt();
				sc.nextLine();
			}
			System.out.print("Enter "+typeOfGoods+" name or category: ");
			String goods;
			do {
				goods = sc.nextLine();
			}while(goods.isEmpty());
			String goodsId = null ;
			if("IndustrialGoods".equals(typeOfGoods))
				goodsId = service.checkingIndustry(goods);
			else if("ConsumerGoods".equals(typeOfGoods))
				goodsId = service.checkingConsumer(goods);
			if(goodsId.isEmpty()) {
				if("IndustrialGoods".equals(typeOfGoods)) {
					System.out.print("Enter "+typeOfGoods+" description:");
					String description = sc.nextLine();
					goodsId = service.addIndustry(goods, description);			
				}
				else if("ConsumerGoods".equals(typeOfGoods)) {
					System.out.print("Enter "+typeOfGoods+" description:");
					String description = sc.nextLine();
					goodsId = service.addIndustry(goods, description);			
				}
				if(goodsId.isEmpty()) {
					System.out.println("sorry..!, This service corrently not working. please try again later.");
					updateProductInfo();
				}
			}
			System.out.println("Enter the product details formate is : \n[PRODUCT_INFO_ID:PRODUCT_NAME:PRODUCT_DESCRIPTION:UNIT_PRICE:SUPPLIER_ID]");
			int successProducts = 0;
			
			for(int i = 0; i < noOfProducts; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				String productInfo = null;
				if("IndustrialGoods".equals(typeOfGoods))
					productInfo = service.addProductInfo(productDetails+":"+goodsId+"IndustrialGoods");
				else if("ConsumerGoods".equals(typeOfGoods))
					productInfo = service.addProductInfo(productDetails+":"+goodsId+"ConsumerGoods");
				if(!productInfo.isEmpty()) {
					System.out.println(productInfo);
					successProducts++;
				}
			}
			
			if(successProducts==0) System.out.println("products/product inforamtion not updated....");
			else if(successProducts==1) System.out.println("Successfully "+successProducts+" product inforamtion updated.");
			else if(successProducts>1) System.out.println("Successfully "+successProducts+" products information updated.");
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			addProductInfo();
		}
		
	}
	
	private void deleteProductInfo() {
		try {
			System.out.print("--------------DELETE PRODUCT INFORMATION DETAILS--------------\nEnter number of product informations: ");
			int noOfProductInfos = sc.nextInt();
			while(noOfProductInfos<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfProductInfos = sc.nextInt();
			}
			int successfull = 0;			

			System.out.print("Enter product information id:");
			for(int i = 0; i < noOfProductInfos; i++) {
				String productId;
				do {
					productId = sc.nextLine();
				}while(productId.isEmpty());
				String deleteProductInfo = service.deleteProductInfo(productId);
				if(!deleteProductInfo.isEmpty()) {
					System.out.println(deleteProductInfo);
					successfull++;
				}	
			}
			if(successfull==0) System.out.println("products/product information not added....");
			else if(successfull==1) System.out.println("Successfully "+successfull+" product information added.");
			else if(successfull>1) System.out.println("Successfully "+successfull+" products information added.");
			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			deleteProductInfo();
		}
	}
	
	private void addProductInfo(){
		
		try {
			
			System.out.println("--------------ADDING PRODUCT INFORMATION DETAILS--------------\nSelect Product Type......\n1. IndustrialGoods\n2. ConsumerGoods\n3. <- Go Back");
			String typeOfGoods = goodsType();
			if(typeOfGoods == null) {
				return;
			}
			System.out.println("-------"+typeOfGoods+" Products information-------");
			System.out.print("Enter the number of products: ");
			int noOfProducts = sc.nextInt();
			sc.nextLine();
			while(noOfProducts<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfProducts = sc.nextInt();
				sc.nextLine();
			}
			System.out.print("Enter "+typeOfGoods+" name or category: ");
			String goods;
			do {
				goods = sc.nextLine();
			}while(goods.isEmpty());
			String goodsId = null ;
			if("IndustrialGoods".equals(typeOfGoods))
				goodsId = service.checkingIndustry(goods);
			else if("ConsumerGoods".equals(typeOfGoods))
				goodsId = service.checkingConsumer(goods);
			if(goodsId.isEmpty()) {
				if("IndustrialGoods".equals(typeOfGoods)) {
					System.out.print("Enter "+typeOfGoods+" description:");
					String description = sc.nextLine();
					goodsId = service.addIndustry(goods, description);			
				}
				else if("ConsumerGoods".equals(typeOfGoods)) {
					System.out.print("Enter "+typeOfGoods+" description:");
					String description = sc.nextLine();
					goodsId = service.addIndustry(goods, description);			
				}
				if(goodsId.isEmpty()) {
					System.out.println("sorry..!, This service corrently not working. please try again later.");
					addProductInfo();
				}
			}
			System.out.println("Enter the product information details formate is : \n[PRODUCT_NAME:PRODUCT_DESCRIPTION:UNIT_PRICE:SUPPLIER_ID]");
			int successProducts = 0;
			
			for(int i = 0; i < noOfProducts; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				String productInfo = null;
				if("IndustrialGoods".equals(typeOfGoods))
					productInfo = service.addProductInfo(productDetails+":"+goodsId+"IndustrialGoods");
				else if("ConsumerGoods".equals(typeOfGoods))
					productInfo = service.addProductInfo(productDetails+":"+goodsId+"ConsumerGoods");
				if(!productInfo.isEmpty()) {
					System.out.println(productInfo);
					successProducts++;
				}
			}
			
			if(successProducts==0) System.out.println("products/product information not added....");
			else if(successProducts==1) System.out.println("Successfully "+successProducts+" product information added.");
			else if(successProducts>1) System.out.println("Successfully "+successProducts+" products information added.");
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			addProductInfo();
		}
	}

	private String goodsType() {
		int choice;
		do {
			System.out.print("Enter option: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1: 
				return "IndustrialGoods";
			case 2:
				return "ConsumerGoods";
			case 3:
				System.out.println("Welcome Back Product Section......!!!");
				break;
			default:
				System.out.println("Please select correct option....");
			}
			
		}while(choice != 3);
		
		return null;
		
	}
	

}
