package Business_Layer;

import java.util.List;

public interface IRestaurantProcessing {
    void createMenuItem(MenuItem menuItem);

    void deleteMenuItem(int id) throws IllegalArgumentException;

    void updateMenuItem(MenuItem menuItem) throws IllegalArgumentException;

    void createOrder(Order order, List<MenuItem> items) throws IllegalArgumentException;

    double calculatePrice(int orderId) throws IllegalArgumentException;

    void generateBill(int orderId) throws IllegalArgumentException;

    MenuItem getById(int id) throws IllegalArgumentException;

    List<MenuItem> getMenu();

    List<Order> getOrders();

    List<MenuItem> getItemsForOrder(Order order);
}
