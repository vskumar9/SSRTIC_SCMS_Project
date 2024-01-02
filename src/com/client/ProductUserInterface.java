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
	
	public void product() {
		char productChoice;
		do {
			System.out.print("--------------PRODUCT SECTION--------------\nA. Add Product\nB. Delete Product\nC. Update Product\nD. Show Producs\nE. Search Product\nF. <- Go to Main Section");
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
				retrieveProducts();
				break;
			case 'E':
			case 'e':
				searchProduct();
				break;
			case 'F':
			case 'f':
				System.out.println("-----Complete Product Section-----");
				return;
			default:
				System.out.println("Your choice is wrong. ");
				
			}
			
		}while(productChoice != 'F' || productChoice != 'f');
		
		
		
	}
	
	private void searchProduct() {
		int search;
		do {
			System.out.print("--------------SEARCH PRODUCT DETAILS--------------\n1. Product ID\n2. Product Name\n3. Supplier Name\n4. <- Go Back\nEnter your option: ");
			search = sc.nextInt();
			switch(search) {
			case 1: 
				System.out.print("Enter product Id: ");
				String productId;
				do {
					productId = sc.nextLine();
				}while(productId.isEmpty());
				try {
					if(util.validateProductId(productId)) {
						service.searchByProductId(productId).forEach(e -> {
							System.out.printf("%-15s%-15s%-15s%-15s%-15s%-30s%-15s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Name", "Supplier Address", "Industry/Category");
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
				
				try {
					if(service.searchByProductName(productName) == null) {
						System.out.println("Not Available "+productName);
					}
					else {
						service.searchByProductName(productName).forEach(e->{
							System.out.printf("%-15s%-15s%-15s%-15s%-15s%-30s%-15s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Name", "Supplier Address", "Industry/Category");
							System.out.println();
							System.out.println("------------------------------------------------------------------------------------------------------------------------------");
							System.out.println(e);
						});					
					}
				} catch(ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				} catch(InvalidProduct e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				System.out.print("Enter the Supplier Name: ");
				String supplierName;
				do {
					supplierName = sc.nextLine();
				}while(supplierName.isEmpty());
				
				try {
					if(service.searchBySupplierName(supplierName) == null) {
						System.out.println("There is no supplier on this name: "+supplierName);
					}
					else {
						service.searchBySupplierName(supplierName).forEach(e->{
							System.out.printf("%-15s%-15s%-15s%-15s%-15s%-30s%-15s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Name", "Supplier Address", "Industry/Category");
							System.out.println();
							System.out.println("------------------------------------------------------------------------------------------------------------------------------");
							System.out.println(e);
						});					
					}					
				} catch(ClassNotFoundException | SQLException | InvalidProduct e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Welcome Back Product Section......!!!");
				return ;
			default:
				System.out.println("Please select correct option....");
			}
		}while(search != 4);
		
		
	}	
	
	private void retrieveProducts() {
		System.out.println("--------------DISPLAY PRODUCT DETAILS--------------\nSelect Product Type......\n1. IndustrialGoods\n2. ConsumerGoods\n3. All Products\n4. <- Go Back");
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
			
			service.viewProducts(typeOfGoods).forEach(e -> {
				System.out.printf("%-15s%-15s%-15s%-15s%-15s%-30s%-15s","Product ID", "Product Name", "Description", "Unit Price", "Supplier Name", "Supplier Address", "Industry/Category");
				System.out.println();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(e);
			});	
			
		}catch(ClassNotFoundException | SQLException | InvalidProduct e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void updateProduct() {
		
		System.out.println("--------------UPDATE PRODUCT DETAILS--------------\nSelect Product Type......\n1. IndustrialGoods\n2. ConsumerGoods\n3. <- Go Back");
		String typeOfGoods = goodsType();
		System.out.println(typeOfGoods);
		if(typeOfGoods == null) {
			return;
		}
		System.out.print("Enter number of products: ");
		int noOfProducts = sc.nextInt();
		while(noOfProducts<=0) {
			System.out.println("Your ennter number is wrong... ");
			noOfProducts = sc.nextInt();
		}
		System.out.println("Enter the product details formate is : \n[PRODUCT_NAME:PRODUCT_DESCRIPTION:UNIT_PRICE:SUPPLIER_NAME:SUPPLIER_ADDRESS:INDUSTRIAL_OR_CATEGORY]");
		int successProducts = 0;
		
		try {
			for(int i = 0; i < noOfProducts; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				if(service.updateProduct(productDetails+":"+typeOfGoods)) {
					successProducts++;
				}
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(successProducts==0) System.out.println("products/product not updated....");
		else if(successProducts==1) System.out.println("Successfully "+successProducts+" product updated.");
		else if(successProducts>1) System.out.println("Successfully "+successProducts+" products updated.");
	}
	
	private void deleteProduct() {
		
		System.out.println("--------------DELETE PRODUCT DETAILS--------------\nSelect Product Type......\\n1. IndustrialGoods\\n2. ConsumerGoods\\n3. <- Go Back");
		String typeOfGoods = goodsType();
		if(typeOfGoods == null) {
			return;
		}
		System.out.println("-------"+typeOfGoods+" Products-------");
		System.out.print("Enter number of products: ");
		int noOfProducts = sc.nextInt();
		while(noOfProducts<=0) {
			System.out.println("Your ennter number is wrong... ");
			noOfProducts = sc.nextInt();
		}
		System.out.println("Enter the product Id's :");
		int successProducts = 0;
		
		try {
			for(int i = 0; i < noOfProducts; i++) {
				String productId;
				do {
					productId = sc.nextLine();
				}while(productId.isEmpty());
				if(service.deleteProduct(productId, typeOfGoods)) {
					successProducts++;
				}
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(successProducts==0) System.out.println("products/product not deleted....");
		else if(successProducts==1) System.out.println("Successfully "+successProducts+" product deleted.");
		else if(successProducts>1) System.out.println("Successfully "+successProducts+" products deleted.");
		
		
		
	}

	private void addProduct(){
		System.out.println("--------------ADDING PRODUCT DETAILS--------------\nSelect Product Type......\n1. IndustrialGoods\n2. ConsumerGoods\n3. <- Go Back");
		String typeOfGoods = goodsType();
		if(typeOfGoods == null) {
			return;
		}
		System.out.println("-------"+typeOfGoods+" Products-------");
		System.out.print("Enter the number of products: ");
		int noOfProducts = sc.nextInt();
		sc.nextLine();
		while(noOfProducts<=0) {
			System.out.println("Your ennter number is wrong... ");
			noOfProducts = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Enter the product details formate is : \n[PRODUCT_NAME:PRODUCT_DESCRIPTION:UNIT_PRICE:SUPPLIER_NAME:SUPPLIER_ADDRESS:INDUSTRIAL_OR_CATEGORY]");
		int successProducts = 0;
		
		try {
			for(int i = 0; i < noOfProducts; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				if(service.addProduct(productDetails+":"+typeOfGoods)) {
					successProducts++;
				}
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(successProducts==0) System.out.println("products/product not added....");
		else if(successProducts==1) System.out.println("Successfully "+successProducts+" product added.");
		else if(successProducts>1) System.out.println("Successfully "+successProducts+" products added.");
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
