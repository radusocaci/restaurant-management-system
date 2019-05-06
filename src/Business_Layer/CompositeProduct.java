package Business_Layer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {
    private List<MenuItem> menuItems;

    public CompositeProduct(String name, int inStock, List<MenuItem> menuItems) {
        super(name, inStock);
        this.menuItems = menuItems;
    }

    public CompositeProduct(String name, int inStock) {
        super(name, inStock);

        menuItems = new ArrayList<>();
    }

    @Override
    public double getPrice() {
        return menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    @Override
    public void setPrice(double price) {
    }

    @Override
    public int getGrams() {
        return menuItems.stream().mapToInt(MenuItem::getGrams).sum();
    }

    @Override
    public void setGrams(int grams) {
    }

    @Override
    public void update(MenuItem menuItem) {
        super.update(menuItem);

        menuItems = ((CompositeProduct) menuItem).getMenuItems();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
