package com.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.exception.InvalidException;
import com.model.Transportation;
import com.service.OrderProcessingService;
import com.service.TransportationService;
import com.util.ApplicationUtil;

public class TransportationUserInterface {
	
	Scanner sc = new Scanner(System.in);
	ApplicationUtil util = new ApplicationUtil();
	TransportationService service = new TransportationService();
	OrderProcessingService orderService = new OrderProcessingService();
	
	
	public void shipmentSection() {
		try {
			char choice;
			do{
			System.out.print("--------------TRNASPORT SECTION--------------\nA. Add Trnasport\nB. Update Transport\nC. Show Trnasport\nD. Search Trnasport\nE. <- Go to Main Section");
			System.out.print("\nEnter your option: ");
				choice = sc.next().charAt(0);
				sc.nextLine();
				switch(choice) {
				case 'a':
				case 'A':
					// call addShipment method
					addShipment();
					break;
				case 'b':
				case 'B':
					// call updateShipment method
					updateShipment();
					break;
				case 'c':
				case 'C':
					// call viewShipment method
					viewShipment();
					break;
				case 'd':
				case 'D':
					// call searchShipment method
					searchShipment();
					break;
				case 'e':
				case 'E':
					// print string and exits the Transport method this choice
					System.out.println("-----Complete Transport Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
				}
			}while(choice != 'e' || choice != 'E');
			
			
		} catch (Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			shipmentSection();
		}
	}
	
	private void searchShipment() {
		ArrayList<Transportation> list = new ArrayList<Transportation>();
		ArrayList<String> list2 = new ArrayList<String>();
		
		try {
			int search;
			do {
				System.out.print("--------------SEARCH TRNASPORT DETAILS--------------\n1. Trnasport ID\n2. Carrier Id\n3 Order Id <- Go Back\nEnter your option: ");
				search = sc.nextInt();
				switch(search) {
				case 1: 
					System.out.print("Enter Trnasport Id: ");
					String trnasportId;
					do {
						trnasportId = sc.nextLine();
					}while(trnasportId.isEmpty());
					try {
						if(util.isValidShipment(trnasportId)) {
							list = service.searchShipmentById(trnasportId);
							if(list == null) {
								System.out.println("There is no shipment on this Id: "+trnasportId);
							}else {
								System.out.println("--------------DISPLAY TRNASPORT DETAILS--------------");
								System.out.printf("%-25s%-25s%-25s%-25s", "shipmentId", "orderId", "carrierId", "shipmentStatus");
								System.out.println();
								System.out.println("----------------------------------------------------------------------------------------------------------------------");
								list.forEach(e -> {
									System.out.println(e);
								});															
							}
						}
						System.out.println();
					}catch(InvalidException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.print("Enter the Carrier Id: ");
					String carrierId;
					do {
						carrierId = sc.nextLine();
					}while(carrierId.isEmpty());
					System.out.println("Yes");
					list2 = service.searchCarrierById(carrierId);
					if(!list2.isEmpty()) {
						System.out.printf("%-30s%-30s%-30s%-30s%-30s", "carrierID", "carrierName", "contactPerson", "contactEmail", "contactPhone");
						System.out.println();
						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
						list2.forEach(e -> {
							System.out.println(e);
						});
						list = service.searchTransportByCarrierId(carrierId);
						System.out.println();
						System.out.println("************************************************************************************************************************************");
						System.out.println();
						if(list == null) {
							System.out.println("There is no carrier on this Id: "+carrierId);
						}
						else {
							System.out.printf("%-25s%-50s%-15s%-15s","Inventory ID", "Product information", "quntityInStock", "lastStockUpdate");
							System.out.println();
							System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
							list.forEach(e->{
								System.out.println(e);
							});	
						}
					}
					System.out.println();
					break;
				case 3:
					System.out.println("Welcome Back Main Section......!!!");
					return ;
				default:
					System.out.println("Please select correct option....");
				}
			}while(search != 3);
			} catch(Exception e) {
				System.out.println();
				System.out.println("Something error. Please try again.....");
				System.out.println();
				sc.nextLine();
				searchShipment();
			} 
		
		
	}
	
	private void viewShipment() {
		ArrayList<Transportation> list = new ArrayList<Transportation>();
		try {
			list = service.viewShipment();
			if(!list.isEmpty()) {
				System.out.println("--------------DISPLAY TRNASPORT DETAILS--------------");
				System.out.printf("%-25s%-25s%-25s%-25s", "shipmentId", "orderId", "carrierId", "shipmentStatus");
				System.out.println();
				System.out.println("----------------------------------------------------------------------------------------------------------------------");
				list.forEach(e -> {
					System.out.println(e);
				});				
			}
			else {
				System.out.println("No Data.");
			}
			System.out.println();
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			e.printStackTrace();
			shipmentSection();
		} 
		
	}

	private void updateShipment() {
		try {
			System.out.print("Enter number of Transport's: ");
			int noOfTransport = sc.nextInt();
			while(noOfTransport<1) {
				System.out.println("Your enter number is wrong... ");
				noOfTransport = sc.nextInt();
			}
			sc.nextLine();
			int success = 0;
			for(int i = 0; i < noOfTransport; i++) {
				System.out.println("Enter Transport Id's: ");
				String transportId = null;
				do {
					transportId = sc.nextLine();
				}while(transportId == null);
				System.out.println("Select Order Status: 1. Shipped 2. Dispatched 3. Delivered 4. Return 5.Completed 6.Cancel");
				int choice = sc.nextInt();
				sc.nextLine();
				String status = null;
				do {
					switch(choice) {
					case 1: status = "Shipped";break;
					case 2: status = "Dispatched";break;
					case 3: status = "Delivered";break;
					case 4: status = "Return";break;
					case 5: status = "Completed";break;
					case 6: status = "Cancel";break;
					default: System.out.println("Wrong selection order status. Try again.");
					}					
				}while(status == null);
				if(service.updateShipment(transportId, status)) {
					success++;
				}
				else {
					System.out.println("Transportation Id is not existed.");
				}
			}
			if(success==0) System.out.println("Transport's/Transport is not Updated....");
			else if(success==1) System.out.println("Successfully "+success+" Transport Updated.");
			else if(success>1) System.out.println("Successfully "+success+" Transport's Updated.");			
			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			shipmentSection();
		}
		
	}
	
	// Helps method to adding orders transport details
	private void addShipment() {
		try {
			System.out.println("--------------ADDING TRANSPORT DETAILS--------------");
			System.out.print("Enter Carrier ID: ");
			String carrierid= sc.nextLine();
			if(service.isCheckingCarrier(carrierid)) {
				System.out.print("Enter number of orders transport: ");
				int noOfOrders = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter order id's");
				String orders = null;
				for(int i = 0; i < noOfOrders ; i++) {
					String order = null;
					do {
						order = sc.nextLine();
					}while(order == null);
					if(orderService.searchOrdersByOrderId(order) != null) {
						if(orders == null) {
							orders = order;
						}
						else {
							orders += "-"+order;
						}						
					}
				}
				String shipmentId = service.addShipment(carrierid, orders);
				if(shipmentId != null) {
					System.out.println("Successfull added.\n"+shipmentId);
				}
			}
			else {
				System.out.println("Carrier id is not existed.");
			}
					
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();			
			sc.nextLine();
			shipmentSection();
		}
		
		
	}

}


