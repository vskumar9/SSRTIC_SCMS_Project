package com.client;
import java.util.*;
import com.management.*;
import com.util.*;

public class UserInterface {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter  name");
		String name = sc.next();
		
		
		ProductManagement pd = new ProductManagement(name, "Kumar", 45, "srinivas", "Ram");
		
		System.out.println(pd);

	}

}
