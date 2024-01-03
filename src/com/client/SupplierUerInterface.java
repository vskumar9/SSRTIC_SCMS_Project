package com.client;

import java.sql.SQLException;
import java.util.Scanner;


import com.exception.InvalidSupplierId;
import com.service.SupplierService;
import com.util.ApplicationUtil;

public class SupplierUerInterface {
	
	Scanner sc = new Scanner(System.in);
	SupplierService service = new SupplierService();
	ApplicationUtil util = new ApplicationUtil();
	
	public void supplier() {
		
		char supplierChoice;
		do {
			System.out.print("--------------SUPPLIER SECTION--------------\nA. Add Supplier\nB. Delete Supplier\nC. Update Supplier\nD. Show Supplier\nE. Search Supplier\nF. <- Go to Main Section");
			System.out.print("\nEnter your option: ");
			supplierChoice = sc.next().charAt(0);
			
			switch(supplierChoice) {
			case 'A':
			case 'a':
				addSupplier();
				break;
			case 'B':
			case 'b':
				deleteSupplier();
				break;
			case 'C':
			case 'c':
				updateSupplier();
				break;
			case 'D':
			case 'd':
				displaySuppliers();
				break;
			case 'E':
			case 'e':
				searchSupplier();
				break;
			case 'F':
			case 'f':
				System.out.println("-----Complete Supplier Section-----");
				return;
			default:
				System.out.println("Your choice is wrong.");
				
			}
			
		}while(supplierChoice != 'F' || supplierChoice != 'f');
		
		
	
		
	}

	private void searchSupplier() {
		int search;
		do {
			System.out.print("--------------SEARCH SUPPLIER DETAILS--------------\n1. Supplier ID\n2. Supplier Name\n3. <- Go Back\nEnter your option: ");
			search = sc.nextInt();
			switch(search) {
			case 1: 
				System.out.print("Enter Supplier Id: ");
				String supplierId;
				do {
					supplierId = sc.nextLine();
				}while(supplierId.isEmpty());
				try {
					if(util.validateSupplierId(supplierId)) {
						service.searchBySupplierId(supplierId).forEach(e -> {
							System.out.printf("%-15s%-15s%-15s%-15s%-15s","Supplier ID", "Supplier Name", "Contact Person", "Email", "Phone Number");
							System.out.println();
							System.out.println("------------------------------------------------------------------------------------------------------------------------------");
							System.out.println(e);
						});						
					}
				}catch(InvalidSupplierId e) {
					System.out.println(e.getMessage());
				} catch(ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
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
							System.out.printf("%-15s%-15s%-15s%-15s%-15s","Supplier ID", "Supplier Name", "Contact Person", "Email", "Phone Number");
							System.out.println();
							System.out.println("------------------------------------------------------------------------------------------------------------------------------");
							System.out.println(e);
						});					
					}					
				} catch(ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Welcome Back Product Section......!!!");
				return ;
			default:
				System.out.println("Please select correct option....");
			}
		}while(search != 3);
		
		
	}

	private void displaySuppliers() {
		System.out.println("--------------DISPLAY PRODUCT DETAILS--------------");
		try {	
			service.viewSuppliers().forEach(e -> {
				System.out.printf("%-15s%-15s%-15s%-15s%-15s","Supplier ID", "Supplier Name", "Contact Person", "Email", "Phone Number");
				System.out.println();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(e);
			});	
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void updateSupplier() {
		System.out.println("--------------UPDATE SUPPLIER DETAILS--------------");
		System.out.print("Enter number of suppliers: ");
		int noOfSuppliers= sc.nextInt();
		sc.nextLine();
		while(noOfSuppliers<=0) {
			System.out.println("Your ennter number is wrong... ");
			noOfSuppliers = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Enter the supplier details formate is : \n[SUPPLIER_ID:SUPPLIER_NAME:CONTACT_PERSON:EMAIL:PHONE_NUMBER]");
		int success = 0;
		try {
			for(int i = 0; i < noOfSuppliers; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				if(service.updateSupplier(productDetails)) {
					success++;
				}
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(success==0) System.out.println("suppliers/supplier not added....");
		else if(success==1) System.out.println("Successfully "+success+" supplier added.");
		else if(success>1) System.out.println("Successfully "+success+" suppliers added.");

		
	}

	private void deleteSupplier() {
		System.out.println("--------------DELETE SUPPLIER DETAILS--------------");
		System.out.print("Enter number of products: ");
		int noOfSuppliers = sc.nextInt();
		while(noOfSuppliers<=0) {
			System.out.println("Your enter number is wrong... ");
			noOfSuppliers = sc.nextInt();
		}
		System.out.println("Enter the Supplier Id's :");
		int successSupplier = 0;
		
		try {
			for(int i = 0; i < noOfSuppliers; i++) {
				String supplierId;
				do {
					supplierId = sc.nextLine();
				}while(supplierId.isEmpty());
				if(service.deleteSupplier(supplierId)) {
					successSupplier++;
				}
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(successSupplier == 0) System.out.println("products/product not deleted....");
		else if(successSupplier == 1) System.out.println("Successfully "+successSupplier+" supplier deleted.");
		else if(successSupplier > 1) System.out.println("Successfully "+successSupplier+" suppliers deleted.");
		
		
		
	}

	private void addSupplier() {
		
		System.out.println("--------------ADDING SUPPLIER DETAILS--------------");
		System.out.print("Enter number of suppliers: ");
		int noOfSuppliers= sc.nextInt();
		sc.nextLine();
		while(noOfSuppliers<=0) {
			System.out.println("Your ennter number is wrong... ");
			noOfSuppliers = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Enter the supplier details formate is : \n[SUPPLIER_NAME:CONTACT_PERSON:EMAIL:PHONE_NUMBER]");
		int success = 0;
		try {
			for(int i = 0; i < noOfSuppliers; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				if(service.addSupplier(productDetails)) {
					success++;
				}
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(success==0) System.out.println("suppliers/supplier not added....");
		else if(success==1) System.out.println("Successfully "+success+" supplier added.");
		else if(success>1) System.out.println("Successfully "+success+" suppliers added.");
		
	}

}
