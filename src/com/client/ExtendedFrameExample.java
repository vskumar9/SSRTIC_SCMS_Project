package com.client;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ExtendedFrameExample extends JFrame implements PropertyChangeListener {

 private AddProductLayout addProductLayout;
 private AddSupplierLayout addSupplierLayout;
 private AddInventoryLayout addInventoryLayout;
 private MainLayout mainLayout;

 public ExtendedFrameExample() {
     mainLayout = new MainLayout();
     mainLayout.addPropertyChangeListener(this);

     addProductLayout = new AddProductLayout();
     addProductLayout.addPropertyChangeListener(this);

     addSupplierLayout = new AddSupplierLayout();
     addSupplierLayout.addPropertyChangeListener(this);

     addInventoryLayout = new AddInventoryLayout();
     addInventoryLayout.addPropertyChangeListener(this);

     setContentPane(mainLayout);
     setTitle("Extended Frame Example");
     setSize(500, 400);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setVisible(true);
 }

 public void propertyChange(PropertyChangeEvent evt) {
     if ("switchToProductLayout".equals(evt.getPropertyName())) {
         setContentPane(addProductLayout);
     } else if ("switchToSupplierLayout".equals(evt.getPropertyName())) {
         setContentPane(addSupplierLayout);
     } else if ("switchToInventoryLayout".equals(evt.getPropertyName())) {
         setContentPane(addInventoryLayout);
     } else if ("switchToMainPanel".equals(evt.getPropertyName())) {
         setContentPane(mainLayout);
     }

     // Revalidate and repaint the frame to show the new content
     revalidate();
     repaint();
 }

 public static void main(String[] args) {
     new ExtendedFrameExample();
 }
}
