package com.client;

import java.util.Scanner;


import com.exception.InvalidException;
import com.service.SupplierService;
import com.util.ApplicationUtil;

public class SupplierUerInterface {
	
	Scanner sc = new Scanner(System.in);
	SupplierService service = new SupplierService();
	ApplicationUtil util = new ApplicationUtil();
	
	// Helper method to dashboard of supplier interface
	public void supplierSection() {
		
		try {
			char supplierChoice;
			do {
				System.out.print("--------------SUPPLIER SECTION--------------\nA. Add Supplier\nB. Delete Supplier\nC. Update Supplier\nD. Show Suppliers\nE. Search Supplier\nF. <- Go to Main Section");
				System.out.print("\nEnter your option: ");
				
				supplierChoice = sc.next().charAt(0);				
				
				switch(supplierChoice) {
				case 'A':
				case 'a':
					// call addSupplier method
					addSupplier();
					break;
				case 'B':
				case 'b':
					// call deleteSupplier method
					deleteSupplier();
					break;
				case 'C':
				case 'c':
					// call updateSupplier method
					updateSupplier();
					break;
				case 'D':
				case 'd':
					// call displaySuppliers method
					displaySuppliers();
					break;
				case 'E':
				case 'e':
					// call asearchSupplier method
					searchSupplier();
					break;
				case 'F':
				case 'f':
					// print string and exits the supplier method this choice
					System.out.println("-----Complete Supplier Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(supplierChoice != 'F' || supplierChoice != 'f');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			supplierSection();
		}
	}

	// Helps method to searching the supllier details based on the supplier id and supplier name
	private void searchSupplier() {
		try {
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
						System.out.printf("%-25s%-30s%-30s%-30s%-15s","Supplier ID", "Supplier Name", "Contact Person", "Email", "Phone Number");
						System.out.println();
						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
						service.searchBySupplierId(supplierId).forEach(e -> {
							System.out.println(e);
						});						
					}
					System.out.println();
				}catch(InvalidException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.print("Enter the Supplier Name: ");
				String supplierName;
				do {
					supplierName = sc.nextLine();
				}while(supplierName.isEmpty());
				
				if(service.searchBySupplierName(supplierName) == null) {
					System.out.println("There is no supplier on this name: "+supplierName);
				}
				else {
					System.out.printf("%-25s%-30s%-30s%-30s%-15s","Supplier ID", "Supplier Name", "Contact Person", "Email", "Phone Number");
					System.out.println();
					System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
					service.searchBySupplierName(supplierName).forEach(e->{
						System.out.println(e);
					});	
				}
				System.out.println();
				break;
			case 3:
				System.out.println("Welcome Back Product Section......!!!");
				return ;
			default:
				System.out.println("Please select correct option....");
			}
		}while(search != 3);
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			searchSupplier();
		} 
		
	}

	// Helps method to retrieve the all supplier details and displaying details in console
	private void displaySuppliers() {
		try {
			System.out.println("--------------DISPLAY SUPPLIER DETAILS--------------");
			System.out.printf("%-25s%-30s%-30s%-30s%-15s","Supplier ID", "Supplier Name", "Contact Person", "Email", "Phone Number");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			service.viewSuppliers().forEach(e -> {
				System.out.println(e);
			});
			System.out.println();			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
		} 
			
		
	}

	// Helps method to update the supplier details
	private void updateSupplier() {
		try {
			System.out.println("--------------UPDATE SUPPLIER DETAILS--------------");
			System.out.print("Enter number of suppliers: ");
			int noOfSuppliers= sc.nextInt();
			sc.nextLine();
			while(noOfSuppliers<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfSuppliers = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the supplier details formate is : \n[SUPPLIER_ID:SUPPLIER_NAME:CONTACT_PERSON:EMAIL:PHONE_NUMBER]");
			int success = 0;
			for(int i = 0; i < noOfSuppliers; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				if(service.updateSupplier(productDetails)) {
					success++;
				}
			}
			
			if(success==0) System.out.println("suppliers/supplier not updated....");
			else if(success==1) System.out.println("Successfully "+success+" supplier updated.");
			else if(success>1) System.out.println("Successfully "+success+" suppliers updated.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			updateSupplier();
		} 

		
	}

	// Helps method to delete supplier details
	private void deleteSupplier() {
		try {
			System.out.println("--------------DELETE SUPPLIER DETAILS--------------");
			System.out.print("Enter number of suppliers: ");
			int noOfSuppliers = sc.nextInt();
			while(noOfSuppliers<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfSuppliers = sc.nextInt();
			}
			System.out.println("Enter the Supplier Id's :");
			int successSupplier = 0;
			for(int i = 0; i < noOfSuppliers; i++) {
				String supplierId;
				do {
					supplierId = sc.nextLine();
				}while(supplierId.isEmpty());
				if(service.deleteSupplier(supplierId)) {
					successSupplier++;
				}
			}
			
			
			if(successSupplier == 0) System.out.println("suppliers/supplier not deleted....");
			else if(successSupplier == 1) System.out.println("Successfully "+successSupplier+" supplier deleted.");
			else if(successSupplier > 1) System.out.println("Successfully "+successSupplier+" suppliers deleted.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			deleteSupplier();
		} 
		
		
	}

	// Helps method to adding supplier details
	private void addSupplier() {
		
		try {
			System.out.println("--------------ADDING SUPPLIER DETAILS--------------");
			System.out.print("Enter number of suppliers: ");
			int noOfSuppliers= sc.nextInt();
			sc.nextLine();
			while(noOfSuppliers<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfSuppliers = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the supplier details formate is : \n[SUPPLIER_NAME:CONTACT_PERSON:EMAIL:PHONE_NUMBER]");
			int success = 0;
			for(int i = 0; i < noOfSuppliers; i++) {
				String productDetails;
				do {
					productDetails = sc.nextLine();
				}while(productDetails.isEmpty());
				String supplierId = service.addSupplier(productDetails);
				if( supplierId != null) {
					System.out.println(supplierId);
					success++;
				}
			}
			
			if(success==0) System.out.println("suppliers/supplier not added....");
			else if(success==1) System.out.println("Successfully "+success+" supplier added.");
			else if(success>1) System.out.println("Successfully "+success+" suppliers added.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			addSupplier();
		} 
	}

}
