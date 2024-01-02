package com.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.service.ProductService;
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
		// TODO Auto-generated method stub
		
	}

	private void displaySuppliers() {
		// TODO Auto-generated method stub
		
	}

	private void updateSupplier() {
		// TODO Auto-generated method stub
		
	}

	private void deleteSupplier() {
		// TODO Auto-generated method stub
		
	}

	private void addSupplier() {
		
		System.out.println("--------------ADDING SUPPLIER DETAILS--------------");
		int noOfSuppliers= sc.nextInt();
		sc.nextLine();
		while(noOfSuppliers<=0) {
			System.out.println("Your ennter number is wrong... ");
			noOfSuppliers = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Enter the product details formate is : \n[SUPPLIER_NAME:CONTACT_PERSON:EMAIL:PHONE_NUMBER]");
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
