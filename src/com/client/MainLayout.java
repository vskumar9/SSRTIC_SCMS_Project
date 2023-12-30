package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLayout extends JPanel implements ActionListener {

 private JButton productButton, supplierButton, exitButton;
private JButton inventoryButton;

 public MainLayout() {
     setLayout(new FlowLayout());
     productButton = new JButton("Product Layout");
     productButton.addActionListener(this);
     supplierButton = new JButton("Supplier Layout");
     supplierButton.addActionListener(this);
     inventoryButton = new JButton("Inventory Layout");
     inventoryButton.addActionListener(this);
     exitButton = new JButton("Exit");
     exitButton.addActionListener(this);

     add(productButton);
     add(supplierButton);
     add(inventoryButton);
     add(exitButton);
 }

 public void actionPerformed(ActionEvent e) {
     if (e.getSource() == productButton) {
         firePropertyChange("switchToProductLayout", null, null);
     } else if (e.getSource() == supplierButton) { 
         firePropertyChange("switchToSupplierLayout", null, null);
     }if (e.getSource() == inventoryButton) {
         firePropertyChange("switchToInventoryLayout", null, null);
     } else if (e.getSource() == exitButton) {
         System.exit(0);
     }
 }
}

