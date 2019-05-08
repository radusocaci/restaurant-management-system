package Business_Layer;

/**
 * @author Socaci Radu Andrei
 */
public class BaseProduct extends MenuItem {
    private double price;
    private int grams;

    /**
     * Creates a new BaseProduct instance
     *
     * @param name    name
     * @param inStock stock number
     * @param price   price
     * @param grams   weight
     */
    public BaseProduct(String name, int inStock, double price, int grams) {
        super(name, inStock);

        this.price = price;
        this.grams = grams;
    }

    /**
     * returns the price of the product
     *
     * @return price
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * returns the weight of the product
     *
     * @return weight
     */
    @Override
    public int getGrams() {
        return grams;
    }

    /**
     * updates the attributes of this product
     *
     * @param menuItem new product
     */
    @Override
    public void update(MenuItem menuItem) {
        super.update(menuItem);

        this.price = menuItem.getPrice();
        this.grams = menuItem.getGrams();
    }
}
