package com.client;

import java.util.Scanner;

public class WarehouseUserInterface {
	
	Scanner sc = new Scanner(System.in);
	
	public void warehouseSection() {
		
		try {
			char warehouseChoice;
			do {
				System.out.print("--------------WAREHOUSE SECTION--------------\nA. Add warehouse\nB. Delete warehouse\nC. Update warehouse\nD. Show warehouse\nE. <- Go to Main Section");
				System.out.print("\nEnter your option: ");
				
				warehouseChoice = sc.next().charAt(0);				
				
				switch(warehouseChoice) {
				case 'A':
				case 'a':
					// call addWarehouse method
					addWarehouse();
					break;
				case 'B':
				case 'b':
					// call deleteWarehouse method
					deleteWarehouse();
					break;
				case 'C':
				case 'c':
					// call updateWarehouse method
					updateWarehouse();
					break;
				case 'D':
				case 'd':
					// call displayWarehouse method
					displayWarehouse();
					break;
				case 'E':
				case 'e':
					// print string and exits the Warehouse method this choice
					System.out.println("-----Complete Warehouse Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(warehouseChoice != 'E' || warehouseChoice != 'e');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			warehouseSection();
		}
	}

	private void displayWarehouse() {
		// TODO Auto-generated method stub
		
	}

	private void updateWarehouse() {
		// TODO Auto-generated method stub
		
	}

	private void deleteWarehouse() {
		// TODO Auto-generated method stub
		
	}

	private void addWarehouse() {
		// TODO Auto-generated method stub
		
	}

}
