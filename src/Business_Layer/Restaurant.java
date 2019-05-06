package Business_Layer;

import Data_Layer.FileWriter;

import java.io.Serializable;
import java.util.*;

public class Restaurant extends Observable implements Serializable, IRestaurantProcessing {
    private static final long serialversionUID = 129348938L;
    private Map<Order, List<MenuItem>> orders;
    private List<MenuItem> menu;

    public Restaurant() {
        this.menu = new ArrayList<>();
        this.orders = new HashMap<>();
    }

    @Override
    public void createMenuItem(MenuItem menuItem) {
        menu.add(menuItem);
    }

    @Override
    public void deleteMenuItem(int id) throws IllegalArgumentException {
        MenuItem toRemove = menu.stream().filter(menuItem -> menuItem.getId() == id).findAny().orElse(null);

        if (toRemove != null) {
            menu.remove(toRemove);
        } else {
            throw new IllegalArgumentException("[DELETE] Menu item with id " + id + " was not found in the menu!");
        }
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) throws IllegalArgumentException {
        MenuItem toUpdate = menu.stream().filter(menuItm -> menuItm.getId() == menuItem.getId()).findAny().orElse(null);

        if (toUpdate != null) {
            toUpdate.update(menuItem);
        } else {
            throw new IllegalArgumentException("[UPDATE] Menu item with id " + menuItem.getId() + "was not found in the menu!");
        }
    }

    @Override
    public void createOrder(Order order, List<MenuItem> items) throws IllegalArgumentException {
        items.forEach(item -> {
            if (item.getInStock() == 0) {
                throw new IllegalArgumentException("Out of stock!");
            }

            item.setInStock(item.getInStock() - 1);
        });

        if (orders.containsKey(order)) {
            orders.get(order).addAll(items);
        } else {
            orders.put(order, items);
        }

        items.stream().filter(CompositeProduct.class::isInstance).forEach(product -> {
            this.setChanged();
            this.notifyObservers(product);
        });
    }

    @Override
    public double calculatePrice(int orderId) throws IllegalArgumentException {
        Order order = orders.keySet().stream().filter(orderLocal -> orderLocal.getId() == orderId).findAny().orElse(null);

        if (order != null) {
            return orders.get(order).stream().mapToDouble(MenuItem::getPrice).sum();
        } else {
            throw new IllegalArgumentException("[PRICE_GEN] Order with id" + orderId + " was not found!");
        }
    }

    @Override
    public void generateBill(int orderId) throws IllegalArgumentException {
        Order order = orders.keySet().stream().filter(orderLocal -> orderLocal.getId() == orderId).findAny().orElse(null);

        if (order != null) {
            StringBuilder stringBuilder = new StringBuilder("Order details: \n\n" + order.toString() + "\n");

            stringBuilder.append("Products ordered: \n\n");
            orders.get(order).forEach(product -> stringBuilder.append(product.toString()));

            stringBuilder.append("\nTotal payment: \n\n").append(calculatePrice(orderId));

            FileWriter fileWriter = FileWriter.getInstance();
            fileWriter.generateBill(order, stringBuilder.toString());
        } else {
            throw new IllegalArgumentException("[BILL_GEN] Order with id" + orderId + " was not found!");
        }
    }

    @Override
    public MenuItem getById(int id) throws IllegalArgumentException {
        MenuItem toFind = menu.stream().filter(item -> item.getId() == id).findAny().orElse(null);

        if (toFind == null) {
            throw new IllegalArgumentException("Could not find menu item with id " + id + "!");
        }

        return toFind;
    }

    @Override
    public List<MenuItem> getMenu() {
        return menu;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders.keySet());
    }
}
