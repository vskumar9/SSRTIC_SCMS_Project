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
	
	
	
	private void product() {
		
		char productChoice;
		do {
			System.out.print("--------------PRODUCT SECTION--------------\nA. Add Product \nB. Delete Product \nC. Update Product \nD. Show Producs \nE. Search Product \nF. <- Go to Main Section");
			System.out.print("\nEnter your option: ");
			productChoice = sc.next().charAt(0);
			
			switch(productChoice) {
			case 'A':
			case 'a':
				addProduct();
				break;
			case 'B':
			case 'b':
				deleteProduct();
				break;
			case 'C':
			case 'c':
				updateProduct();
				break;
			case 'D':
			case 'd':
				viewProduct();
				break;
			case 'E':
			case 'e':
				System.out.println("-----Complete Product Section-----");
				return;
			default:
				System.out.println("Your choice is wrong. ");
				
			}
			
		}while(productChoice != 'E' || productChoice != 'e');
		
		
		
	}
	
	private void industry() {
		char industryChoice;
		do {
			System.out.print("--------------PRODUCT SECTION--------------\nA. Add Product \nB. Delete Product \nC. Update Product \nD. Show Producs \nE. Search Product \nF. <- Go to Main Section");
			System.out.print("\nEnter your option: ");
			industryChoice = sc.next().charAt(0);
			
			switch(industryChoice) {
			case 'A':
			case 'a':
				addIndustry();
				break;
			case 'B':
			case 'b':
				deleteIndustry();
				break;
			case 'C':
			case 'c':
				updateIndustry();
				break;
			case 'D':
			case 'd':
				viewIndustry();
				break;
			case 'E':
			case 'e':
				System.out.println("-----Complete Industry Section-----");
				return;
			default:
				System.out.println("Your choice is wrong. ");
				
			}
			
		}while(industryChoice != 'E' || industryChoice != 'e');
		
		
	}
	
	private void consumer() {
		char consumerChoice;
		do {
			System.out.print("--------------PRODUCT SECTION--------------\nA. Add Product \nB. Delete Product \nC. Update Product \nD. Show Producs \nE. Search Product \nF. <- Go to Main Section");
			System.out.print("\nEnter your option: ");
			consumerChoice = sc.next().charAt(0);
			
			switch(consumerChoice) {
			case 'A':
			case 'a':
				addConsumer();
				break;
			case 'B':
			case 'b':
				deleteConsumer();
				break;
			case 'C':
			case 'c':
				updateConsumer();
				break;
			case 'D':
			case 'd':
				viewConsumer();
				break;
			case 'E':
			case 'e':
				System.out.println("-----Complete Industry Section-----");
				return;
			default:
				System.out.println("Your choice is wrong. ");
				
			}
			
		}while(consumerChoice != 'E' || consumerChoice != 'e');
		
	}
	
	
	private void viewConsumer() {
		
		System.out.printf("%-30s%-30s%-30s","Consumer ID", "Consumer Name", "Description");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		service.viewConsumer().forEach(e -> {
			String[] consumer = e.split(":");
			System.out.printf(consumer[0],consumer[1], consumer[2], consumer[3]);
			System.out.println();
		});
	}
	
	private void updateConsumer() {
		
		try {
			System.out.println("--------------UPDATE CONSUMER DETAILS--------------");
			System.out.print("Enter the number of categories: ");
			int noOfCategories = sc.nextInt();
			sc.nextLine();
			while(noOfCategories<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfCategories = sc.nextInt();
				sc.nextLine();
			}
			
			System.out.println("Enter the consumer details formate is : \n[CONSUMER_ID:CONSUMER_NAME:CONSUMER_DESCRIPTION]");
			int success = 0;
			
			for(int i = 0; i < noOfCategories; i++) {
				String consumerDetails;
				do {
					consumerDetails = sc.nextLine();
				}while(consumerDetails.isEmpty());
				if(service.updateConsumer(consumerDetails)) 
					success++;
				}
			
			if(success==0) System.out.println("categories/category not deleted....");
			else if(success==1) System.out.println("Successfully "+success+" category deleted.");
			else if(success>1) System.out.println("Successfully "+success+" categories deleted.");
			
			} catch(Exception e) { 
				System.out.println();
				System.out.println("Something error. Please try again.....");
				System.out.println();
				updateProduct();
		}
		
	}
	
	private void deleteConsumer() {
		try {
			System.out.println("--------------DELETE CONSUMER DETAILS--------------");
			System.out.print("Enter the number of categories: ");
			int noOfCategories = sc.nextInt();
			sc.nextLine();
			while(noOfCategories<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfCategories = sc.nextInt();
				sc.nextLine();
			}
			int success = 0;
			
			for(int i = 0; i < noOfCategories; i++) {
				String consumerId;
				do {
					consumerId = sc.nextLine();
				}while(consumerId.isEmpty());
				
				if(service.deleteConsumer(consumerId)) {
					success++;
				}
			}
			
			if(success==0) System.out.println("categories/category not deleted....");
			else if(success==1) System.out.println("Successfully "+success+" category deleted.");
			else if(success>1) System.out.println("Successfully "+success+" categories deleted.");
			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			deleteProduct();
		}
	}
	
	private void addConsumer() {
		
		try {
			System.out.println("--------------ADDING CONSUMER DETAILS--------------");
			System.out.print("Enter the number of categories: ");
			int noOfCategories = sc.nextInt();
			sc.nextLine();
			while(noOfCategories<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfCategories = sc.nextInt();
				sc.nextLine();
			}
			
			System.out.println("Enter the consumer details formate is : \n[CONSUMER_NAME:CONSUMER_DESCRIPTION]");
			int success = 0;
			
			for(int i = 0; i < noOfCategories; i++) {
				String consumerDetails;
				do {
					consumerDetails = sc.nextLine();
				}while(consumerDetails.isEmpty());
				String[] consumer = consumerDetails.split(":");
				String industry = service.addConsumer(consumer[0], consumer[1]);
				if(!industry.isEmpty()) {
					System.out.println("Industry Id: "+industry);
					success++;
				}
			}
			
			if(success==0) System.out.println("Industries/Industry not added....");
			else if(success==1) System.out.println("Successfully "+success+" Industry added.");
			else if(success>1) System.out.println("Successfully "+success+" Industries added.");
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			addProduct();
		}
		
		
	}
	
	private void viewIndustry() {
		
		System.out.printf("%-30s%-30s%-30s","Industry ID", "Industry Name", "Description");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		service.viewIndustry().forEach(e -> {
			String[] industry = e.split(":");
			System.out.printf(industry[0],industry[1], industry[2], industry[3]);
			System.out.println();
		});
	}
	
	private void updateIndustry() {
		
		try {
			System.out.println("--------------UPDATE INDUSTRY DETAILS--------------");
			System.out.print("Enter the number of industries: ");
			int noOfIndustries = sc.nextInt();
			sc.nextLine();
			while(noOfIndustries<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfIndustries = sc.nextInt();
				sc.nextLine();
			}
			
			System.out.println("Enter the industry details formate is : \n[INDUSTRY_ID:INDUSTRY_NAME:INDUSTRY_DESCRIPTION]");
			int success = 0;
			
			for(int i = 0; i < noOfIndustries; i++) {
				String industryDetails;
				do {
					industryDetails = sc.nextLine();
				}while(industryDetails.isEmpty());
				if(service.updateIndustry(industryDetails)) 
					success++;
				}
			
			if(success==0) System.out.println("industries/industry not deleted....");
			else if(success==1) System.out.println("Successfully "+success+" industry deleted.");
			else if(success>1) System.out.println("Successfully "+success+" industries deleted.");
			
			} catch(Exception e) { 
				System.out.println();
				System.out.println("Something error. Please try again.....");
				System.out.println();
				updateProduct();
		}
		
	}
	
	private void deleteIndustry() {
		try {
			System.out.println("--------------DELETE INDUSTRY DETAILS--------------");
			System.out.print("Enter the number of industries: ");
			int noOfIndustries = sc.nextInt();
			sc.nextLine();
			while(noOfIndustries<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfIndustries = sc.nextInt();
				sc.nextLine();
			}
			int success = 0;
			
			for(int i = 0; i < noOfIndustries; i++) {
				String industryId;
				do {
					industryId = sc.nextLine();
				}while(industryId.isEmpty());
				
				if(service.deleteIndustry(industryId)) {
					success++;
				}
			}
			
			if(success==0) System.out.println("industries/industry not deleted....");
			else if(success==1) System.out.println("Successfully "+success+" industry deleted.");
			else if(success>1) System.out.println("Successfully "+success+" industries deleted.");
			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			deleteProduct();
		}
	}
	
	private void addIndustry() {
		
		try {
			System.out.println("--------------ADDING INDUSTRY DETAILS--------------");
			System.out.print("Enter the number of industries: ");
			int noOfIndustry = sc.nextInt();
			sc.nextLine();
			while(noOfIndustry<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfIndustry = sc.nextInt();
				sc.nextLine();
			}
			
			System.out.println("Enter the industry details formate is : \n[INDUSTRY_NAME:INDUSTRY_DESCRIPTION]");
			int success = 0;
			
			for(int i = 0; i < noOfIndustry; i++) {
				String industryDetails;
				do {
					industryDetails = sc.nextLine();
				}while(industryDetails.isEmpty());
				String[] industrial = industryDetails.split(":");
				String industry = service.addIndustry(industrial[0], industrial[1]);
				if(!industry.isEmpty()) {
					System.out.println("Industry Id: "+industry);
					success++;
				}
			}
			
			if(success==0) System.out.println("Industries/Industry not added....");
			else if(success==1) System.out.println("Successfully "+success+" Industry added.");
			else if(success>1) System.out.println("Successfully "+success+" Industries added.");
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			addProduct();
		}
		
		
	}
	
	
	private void viewProduct() {
			
		System.out.printf("%-30s%-30s%-30s%-30s","Product ID", "Product Name", "Description", "Unit Price");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		service.viewProduct().forEach(e -> {
			String[] product = e.split(":");
			System.out.printf(product[0],product[1], product[2], product[3], product[4]);
			System.out.println();
		});	
	}
	
	private void updateProduct() {
		
		try {
			System.out.println("--------------UPDATE PRODUCT DETAILS--------------");
			System.out.print("Enter the number of products: ");
			int noOfProducts = sc.nextInt();
			sc.nextLine();
			while(noOfProducts<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfProducts = sc.nextInt();
				sc.nextLine();
			}
			
			System.out.println("Enter the product details formate is : \n[PRODUCT_ID:PRODUCT_NAME:PRODUCT_DESCRIPTION:UNIT_PRICE]");
			int successProducts = 0;
			
			for(int i = 0; i < noOfProducts; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				if(service.updateProduct(productDetails)) 
					successProducts++;
				}
			if(successProducts==0) System.out.println("products/product not updated....");
			else if(successProducts==1) System.out.println("Successfully "+successProducts+" product updated.");
			else if(successProducts>1) System.out.println("Successfully "+successProducts+" products updated.");
			} catch(Exception e) { 
				System.out.println();
				System.out.println("Something error. Please try again.....");
				System.out.println();
				updateProduct();
		}
		
	}
	
	private void deleteProduct() {
		try {
			System.out.println("--------------DELETE PRODUCT DETAILS--------------");
			System.out.print("Enter the number of products: ");
			int noOfProducts = sc.nextInt();
			sc.nextLine();
			while(noOfProducts<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfProducts = sc.nextInt();
				sc.nextLine();
			}
			int successProducts = 0;
			
			for(int i = 0; i < noOfProducts; i++) {
				String productId;
				do {
					productId = sc.nextLine();
				}while(productId.isEmpty());
				
				if(service.deleteProduct(productId)) {
					successProducts++;
				}
			}
			
			if(successProducts==0) System.out.println("products/product not deleted....");
			else if(successProducts==1) System.out.println("Successfully "+successProducts+" product deleted.");
			else if(successProducts>1) System.out.println("Successfully "+successProducts+" products deleted.");
			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			deleteProduct();
		}
	}
	
	private void addProduct() {
		try {
			System.out.println("--------------ADDING PRODUCT DETAILS--------------");
			System.out.print("Enter the number of products: ");
			int noOfProducts = sc.nextInt();
			sc.nextLine();
			while(noOfProducts<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfProducts = sc.nextInt();
				sc.nextLine();
			}
			
			System.out.println("Enter the product details formate is : \n[PRODUCT_NAME:PRODUCT_DESCRIPTION:UNIT_PRICE]");
			int successProducts = 0;
			
			for(int i = 0; i < noOfProducts; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				String productInfo = service.addProduct(productDetails);
				if(!productInfo.isEmpty()) {
					System.out.println(productInfo);
					successProducts++;
				}
			}
			
			if(successProducts==0) System.out.println("products/product not added....");
			else if(successProducts==1) System.out.println("Successfully "+successProducts+" product added.");
			else if(successProducts>1) System.out.println("Successfully "+successProducts+" products added.");
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			addProduct();
		}
		
		
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
