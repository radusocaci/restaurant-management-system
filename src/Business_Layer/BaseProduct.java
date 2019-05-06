package Business_Layer;

public class BaseProduct extends MenuItem {
    private double price;
    private int grams;

    public BaseProduct(String name, int inStock, double price, int grams) {
        super(name, inStock);

        this.price = price;
        this.grams = grams;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getGrams() {
        return grams;
    }

    @Override
    public void setGrams(int grams) {
        this.grams = grams;
    }

    @Override
    public void update(MenuItem menuItem) {
        super.update(menuItem);

        this.price = menuItem.getPrice();
        this.grams = menuItem.getGrams();
    }
}
