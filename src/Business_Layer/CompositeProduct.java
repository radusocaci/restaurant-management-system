package Business_Layer;

import java.util.List;

public class CompositeProduct extends MenuItem {
    private List<MenuItem> menuItems;

    public CompositeProduct(String name, int inStock, List<MenuItem> menuItems) {
        super(name, inStock);
        this.menuItems = menuItems;
    }

    @Override
    public double getPrice() {
        return menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    @Override
    public int getGrams() {
        return menuItems.stream().mapToInt(MenuItem::getGrams).sum();
    }

    @Override
    public void update(MenuItem menuItem) {
        super.update(menuItem);

        menuItems = ((CompositeProduct) menuItem).getMenuItems();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
