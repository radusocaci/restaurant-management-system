package Presentation_Layer;

import Business_Layer.MenuItem;
import Business_Layer.Order;
import Business_Layer.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class View extends JFrame {
    WaiterGraphicalUserInterface waiterPanel;
    AdministratorGraphicalUserInterface administratorPanel;
    ChefGraphicalUserInterface chefPanel;

    public View(String title) throws HeadlessException {
        super(title);

        Restaurant restaurant = new Restaurant();

        waiterPanel = new WaiterGraphicalUserInterface(restaurant, this);
        administratorPanel = new AdministratorGraphicalUserInterface(restaurant, this);
        chefPanel = new ChefGraphicalUserInterface(restaurant);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Administrator", administratorPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Waiter", waiterPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Chef", chefPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setContentPane(tabbedPane);
        setSize((int) screenSize.getWidth() * 3 / 4, (int) screenSize.getHeight() * 3 / 4);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static JTable generateMenuTable(List<MenuItem> menuItemList) {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0, 5);
        JTable table = new JTable(defaultTableModel);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        defaultTableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Price", "Grams", "Stock"});

        for (MenuItem menuItem : menuItemList) {
            defaultTableModel.addRow(new Object[]{menuItem.getId(), menuItem.getName(), menuItem.getPrice(), menuItem.getGrams(), menuItem.getInStock()});
        }

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.setDefaultRenderer(table.getColumnClass(i), defaultTableCellRenderer);
        }

        return table;
    }

    public static JTable generateOrderTable(List<Order> ordersList) {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0, 1);
        JTable table = new JTable(defaultTableModel);

        defaultTableModel.setColumnIdentifiers(new String[]{"Order for Table"});

        for (Order order : ordersList) {
            defaultTableModel.addRow(new Object[]{order.getTable()});
        }

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.setDefaultRenderer(table.getColumnClass(i), defaultTableCellRenderer);
        }

        return table;
    }

    public void updateMenuTable(List<MenuItem> itemList) {
        waiterPanel.setMenuTable(View.generateMenuTable(itemList));
        administratorPanel.setMenuTable(View.generateMenuTable(itemList));
    }

    public void updateOrdersTable(JTable table) {
        waiterPanel.setOrdersTable(table);
    }

    public void printError(String message) {
        JOptionPane.showMessageDialog(this, message, "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
