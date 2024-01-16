package com.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.model.OrderProcessing;
import com.service.OrderProcessingService;
import com.service.ProductService;
import com.util.ApplicationUtil;

public class OrderUserInterface {
	
	Scanner sc = new Scanner(System.in);
	ApplicationUtil util = new ApplicationUtil();
	OrderProcessingService service = new OrderProcessingService();
	ProductService productService = new ProductService();
	
	public void orderSection() {
		
		try {
			char orderChoice;
			do {
				System.out.print("--------------ORDER SECTION--------------\nA. Add order\nB. Update order\nC. Show order\nD. Search order\nE. <- Go to Main Section\nF. Delete Orders");
				System.out.print("\nEnter your option: ");
				
				orderChoice = sc.next().charAt(0);				
				sc.nextLine();
				switch(orderChoice) {
				case 'A':
				case 'a':
					// call addOrder method
					addOrder();
					break;
				case 'B':
				case 'b':
					// call updateOrder method
					updateOrder();
					break;
				case 'C':
				case 'c':
					// call displayOrder method
					displayOrder();
					break;
				case 'D':
				case 'd':
					// call searchOrder method
					searchOrder();
					break;
				case 'E':
				case 'e':
					// print string and exits the Order method this choice
					System.out.println("-----Complete Order Section-----");
					return;
				case 'F':
				case 'f':
					// call deleteOrder method
					deleteOrder();
					break;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(orderChoice != 'E' || orderChoice != 'e');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			orderSection();
		}
		
	}

	private void searchOrder() {
		try {
			ArrayList<String> listPro = new ArrayList<String>();
			ArrayList<OrderProcessing> list = new ArrayList<OrderProcessing>();
			int search;
			do {
				System.out.print("--------------SEARCH ORDER DETAILS--------------\n1. Order ID\n2. Customer Id\n3. Product Id\n4. Product Name\n5. <- Go Back\nEnter your option: ");
				search = sc.nextInt();
				switch(search) {
				case 1: 
					System.out.print("Enter Order Id: ");
					String orderId;
					do {
						orderId = sc.nextLine();
					}while(orderId.isEmpty());
					list = service.searchOrdersByOrderId(orderId);
					if(list == null) {
						System.out.println("There is no inventory on this Id: "+orderId);
					}else {
						System.out.printf("%-25s%-30s%-30s%-30s%-30s","Order ID", "Customer Id", "Order Date", "Total Amount", "Status");
						System.out.println();
						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
						list.forEach(e -> {
							System.out.println(e);
						});
						System.out.println();
						System.out.println("--------------------------****************************Order Details***************************--------------------------");
						System.out.println();
						System.out.printf("%-30s%-30s%-15s%-15s%-15s", "Product Id", "Product Name", "Price", "Quantity", "Amount");
						System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
						service.searchProductsByOrderId(orderId).forEach(e -> {
							System.out.println(e);
						});
					}
					System.out.println();
					break;
				case 2:
					System.out.print("Enter Customer Id: ");
					String customerId;
					do {
						customerId = sc.nextLine();
					}while(customerId.isEmpty());
					list = service.searchOrdersByCustomerId(customerId);
					if(list == null) {
						System.out.println("There is no Orders on this Customer Id: "+customerId);
					}else {
						System.out.printf("%-25s%-30s%-30s%-30s%-30s","Order ID", "Customer Id", "Order Date", "Total Amount", "Status");
						System.out.println();
						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
						list.forEach(e -> {
							System.out.println(e);
						});
					}
					System.out.println();
					break;
				case 3:
					System.out.print("Enter the Product Id: ");
					String productId;
					do {
						productId = sc.nextLine();
					}while(productId.isEmpty());
					listPro = service.searchOrdersByProductId(productId);
					if(listPro == null) {
						System.out.println("There is no Orders on this Product Id: "+productId);
					}
					else {
						System.out.printf("%-25s%-25s%-30s%-30s%-15s%-20s%-20s","Order ID", "Customer Id", "Order Date", "Product Name", "Quantity", "Unit Price", "Status");
						System.out.println();
						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
						listPro.forEach(e->{
							System.out.println(e);
						});	
					}
					System.out.println();
					break;
				case 4:
					System.out.print("Enter the Product Name: ");
					String productName;
					do {
						productName = sc.nextLine();
					}while(productName.isEmpty());
					
					listPro = service.searchOrdersByProductName(productName);
					if(listPro == null) {
						System.out.println("There is no Orders on this Product name: "+productName);
					}
					else {
						System.out.printf("%-25s%-25s%-30s%-30s%-15s%-20s%-20s","Order ID", "Customer Id", "Order Date", "Product Name", "Quantity", "Unit Price", "Status");
						System.out.println();
						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
						listPro.forEach(e->{
							System.out.println(e);
						});	
					}
					System.out.println();
					break;
				case 5:
					System.out.println("Welcome Back Main Section......!!!");
					return ;
				default:
					System.out.println("Please select correct option....");
				}
			}while(search != 5);
			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			e.printStackTrace();
			searchOrder();
		}
		
	}

	private void displayOrder() {
		try {
			System.out.println("--------------DISPLAY ORDER DETAILS--------------");
			System.out.printf("%-25s%-30s%-30s%-30s%-30s","Order ID", "Customer Id", "Order Date", "Total Amount", "Status");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			if(service.viewOrders() == null) {
				System.out.println(service.viewOrders());
			}
			service.viewOrders().forEach(e -> {
				System.out.println(e);
			});
			System.out.println();
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			e.printStackTrace();
		}
	}

	private void updateOrder() {
		try {
			System.out.print("Enter number of orders: ");
			int noOfOrders = sc.nextInt();
			while(noOfOrders<1) {
				System.out.println("Your enter number is wrong... ");
				noOfOrders = sc.nextInt();
			}
			sc.nextLine();
			int success = 0;
			for(int i = 0; i < noOfOrders; i++) {
				System.out.println("Enter Order Id's: ");
				String orderId = null;
				do {
					orderId = sc.nextLine();
				}while(orderId == null);
				System.out.println("Select Order Status: 1. Pending 2. Processing 3. Shipped 4. Dispatched 5. Delivered 6. Return 7.Completed 8.Cancel");
				int choice = sc.nextInt();
				sc.nextLine();
				String status = null;
				do {
					switch(choice) {
					case 1: status = "Pending";break;
					case 2: status = "Processing";break;
					case 3: status = "Shipped";break;
					case 4: status = "Dispatched";break;
					case 5: status = "Delivered";break;
					case 6: status = "Return";break;
					case 7: status = "Completed";break;
					case 8: status = "Cancel";break;
					default: System.out.println("Wrong selection order status. Try again.");
					}					
				}while(status == null);
				if(service.updateOrder(orderId, status)) {
					success++;
				}
			}
			if(success==0) System.out.println("Orders/Order not Updated....");
			else if(success==1) System.out.println("Successfully "+success+" Order Updated.");
			else if(success>1) System.out.println("Successfully "+success+" Orders Updated.");			
			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			updateOrder();
		}
	}

	private void deleteOrder() {
		try {
			System.out.print("Enter number of orders: ");
			int noOfOrders = sc.nextInt();
			while(noOfOrders<1) {
				System.out.println("Your enter number is wrong... ");
				noOfOrders = sc.nextInt();
			}
			sc.nextLine();
			int success = 0;
			for(int i = 0; i < noOfOrders; i++) {
				System.out.println("Enter Order Id's: ");
				String orderId = null;
				do {
					orderId = sc.nextLine();
				}while(orderId == null);
				if(service.deleteOrder(orderId)) {
					success++;
				}
				else {
					System.out.println("You want delete this order enter: YES/Y");
					String deleteOrder;
					do {
						deleteOrder = sc.nextLine();
					} while(deleteOrder == null);
					if("yes".equalsIgnoreCase(deleteOrder) || "y".equalsIgnoreCase(deleteOrder)) {
						if(service.deleteProducts(orderId)) {
							if(service.deleteOrder(orderId)) {
								success++;
							}
						}
					}
				}
			}
			if(success==0) System.out.println("orders/order not deleted....");
			else if(success==1) System.out.println("Successfully "+success+" order deleted.");
			else if(success>1) System.out.println("Successfully "+success+" orders deleted.");			
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			deleteOrder();
		}
	}

	private void addOrder() {
		try {
			System.out.print("Enter customerID: ");
			String customerId;
			do {
				customerId = sc.nextLine();
			}while(customerId == null);
			if(!service.ExistCustomerId(customerId)) {
				System.out.println("Customer not exists.");
				return;
			}
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			System.out.println("IF ORDER IS COMPLETE ENTER: DONE/COMPLETE");
			System.out.println("Enter order details format is: [PRODUCT_ID:NUMBER_OF_QUANTITY]");
			String orderDetails = null;
			outerLoop:
			while(true) {
				String productDetails = null;
				do {
					productDetails = sc.nextLine();
				}while(productDetails == null);
				if("done".equalsIgnoreCase(productDetails) || "complete".equalsIgnoreCase(productDetails)) {
					break outerLoop;
				}
				if(productDetails.contains(":")) {
					String[] product = productDetails.split(":");
					if(productService.checkingProductId(product[0]) && product.length == 2) {
						if(Integer.valueOf(product[1])>0) {
							if(orderDetails == null)
								orderDetails = productDetails;
							else
								orderDetails = orderDetails +"-"+productDetails;
							}
						else {
							System.out.println("Invalid number of quantity.");
							}
						}else {
							System.out.println("Product details wrong");
						}
					}
			}
			if(orderDetails != null) {
				String order = service.addOrder(customerId, orderDetails);
				if(order == null) {
					System.out.println("Order failed.");
				}
				System.out.println(order);
			}
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			addOrder();
		}
	}

}
