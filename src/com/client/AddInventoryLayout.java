package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddInventoryLayout extends JPanel implements ActionListener {

 private JButton addButton, deleteButton, updateButton, backButton;

 public AddInventoryLayout() {
     setLayout(new FlowLayout());
     addButton = new JButton("Add Inventory");
     addButton.addActionListener(this);

     deleteButton = new JButton("Delete Inventory");
     deleteButton.addActionListener(this);

     updateButton = new JButton("Update Inventory");
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
     // Handle other actions for adding, deleting, and updating inventory
     // Add specific logic based on your application requirements
 }
}
