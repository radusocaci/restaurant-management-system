package Business_Layer;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Socaci Radu Andrei
 */
public abstract class MenuItem implements Serializable {
    private static final AtomicInteger igGenerator = new AtomicInteger(1);

    private static final long serialversionUID = 63412134L;

    private int id;
    private String name;
    private int inStock;

    /**
     * Creates a new MenuItem instance
     *
     * @param name    name
     * @param inStock stock number
     */
    public MenuItem(String name, int inStock) {
        this.id = igGenerator.getAndIncrement();

        this.name = name;
        this.inStock = inStock;
    }

    /**
     * returns the price of the product
     *
     * @return price
     */
    public abstract double getPrice();

    /**
     * returns the weight of the product
     *
     * @return weight
     */
    public abstract int getGrams();

    /**
     * updates the attributes of this product
     *
     * @param menuItem new product
     */
    public void update(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.inStock = menuItem.getInStock();
    }

    /**
     * returns the name of the products
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the stock of the product
     *
     * @return stock
     */
    public int getInStock() {
        return inStock;
    }

    /**
     * sets the stock of the product
     *
     * @param inStock new stock
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    /**
     * return the id of the product
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of the product
     *
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + "\t" + getPrice() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return id == menuItem.id &&
                inStock == menuItem.inStock &&
                Objects.equals(name, menuItem.name);
    }
}
