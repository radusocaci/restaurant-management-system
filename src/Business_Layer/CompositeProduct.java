package Business_Layer;

import java.util.List;

/**
 * @author Socaci Radu Andrei
 */
public class CompositeProduct extends MenuItem {
    private List<MenuItem> menuItems;

    /**
     * Creates a new CompositeProduct instance
     *
     * @param name      name
     * @param inStock   stock number
     * @param menuItems BaseProduct list
     */
    public CompositeProduct(String name, int inStock, List<MenuItem> menuItems) {
        super(name, inStock);
        this.menuItems = menuItems;
    }

    /**
     * returns the price of the product
     *
     * @return price
     */
    @Override
    public double getPrice() {
        return menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    /**
     * returns the weight of the product
     *
     * @return weight
     */
    @Override
    public int getGrams() {
        return menuItems.stream().mapToInt(MenuItem::getGrams).sum();
    }

    /**
     * updates the attributes of this product
     *
     * @param menuItem new product
     */
    @Override
    public void update(MenuItem menuItem) {
        super.update(menuItem);

        menuItems = ((CompositeProduct) menuItem).getMenuItems();
    }

    /**
     * Returns the list of BaseProducts that form the CompositeProduct
     *
     * @return BaseProduct list
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
