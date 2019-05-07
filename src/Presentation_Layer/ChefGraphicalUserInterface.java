package Presentation_Layer;

import Business_Layer.CompositeProduct;
import Business_Layer.IRestaurantProcessing;
import Business_Layer.MenuItem;
import Business_Layer.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ChefGraphicalUserInterface extends JPanel implements Observer {
    private JTable cookingTable;

    private JButton finishOrder;

    private List<MenuItem> cooking;

    private IRestaurantProcessing restaurantProcessing;

    private View view;

    public ChefGraphicalUserInterface(IRestaurantProcessing restaurantProcessing, View view) {
        this.restaurantProcessing = restaurantProcessing;
        this.view = view;

        cooking = new ArrayList<>();

        cookingTable = new JTable();

        finishOrder = new JButton("Finish Order");

        cookingTable.setFillsViewportHeight(true);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        btnPanel.add(finishOrder);

        setLayout(new BorderLayout());
        add(cookingTable, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        finishOrder.addActionListener(new FinishOrderListener());
    }

    private void setCookingTable(JTable cookingTable) {
        this.cookingTable = cookingTable;
        JScrollPane scrollPane = new JScrollPane(cookingTable);
        add(scrollPane, BorderLayout.CENTER, 0);

        remove(1);
        revalidate();
        repaint();
    }

    public void setRestaurantProcessing(IRestaurantProcessing restaurantProcessing) {
        this.restaurantProcessing = restaurantProcessing;

        List<Order> orders = restaurantProcessing.getOrders();
        for (Order order : orders) {
            restaurantProcessing.getItemsForOrder(order).stream().filter(CompositeProduct.class::isInstance)
                    .forEach(menuItem -> cooking.add(menuItem));
        }

        setCookingTable(View.generateMenuTable(cooking));
    }

    @Override
    public void update(Observable o, Object arg) {
        cooking.add((MenuItem) arg);
        setCookingTable(View.generateMenuTable(cooking));
    }

    private class FinishOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows = cookingTable.getSelectedRows();

            if (selectedRows.length == 0) {
                view.printError("Select items to finish!");
            } else {
                try {
                    for (int row : selectedRows) {
                        cooking.remove(restaurantProcessing.getById(Integer.parseInt(cookingTable.getValueAt(row, 0).toString())));
                    }
                } catch (IllegalArgumentException ex) {
                    view.printError(ex.getMessage());
                }
            }

            setCookingTable(View.generateMenuTable(cooking));
        }
    }
}
