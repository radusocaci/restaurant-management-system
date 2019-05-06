package Presentation_Layer;

import Business_Layer.IRestaurantProcessing;
import Business_Layer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ChefGraphicalUserInterface extends JPanel implements Observer {
    private JTable cookingTable;

    private JButton finishOrder;

    private List<MenuItem> cooking;

    private IRestaurantProcessing restaurantProcessing;

    public ChefGraphicalUserInterface(IRestaurantProcessing restaurantProcessing) {
        this.restaurantProcessing = restaurantProcessing;

        cooking = new ArrayList<>();

        cookingTable = new JTable();

        finishOrder = new JButton("Finish Order");

        cookingTable.setFillsViewportHeight(true);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        btnPanel.add(finishOrder);

        setLayout(new BorderLayout());
        add(cookingTable, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void setCookingTable(JTable cookingTable) {
        this.cookingTable = cookingTable;
        JScrollPane scrollPane = new JScrollPane(cookingTable);
        add(scrollPane, BorderLayout.CENTER, 0);

        remove(1);
        revalidate();
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        cooking.add((MenuItem) arg);
        setCookingTable(View.generateMenuTable(cooking));
    }
}
