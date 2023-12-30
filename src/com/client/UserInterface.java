package com.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {

    private JPanel mainPanel, productPanel, supplierPanel, transportationPanel, warehousePanel, orderPanel, inventoryPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem productMenuItem, supplierMenuItem, transportationMenuItem, warehouseMenuItem, orderMenuItem, inventoryMenuItem, exitMenuItem;
    private JLabel label;
	private JButton backButton;

    public UserInterface() {
        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        // Create the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create the main menu
        menu = new JMenu("Menu");
        menuBar.add(menu);

        // Create menu items for different layouts
        productMenuItem = new JMenuItem("Product Layout");
        productMenuItem.addActionListener(this);
        menu.add(productMenuItem);

        supplierMenuItem = new JMenuItem("Supplier Layout");
        supplierMenuItem.addActionListener(this);
        menu.add(supplierMenuItem);

        transportationMenuItem = new JMenuItem("Transportation Layout");
        transportationMenuItem.addActionListener(this);
        menu.add(transportationMenuItem);

        warehouseMenuItem = new JMenuItem("Warehouse Layout");
        warehouseMenuItem.addActionListener(this);
        menu.add(warehouseMenuItem);

        orderMenuItem = new JMenuItem("Order Processing Layout");
        orderMenuItem.addActionListener(this);
        menu.add(orderMenuItem);

        inventoryMenuItem = new JMenuItem("Inventory Layout");
        inventoryMenuItem.addActionListener(this);
        menu.add(inventoryMenuItem);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        menu.add(exitMenuItem);

        // Create panels for different layouts
        productPanel = createLayoutPanel("Product Layout");
        supplierPanel = createLayoutPanel("Supplier Layout");
        transportationPanel = createLayoutPanel("Transportation Layout");
        warehousePanel = createLayoutPanel("Warehouse Layout");
        orderPanel = createLayoutPanel("Order Processing Layout");
        inventoryPanel = createLayoutPanel("Inventory Layout");

        // Set the main panel as the content pane of the frame
        setContentPane(mainPanel);
        setTitle("Extended Frame Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createLayoutPanel(String layoutName) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        label = new JLabel("This is the " + layoutName);
        backButton = new JButton("Back to Main Panel");
        backButton.addActionListener(this);
        panel.add(label);
        panel.add(backButton);
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == productMenuItem) {
            setContentPane(productPanel);
        } else if (e.getSource() == supplierMenuItem) {
            setContentPane(supplierPanel);
        } else if (e.getSource() == transportationMenuItem) {
            setContentPane(transportationPanel);
        } else if (e.getSource() == warehouseMenuItem) {
            setContentPane(warehousePanel);
        } else if (e.getSource() == orderMenuItem) {
            setContentPane(orderPanel);
        } else if (e.getSource() == inventoryMenuItem) {
            setContentPane(inventoryPanel);
        } else if (e.getSource() == exitMenuItem) {
            System.exit(0);
        } else if (e.getSource() == backButton) {
            setContentPane(mainPanel);
        }

        // Revalidate and repaint the frame to show the new content
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new UserInterface();
    }
}
