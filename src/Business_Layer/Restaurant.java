package Business_Layer;

import Data_Layer.FileWriter;

import java.io.Serializable;
import java.util.*;

/**
 * @author Socaci Radu Andrei
 * @invariant isWellFormed()
 */
public class Restaurant extends Observable implements Serializable, IRestaurantProcessing {
    private static final long serialversionUID = 129348938L;

    private Map<Order, List<MenuItem>> orders;
    private List<MenuItem> menu;

    public Restaurant() {
        this.menu = new ArrayList<>();
        this.orders = new HashMap<>();
    }

    @Override
    public boolean isWellFormed() {
        if (menu == null || orders == null) {
            return false;
        }

        int k = 0;

        for (MenuItem menuItem : menu) {
            if (!menuItem.equals(menu.get(k++))) {
                return false;
            }
        }

        if (k != menu.size()) {
            return false;
        }

        for (Map.Entry<Order, List<MenuItem>> order : orders.entrySet()) {
            if (order.getKey().getTable() < 0 || order.getValue().isEmpty() || !menu.containsAll(order.getValue())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void createMenuItem(MenuItem menuItem) {
        assert menuItem != null && menu != null && isWellFormed();

        int size = menu.size();

        menu.add(menuItem);

        assert size == menu.size() - 1;
    }

    @Override
    public void deleteMenuItem(int id) throws IllegalArgumentException {
        assert id > 0 && menu != null && menu.size() > 0 && isWellFormed();

        MenuItem toRemove = menu.stream().filter(menuItem -> menuItem.getId() == id).findAny().orElse(null);
        int size = menu.size();

        if (toRemove != null) {
            menu.remove(toRemove);
        } else {
            throw new IllegalArgumentException("[DELETE] Menu item with id " + id + " was not found in the menu!");
        }

        assert size == menu.size() + 1;
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) throws IllegalArgumentException {
        assert menuItem != null && menu != null && menu.size() > 0 && isWellFormed();

        MenuItem toUpdate = menu.stream().filter(menuItm -> menuItm.getId() == menuItem.getId()).findAny().orElse(null);

        if (toUpdate != null) {
            toUpdate.update(menuItem);
        } else {
            throw new IllegalArgumentException("[UPDATE] Menu item with id " + menuItem.getId() + "was not found in the menu!");
        }

        assert toUpdate.equals(menuItem);
    }

    @Override
    public void createOrder(Order order, List<MenuItem> items) throws IllegalArgumentException {
        assert order != null && orders != null && items != null && items.size() > 0 && isWellFormed();

        items.forEach(item -> {
            if (item.getInStock() == 0) {
                throw new IllegalArgumentException("Out of stock!");
            }

            item.setInStock(item.getInStock() - 1);
        });
        int size = orders.size();

        if (orders.containsKey(order)) {
            orders.get(order).addAll(items);
        } else {
            orders.put(order, items);
        }

        items.stream().filter(CompositeProduct.class::isInstance).forEach(product -> {
            this.setChanged();
            this.notifyObservers(product);
        });

        assert size == orders.size() - 1;
    }

    @Override
    public double calculatePrice(int orderId) throws IllegalArgumentException {
        assert orders != null && orders.size() > 0 && isWellFormed();

        Order order = orders.keySet().stream().filter(orderLocal -> orderLocal.getId() == orderId).findAny().orElse(null);

        if (order != null) {
            return orders.get(order).stream().mapToDouble(MenuItem::getPrice).sum();
        } else {
            throw new IllegalArgumentException("[PRICE_GEN] Order with id" + orderId + " was not found!");
        }
    }

    @Override
    public void generateBill(int table) throws IllegalArgumentException {
        assert orders != null && orders.size() > 0 && isWellFormed();

        Order order = orders.keySet().stream().filter(orderLocal -> orderLocal.getTable() == table).findAny().orElse(null);

        if (order != null) {
            StringBuilder stringBuilder = new StringBuilder("Order details: \n\n" + order.toString() + "\n");

            stringBuilder.append("Products ordered: \n\n");
            orders.get(order).forEach(product -> stringBuilder.append(product.toString()));

            stringBuilder.append("\nTotal payment: \n\n").append(calculatePrice(table));

            FileWriter fileWriter = FileWriter.getInstance();
            fileWriter.generateBill(order, stringBuilder.toString());

            orders.remove(order);
        } else {
            throw new IllegalArgumentException("[BILL_GEN] Order with id" + table + " was not found!");
        }
    }

    @Override
    public MenuItem getById(int id) throws IllegalArgumentException {
        assert menu != null && menu.size() > 0 && isWellFormed();

        MenuItem toFind = menu.stream().filter(item -> item.getId() == id).findAny().orElse(null);

        if (toFind == null) {
            throw new IllegalArgumentException("Could not find menu item with id " + id + "!");
        }

        return toFind;
    }

    @Override
    public List<MenuItem> getMenu() {
        assert isWellFormed();

        return menu;
    }

    public List<Order> getOrders() {
        assert isWellFormed();

        return new ArrayList<>(orders.keySet());
    }

    @Override
    public List<MenuItem> getItemsForOrder(Order order) {
        assert orders != null && orders.size() > 0 && isWellFormed();

        return orders.get(order);
    }
}
