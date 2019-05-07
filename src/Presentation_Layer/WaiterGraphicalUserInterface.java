package Presentation_Layer;

import Business_Layer.IRestaurantProcessing;
import Business_Layer.MenuItem;
import Business_Layer.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WaiterGraphicalUserInterface extends JPanel {
    private JTable menuTable;
    private JTable ordersTable;

    private JButton placeOrderBtn;
    private JButton generateBillBtn;
    private JButton saveRestaurantBtn;
    private JButton loadRestaurantBtn;

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
        saveRestaurantBtn = new JButton("Save Restaurant");
        loadRestaurantBtn = new JButton("Load Restaurant");

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
        btnPanel.add(saveRestaurantBtn);
        btnPanel.add(loadRestaurantBtn);

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
        saveRestaurantBtn.addActionListener(new SaveRestaurantListener());
        loadRestaurantBtn.addActionListener(new LoadRestaurantListener());
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

    public void setRestaurantProcessing(IRestaurantProcessing restaurantProcessing) {
        this.restaurantProcessing = restaurantProcessing;
    }

    private class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows = menuTable.getSelectedRows();

            if (selectedRows.length == 0) {
                view.printError("Empty order!");
            } else {
                try {
                    List<MenuItem> itemList = new ArrayList<>();

                    for (int row : selectedRows) {
                        itemList.add(restaurantProcessing.getById(Integer.parseInt(menuTable.getValueAt(row, 0).toString())));
                    }

                    restaurantProcessing.createOrder(new Order(getTableNumber()), itemList);
                } catch (IllegalArgumentException ex) {
                    view.printError(ex.getMessage());
                }
            }

            view.updateOrdersTable(restaurantProcessing.getOrders());
        }
    }

    private class BillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = ordersTable.getSelectedRow();

            if (selectedRow == -1) {
                view.printError("Select an order for the bill!");
            } else {
                restaurantProcessing.generateBill(Integer.parseInt(ordersTable.getValueAt(selectedRow, 0).toString()));
            }

            view.updateOrdersTable(restaurantProcessing.getOrders());
        }
    }

    private class SaveRestaurantListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.saveRestaurant();
        }
    }

    private class LoadRestaurantListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.loadRestaurant();
        }
    }
}
