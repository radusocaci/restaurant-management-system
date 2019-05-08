package Business_Layer;

import java.util.List;

/**
 * @author Socaci Radu Andrei
 */
public interface IRestaurantProcessing {
    boolean isWellFormed();

    /**
     * @param menuItem menu item to add
     * @pre menuItem != null && menu != null && isWellFormed()
     * @post getSize() = getSize()@pre + 1
     * @post @forall k : [0 .. getSize() - 1] @
     * (k < getSize() - 1 ==> getElement(k)@pre == getElement(k)) &&
     * (k == getSize() - 1 ==> getElement(k)@pre == menuItem)
     */
    void createMenuItem(MenuItem menuItem);

    /**
     * @param id id
     * @throws IllegalArgumentException id outside range
     * @pre id > 0 && getSize() > 0 && menu != null && (@exists y: y.id == id && menu.contains(y)) && isWellFormed()
     * @post getSize = getSize()@pre - 1
     * @post (getElement ( i).getId() == id) ==> @forall k : [0 .. getSize() - 1] @
     * (k < i ==> getElement(k)@pre == getElement(k)) &&
     * (k > i ==> getElement(k + 1)@pre == getElement(k))
     */
    void deleteMenuItem(int id) throws IllegalArgumentException;

    /**
     * @pre menuItem != null && menu != null && (@exists y: y.id == menuItem.id && menu.contains(y)) && isWellFormed()
     * @post (getElement ( i).getId() == menuItem.getId()) ==> @forall k : [0 .. getSize() - 1] @
     * (k != i ==> getElement(k)@pre == getElement(k)) &&
     * (k == i ==> getElement(k)@pre == menuItem)
     *
     * @param menuItem menu item to add
     */
    void updateMenuItem(MenuItem menuItem) throws IllegalArgumentException;

    /**
     * @pre order != null && orders != null && items != null && items.getSize() > 0 && isWellFormed()
     * @post getSize() = getSize()@pre + 1
     *
     * @param order order reference
     * @param items menu items for given order
     * @throws IllegalArgumentException menu item out of stock
     */
    void createOrder(Order order, List<MenuItem> items) throws IllegalArgumentException;

    double calculatePrice(int orderId) throws IllegalArgumentException;

    void generateBill(int orderId) throws IllegalArgumentException;

    MenuItem getById(int id) throws IllegalArgumentException;

    List<MenuItem> getMenu();

    List<Order> getOrders();

    List<MenuItem> getItemsForOrder(Order order);
}
