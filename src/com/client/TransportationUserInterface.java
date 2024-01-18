package com.client;

import java.util.Scanner;

public class TransportationUserInterface {
	
	Scanner sc = new Scanner(System.in);
	
	public void shipmentSection() {
		
		int choice;
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			addShipment();
			break;
		case 2:
			deleteShipment();
			break;
		case 3:
			updateShipment();
			break;
		case 4:
			viewShipment();
			break;
		case 5:
			searchShipment();
			break;
		default:
			System.out.println();
		}
		
	}
	
	private void searchShipment() {
		
	}
	
	private void viewShipment() {
		
	}

	private void updateShipment() {
		
	}
	
	private void deleteShipment() {
		
	}
	
	
	private void addShipment() {
		
	}

}


