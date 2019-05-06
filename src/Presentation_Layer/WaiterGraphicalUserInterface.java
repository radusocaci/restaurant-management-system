package Presentation_Layer;

import Business_Layer.IRestaurantProcessing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaiterGraphicalUserInterface extends JPanel {
    private JTable menuTable;
    private JTable ordersTable;

    private JButton placeOrderBtn;
    private JButton generateBillBtn;

    private JTextField tableNumberTF;

    private JLabel tableNumberLabel;

    private JPanel tablePanel;

    private IRestaurantProcessing restaurantProcessing;

    private View view;

    public WaiterGraphicalUserInterface(IRestaurantProcessing restaurantProcessing, View view) {
        this.restaurantProcessing = restaurantProcessing;
        this.view = view;

        menuTable = new JTable();
        ordersTable = new JTable();

        placeOrderBtn = new JButton("Place Order");
        generateBillBtn = new JButton("Generate Bill");

        tableNumberTF = new JTextField("");
        tableNumberTF.setColumns(10);

        tableNumberLabel = new JLabel("Table Number: ");

        menuTable.setFillsViewportHeight(true);
        ordersTable.setFillsViewportHeight(true);

        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        dataPanel.add(tableNumberLabel);
        dataPanel.add(tableNumberTF);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        btnPanel.add(placeOrderBtn);
        btnPanel.add(generateBillBtn);

        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        southPanel.add(dataPanel);
        southPanel.add(btnPanel);

        tablePanel = new JPanel(new GridLayout(1, 2));
        tablePanel.add(menuTable);
        tablePanel.add(ordersTable);

        setLayout(new BorderLayout());
        add(tablePanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        placeOrderBtn.addActionListener(new OrderListener());
        generateBillBtn.addActionListener(new BillListener());
    }

    public void setMenuTable(JTable menuTable) {
        this.menuTable = menuTable;
        JScrollPane scrollPane = new JScrollPane(menuTable);
        tablePanel.add(scrollPane, 0);

        tablePanel.remove(1);
        revalidate();
        repaint();
    }

    public void setOrdersTable(JTable ordersTable) {
        this.ordersTable = ordersTable;
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        tablePanel.add(scrollPane, 1);

        tablePanel.remove(2);
        revalidate();
        repaint();
    }

    public int getTableNumber() throws NumberFormatException {
        return Integer.parseInt(tableNumberTF.getText());
    }

    private class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class BillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
