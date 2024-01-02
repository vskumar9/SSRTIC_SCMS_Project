package com.client;

import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
    	
    	try (Scanner sc = new Scanner(System.in)) {
			int choice;
			do {
				
				System.out.print("1. Products\n2. Supplies\nEnter your choice: ");
				choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					new ProductUserInterface().product();
					break;
				case 2:
					new SupplierUerInterface().supplier();
					break;
					
				}
				
				
				
			}while(choice!=6);
		}
    	
    	
    }
}
