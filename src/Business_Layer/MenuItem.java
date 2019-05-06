package Business_Layer;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MenuItem implements Serializable {
    private static final AtomicInteger igGenerator = new AtomicInteger(1);

    private static final long serialversionUID = 63412134L;

    private int id;
    private String name;
    private int inStock;

    public MenuItem(String name, int inStock) {
        this.id = igGenerator.getAndIncrement();

        this.name = name;
        this.inStock = inStock;
    }

    public abstract double getPrice();

    public abstract void setPrice(double price);

    public abstract int getGrams();

    public abstract void setGrams(int grams);

    public void update(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.inStock = menuItem.getInStock();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + "\t" + getPrice() + "\n";
    }
}
