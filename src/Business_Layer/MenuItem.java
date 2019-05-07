package Business_Layer;

import java.io.Serializable;
import java.util.Objects;
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

    public abstract int getGrams();

    public void update(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.inStock = menuItem.getInStock();
    }

    public String getName() {
        return name;
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
