package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductLayout extends JPanel implements ActionListener {

 private JButton addButton, deleteButton, updateButton, backButton;

 public AddProductLayout() {
     setLayout(new FlowLayout());
     addButton = new JButton("Add Product");
     addButton.addActionListener(this);

     deleteButton = new JButton("Delete Product");
     deleteButton.addActionListener(this);

     updateButton = new JButton("Update Product");
     updateButton.addActionListener(this);

     backButton = new JButton("Back to Main Panel");
     backButton.addActionListener(this);

     add(addButton);
     add(deleteButton);
     add(updateButton);
     add(backButton);
 }

 public void actionPerformed(ActionEvent e) {
	 if (e.getSource() == addButton) {
         // Call the addProduct function from DataManager
     } else if (e.getSource() == deleteButton) {
         // Call the deleteProduct function from DataManager
     } else if (e.getSource() == updateButton) {
         // Call the updateProduct function from DataManager
     } else if (e.getSource() == backButton) {
         firePropertyChange("switchToMainPanel", null, null);
     }
 }
}
