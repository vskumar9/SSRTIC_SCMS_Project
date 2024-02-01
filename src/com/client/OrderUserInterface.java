package com.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.exception.InvalidException;
import com.model.OrderProcessing;
import com.service.OrderProcessingService;
import com.service.ProductService;
import com.util.ApplicationUtil;

public class OrderUserInterface {
	
	// Create objects. it's help's to accessing class inside methods
	Scanner sc = new Scanner(System.in);
	ApplicationUtil util = new ApplicationUtil();
	OrderProcessingService service = new OrderProcessingService();
	ProductService productService = new ProductService();
	
	// Helper method to order section Console user interface method
	public void orderSection() {
		
		try {
			char orderChoice;
			do {
				System.out.print("--------------ORDER SECTION--------------\nA. Add order\nB. Update order\nC. Show order\nD. Search order\nE. <- Go to Main Section");
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
//				case 'F':
//				case 'f':
//					// call deleteOrder method
//					deleteOrder();
//					break;
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

	// Helper method to searching based on specific order id, customer id, product id, product name
	private void searchOrder() {
		try {
//			create two different Array lists
			ArrayList<String> listPro = new ArrayList<String>();
			ArrayList<OrderProcessing> list = new ArrayList<OrderProcessing>();
			int search;
			do {
				System.out.print("--------------SEARCH ORDER DETAILS--------------\n1. Order ID\n2. Customer Id\n3. Product Id\n4. <- Go Back\nEnter your option: ");
				search = sc.nextInt();
				switch(search) {
				case 1: 
//					searching order details specific order id
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
						System.out.println("-------------------------------------****************************Order Details***************************-------------------------------------");
						System.out.println();
						System.out.printf("%-30s%-30s%-15s%-15s%-15s", "Product Id", "Product Name", "Price", "Quantity", "Amount");
						System.out.println();
						System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
						service.searchProductsByOrderId(orderId).forEach(e -> {
							System.out.println(e);
						});
					}
					System.out.println();
					break;
				case 2:
//					searching order details specific customer id
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
						System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
						list.forEach(e -> {
							System.out.println(e);
						});
					}
					System.out.println();
					break;
				case 3:
//					searching order details specific product id
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
						System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
						listPro.forEach(e->{
							System.out.println(e);
						});	
					}
					System.out.println();
					break;
//				case 5:
////					seaching order details specific product name
//					System.out.print("Enter the Product Name: ");
//					String productName;
//					do {
//						productName = sc.nextLine();
//					}while(productName.isEmpty());
//					
//					listPro = service.searchOrdersByProductName(productName);
//					if(listPro == null) {
//						System.out.println("There is no Orders on this Product name: "+productName);
//					}
//					else {
//						System.out.printf("%-25s%-25s%-30s%-30s%-15s%-20s%-20s","Order ID", "Customer Id", "Order Date", "Product Name", "Quantity", "Unit Price", "Status");
//						System.out.println();
//						System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
//						listPro.forEach(e->{
//							System.out.println(e);
//						});	
//					}
//					System.out.println();
//					break;
				case 4:
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

	// Helper method to retrieve all orders
	private void displayOrder() {
		try {
			System.out.println("--------------DISPLAY ORDER DETAILS--------------");
			System.out.printf("%-25s%-30s%-30s%-30s%-30s","Order ID", "Customer Id", "Order Date", "Total Amount", "Status");
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
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

	// Helper method to update order status specific orders
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
				System.out.println("Select Order Status: 1. Shipped 2. Dispatched 3. Delivered 4. Return 5.Completed 6.Cancel");
				int choice = sc.nextInt();
				sc.nextLine();
				String status = null;
				do {
					switch(choice) {
//					case 1: status = "Pending";break;
//					case 2: status = "Processing";break;
					case 1: status = "Shipped";break;
					case 2: status = "Dispatched";break;
					case 3: status = "Delivered";break;
					case 4: status = "Return";break;
					case 5: status = "Completed";break;
					case 6: status = "Cancel";break;
					default: System.out.println("Wrong selection order status. Try again.");
					}					
				}while(status == null);
				if(service.updateOrder(orderId, status)) {
					success++;
				}
			}
			if(success==0) System.out.println("Orders/Order is not Updated....");
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

	// Helper method to delete order and ordered products
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

	// Helper method to add new order details
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
				else {
					if(paymentOptions(order)) {
						System.out.println(customerId+" Order Id: "+order);						
					}
				}
			}
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			addOrder();
		}
	}
	
	private boolean paymentOptions(String order) {
		int pay = 0;
		ArrayList<OrderProcessing> list = service.searchOrdersByOrderId(order);
		if(list.get(0).getStatus().equals("Pending") || list.get(0).getStatus().equals("Processing")) {
			System.out.println("Select payment methods: \n1. CREDIT & DEBITCARDS\n2. NET BANKING\n3. OTHER UPI APPS\n4. EMI\n5. CASH ON DELIVERY/PAY ON DELIVERY");
			String payment = null;
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				System.out.println("Enter format of card details :[CARD_NUMBER:HOLDER_NAME:EXPIRE_DATE:CVV_NUMBER]");
				payment = sc.nextLine();
				String[] cardDetails = payment.split(":");
				if(cardDetails.length == 4) {
					try {
						if(util.isValidCardNumber(cardDetails[0]) && util.isExpired(cardDetails[2]) && util.isValidCVV(cardDetails[3])) {
							pay = 1;
						}
					} catch (InvalidException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case 2:
				
				System.out.print("Enter your net banking username: ");
				String username = sc.nextLine();
				System.out.print("Enter your net banking password: ");
				String password = sc.nextLine();
				
				if (authenticateUser(username, password)) {
					System.out.println("Authentication successful. Proceed with net banking operations.");
					
					System.out.println("Your Payment: "+list.get(0).getTotalAmount()+"\nPay amount: Yes or No");
					String payment1 =sc.nextLine();
					if("yes".equalsIgnoreCase(payment1)) {
						System.out.println("Successfull payment done.");
						pay = 1;
					}
					else {
						System.out.println("failed payment.");
					}
				} else {
					System.out.println("Authentication failed. Please check your credentials.");
				}
			case 3:
				System.out.print("Enter your UPI ID: ");
				String upiId = sc.nextLine();
				
				try {
					if(util.isValidUPI(upiId)) {
						System.out.print("The amount to order: "+list.get(0).getTotalAmount());
						System.out.print("Enter UPI PIN: ");
						int pin = sc.nextInt();
						if (performUpiPayment(upiId, pin)) {
							System.out.println("UPI payment successful. Transaction ID: " + generateTransactionId());
							pay = 1;
						} else {
							System.out.println("UPI payment failed. Please check your UPI ID and try again.");
						}
					}
				} catch (InvalidException e) {
					System.out.println(e.getMessage());
				}
			case 4:
				System.out.println("Unavailable payment.");
			case 5:
				System.out.println("Successful order completed.");
			default:
				System.out.println("Your select Wrong option. Please try again......");
			}
			if(pay == 1) {
				if(service.updateOrder(order, "Paid")) {
					System.out.println("Your order is successful completed.");	
					return true;
				}
				else {
					System.out.println("Something wrong try again..... your amount 2 to 7 days return....");
				}
			}
			else {
				System.out.println("Retry payment: yes/no");
				String retry = sc.nextLine();
				if("yes".equalsIgnoreCase(retry) || "y".equalsIgnoreCase(retry)) {
					paymentOptions(order);
				}
				else {
					if(service.updateOrder(order, "Cancel")){
						System.out.println("Your order is Cancel.....");
						return true;
					}
					else {
						System.out.println("Something wrong try again.....");
					}
				}
			}
			
		}
		else {
			
		}
		return false;
	}
	
	private static boolean authenticateUser(String username, String password) {
        return "demoUser".equals(username) && "demoPassword".equals(password);
    }
	
	private static boolean performUpiPayment(String upiId, double amount) {
        return true;
    }
	
	private static String generateTransactionId() {
		return "TXN" + System.currentTimeMillis();
		}

}
