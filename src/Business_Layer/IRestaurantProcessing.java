package Business_Layer;

import java.util.List;

/**
 * @author Socaci Radu Andrei
 */
public interface IRestaurantProcessing {
    boolean isWellFormed();

    /**
     * @param menuItem menu item to add
     * @invariant isWellFormed()
     * @pre menuItem != null && menu != null
     * @post getSize() = getSize()@pre + 1
     * @post @forall k : [0 .. getSize() - 1] @
     * (k < getSize() - 1 ==> getElement(k)@pre == getElement(k)) &&
     * (k == getSize() - 1 ==> getElement(k)@pre == menuItem)
     */
    void createMenuItem(MenuItem menuItem);

    /**
     * @param id id
     * @throws IllegalArgumentException id outside range
     * @invariant isWellFormed()
     * @pre id > 0 && getSize() > 0 && menu != null && (@exists y: y.id == id && menu.contains(y))
     * @post getSize = getSize()@pre - 1
     * @post (getElement ( i).getId() == id) ==> @forall k : [0 .. getSize() - 1] @
     * (k < i ==> getElement(k)@pre == getElement(k)) &&
     * (k > i ==> getElement(k + 1)@pre == getElement(k))
     */
    void deleteMenuItem(int id) throws IllegalArgumentException;

    /**
     * @param menuItem menu item to add
     * @invariant isWellFormed()
     * @pre menuItem != null && menu != null && (@exists y: y.id == menuItem.id && menu.contains(y))
     * @post (getElement ( i).getId() == menuItem.getId()) ==> @forall k : [0 .. getSize() - 1] @
     * (k != i ==> getElement(k)@pre == getElement(k)) &&
     * (k == i ==> getElement(k)@pre == menuItem)
     */
    void updateMenuItem(MenuItem menuItem) throws IllegalArgumentException;

    /**
     * @param order order reference
     * @param items menu items for given order
     * @throws IllegalArgumentException menu item out of stock
     * @invariant isWellFormed()
     * @pre order != null && orders != null && items != null && items.getSize() > 0
     * @post getSize() = getSize()@pre + 1
     */
    void createOrder(Order order, List<MenuItem> items) throws IllegalArgumentException;

    /**
     * @param orderId order id to calculate price for
     * @return price as double
     * @throws IllegalArgumentException order with given id was not found
     * @invariant isWellFormed()
     * @pre orders != null && getSize() > 0 && (@exists y: y.id == orderId && orders.contains(y))
     * @post @nochange
     */
    double calculatePrice(int orderId) throws IllegalArgumentException;

    /**
     * @param orderId order id for which to generate bill
     * @throws IllegalArgumentException no such order
     * @invariant isWellFormed()
     * @pre orders != null && getSize() > 0 && (@exists y: y.id == orderId && orders.contains(y))
     * @post @nochange
     */
    void generateBill(int orderId) throws IllegalArgumentException;

    /**
     * @param id menuItem id
     * @return menu item
     * @throws IllegalArgumentException not found
     * @invariant isWellFormed()
     * @pre menu != null && getSize() > 0 && (@exists y: y.id == id && menu.contains(y))
     * @post @nochange
     */
    MenuItem getById(int id) throws IllegalArgumentException;

    /**
     * @return the menu of the restaurant
     * @invariant isWellFormed()
     * @post @nochange
     */
    List<MenuItem> getMenu();

    /**
     * @return the orders of the restaurant
     * @invariant isWellFormed()
     * @post @nochange
     */
    List<Order> getOrders();

    /**
     * @param order order
     * @return products associated with the given order
     * @invariant isWellFormed()
     * @pre orders != null && getSize() > 0 && (@exists y: y.id == order.id && orders.contains(y))
     * @post @nochange
     */
    List<MenuItem> getItemsForOrder(Order order);
}
