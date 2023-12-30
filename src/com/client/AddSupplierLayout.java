package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSupplierLayout extends JPanel implements ActionListener {

 private JButton addButton, deleteButton, updateButton, backButton;

 public AddSupplierLayout() {
     setLayout(new FlowLayout());
     addButton = new JButton("Add Supplier");
     addButton.addActionListener(this);

     deleteButton = new JButton("Delete Supplier");
     deleteButton.addActionListener(this);

     updateButton = new JButton("Update Supplier");
     updateButton.addActionListener(this);

     backButton = new JButton("Back to Main Panel");
     backButton.addActionListener(this);

     add(addButton);
     add(deleteButton);
     add(updateButton);
     add(backButton);
 }

 public void actionPerformed(ActionEvent e) {
     if (e.getSource() == backButton) {
         firePropertyChange("switchToMainPanel", null, null);
     }
     // Handle other actions for adding, deleting, and updating suppliers
     // Add specific logic based on your application requirements
 }
}
