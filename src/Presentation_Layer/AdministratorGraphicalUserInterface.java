package Presentation_Layer;

import Business_Layer.BaseProduct;
import Business_Layer.CompositeProduct;
import Business_Layer.IRestaurantProcessing;
import Business_Layer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Socaci Radu Andrei
 */
public class AdministratorGraphicalUserInterface extends JPanel {
    private JTable menuTable;

    private JButton addMenuItemBtn;
    private JButton deleteMenuItemBtn;
    private JButton updateMenuItemBtn;

    private JTextField idTF;
    private JTextField nameTF;
    private JTextField priceTF;
    private JTextField stockTF;
    private JTextField quantityTF;

    private JCheckBox composite;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel stockLabel;
    private JLabel quantityLabel;

    private IRestaurantProcessing restaurantProcessing;

    private View view;

    /**
     * Creates the administrator panel
     *
     * @param restaurantProcessing restaurant reference
     * @param view                 view reference
     */
    public AdministratorGraphicalUserInterface(IRestaurantProcessing restaurantProcessing, View view) {
        this.restaurantProcessing = restaurantProcessing;
        this.view = view;

        menuTable = new JTable();

        addMenuItemBtn = new JButton("Add Menu Item");
        deleteMenuItemBtn = new JButton("Delete Menu Item");
        updateMenuItemBtn = new JButton("Update Menu Item");

        idTF = new JTextField("");
        nameTF = new JTextField("");
        priceTF = new JTextField("");
        stockTF = new JTextField("");
        quantityTF = new JTextField("");

        idTF.setColumns(10);
        nameTF.setColumns(10);
        priceTF.setColumns(10);
        stockTF.setColumns(10);
        quantityTF.setColumns(10);

        idLabel = new JLabel("ID: ");
        nameLabel = new JLabel("Name: ");
        priceLabel = new JLabel("Price: ");
        stockLabel = new JLabel("In Stock: ");
        quantityLabel = new JLabel("Grams: ");

        composite = new JCheckBox("Composite", false);

        menuTable.setFillsViewportHeight(true);

        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        dataPanel.add(idLabel);
        dataPanel.add(idTF);
        dataPanel.add(nameLabel);
        dataPanel.add(nameTF);
        dataPanel.add(priceLabel);
        dataPanel.add(priceTF);
        dataPanel.add(quantityLabel);
        dataPanel.add(quantityTF);
        dataPanel.add(stockLabel);
        dataPanel.add(stockTF);
        dataPanel.add(composite);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        btnPanel.add(addMenuItemBtn);
        btnPanel.add(deleteMenuItemBtn);
        btnPanel.add(updateMenuItemBtn);

        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        southPanel.add(dataPanel);
        southPanel.add(btnPanel);

        setLayout(new BorderLayout());
        add(menuTable, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        addMenuItemBtn.addActionListener(new AddListener());
        deleteMenuItemBtn.addActionListener(new DeleteListener());
        updateMenuItemBtn.addActionListener(new UpdateListener());
    }

    /**
     * updates the menu table JTable
     *
     * @param menuTable updated JTable
     */
    public void setMenuTable(JTable menuTable) {
        this.menuTable = menuTable;
        JScrollPane scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER, 0);

        remove(1);
        revalidate();
        repaint();
    }

    /**
     * converts and returns the id text field
     *
     * @return id
     * @throws NumberFormatException conversion failed
     */
    private int getId() throws NumberFormatException {
        return Integer.parseInt(idTF.getText());
    }

    /**
     * returns the name text field
     *
     * @return name
     */
    private String getNameTf() {
        return nameTF.getText();
    }

    /**
     * converts and returns the price text field
     *
     * @return price
     * @throws NumberFormatException conversion failed
     */
    private double getPrice() throws NumberFormatException {
        return Double.parseDouble(priceTF.getText());
    }

    /**
     * converts and returns the quantity text field
     *
     * @return quantity
     * @throws NumberFormatException conversion failed
     */
    private int getQuantity() throws NumberFormatException {
        return Integer.parseInt(quantityTF.getText());
    }

    /**
     * converts and returns the stock text field
     *
     * @return stock
     * @throws NumberFormatException conversion failed
     */
    private int getStock() throws NumberFormatException {
        return Integer.parseInt(stockTF.getText());
    }

    /**
     * set a new restaurant reference after deserialization
     *
     * @param restaurantProcessing restaurant reference
     */
    public void setRestaurantProcessing(IRestaurantProcessing restaurantProcessing) {
        this.restaurantProcessing = restaurantProcessing;
    }

    /**
     * Implements the functionality of the app when the add button in pressed
     *
     * @author Socaci Radu Andrei
     */
    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (composite.isSelected()) {
                int[] selectedRows = menuTable.getSelectedRows();

                if (selectedRows.length == 0) {
                    view.printError("Composite product needs base products!");
                } else {
                    try {
                        List<MenuItem> itemList = new ArrayList<>();

                        for (int row : selectedRows) {
                            itemList.add(restaurantProcessing.getById(Integer.parseInt(menuTable.getValueAt(row, 0).toString())));
                        }

                        restaurantProcessing.createMenuItem(new CompositeProduct(getNameTf(), getStock(), itemList));
                    } catch (IllegalArgumentException ex) {
                        view.printError(ex.getMessage());
                        return;
                    }
                }
            } else {
                try {
                    restaurantProcessing.createMenuItem(new BaseProduct(getNameTf(), getStock(), getPrice(), getQuantity()));
                } catch (NumberFormatException ex) {
                    view.printError(ex.getMessage());
                    return;
                }
            }

            view.updateMenuTable(restaurantProcessing.getMenu());
        }
    }

    /**
     * Implements the functionality of the app when the delete button in pressed
     *
     * @author Socaci Radu Andrei
     */
    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows = menuTable.getSelectedRows();

            if (selectedRows.length == 0) {
                view.printError("Select some products to be deleted!");
            } else {
                try {
                    for (int row : selectedRows) {
                        restaurantProcessing.deleteMenuItem(Integer.parseInt(menuTable.getValueAt(row, 0).toString()));
                    }
                } catch (IllegalArgumentException ex) {
                    view.printError(ex.getMessage());
                }
            }

            view.updateMenuTable(restaurantProcessing.getMenu());
        }
    }

    /**
     * Implements the functionality of the app when the update button in pressed
     *
     * @author Socaci Radu Andrei
     */
    private class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows = menuTable.getSelectedRows();

            if (selectedRows.length == 0) {
                view.printError("Select product to be updated");
                return;
            }

            try {
                if (composite.isSelected()) {
                    CompositeProduct toUpdate = (CompositeProduct) restaurantProcessing.getById(Integer.parseInt(menuTable.getValueAt(selectedRows[0], 0).toString()));
                    CompositeProduct updated = new CompositeProduct(getNameTf(), getStock(), toUpdate.getMenuItems());
                    updated.setId(toUpdate.getId());

                    restaurantProcessing.updateMenuItem(updated);
                } else {
                    BaseProduct toUpdate = (BaseProduct) restaurantProcessing.getById(Integer.parseInt(menuTable.getValueAt(selectedRows[0], 0).toString()));
                    BaseProduct updated = new BaseProduct(getNameTf(), getStock(), getPrice(), getQuantity());
                    updated.setId(toUpdate.getId());

                    restaurantProcessing.updateMenuItem(updated);
                }
            } catch (IllegalArgumentException ex) {
                view.printError(ex.getMessage());
            }

            view.updateMenuTable(restaurantProcessing.getMenu());
        }
    }
}
