package com.client;

import java.util.Scanner;

import com.util.ApplicationUtil;

public class OrderUserInterface {
	
	Scanner sc = new Scanner(System.in);
	ApplicationUtil util = new ApplicationUtil();
	
	public void orderSection() {
		
		try {
			char orderChoice;
			do {
				System.out.print("--------------ORDER SECTION--------------\nA. Add order\nB. Delete order\nC. Update order\nD. Show order\nE. Search order\nF. <- Go to Main Section");
				System.out.print("\nEnter your option: ");
				
				orderChoice = sc.next().charAt(0);				
				
				switch(orderChoice) {
				case 'A':
				case 'a':
					// call addOrder method
					addOrder();
					break;
				case 'B':
				case 'b':
					// call deleteOrder method
					deleteOrder();
					break;
				case 'C':
				case 'c':
					// call updateOrder method
					updateOrder();
					break;
				case 'D':
				case 'd':
					// call displayOrder method
					displayOrder();
					break;
				case 'E':
				case 'e':
					// call searchOrder method
					searchOrder();
					break;
				case 'F':
				case 'f':
					// print string and exits the Order method this choice
					System.out.println("-----Complete Order Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(orderChoice != 'F' || orderChoice != 'f');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			orderSection();
		}
		
	}

	private void searchOrder() {
		// TODO Auto-generated method stub
		
	}

	private void displayOrder() {
		// TODO Auto-generated method stub
		
	}

	private void updateOrder() {
		// TODO Auto-generated method stub
		
	}

	private void deleteOrder() {
		// TODO Auto-generated method stub
		
	}

	private void addOrder() {
		// TODO Auto-generated method stub
		
	}

}
