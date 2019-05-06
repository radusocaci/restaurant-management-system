import Presentation_Layer.View;

public class Main {
    public static void main(String[] args) {
//        Restaurant restaurant = new Restaurant();
//
//        ArrayList<MenuItem> baseProducts = new ArrayList<>();
//
//        baseProducts.add(new BaseProduct("salam", 10, 12.5, 100));
//        baseProducts.add(new BaseProduct("branza", 10, 10, 100));
//        baseProducts.add(new BaseProduct("paine", 10, 5.75, 100));
//
//        restaurant.createMenuItem(baseProducts.get(0));
//        restaurant.createMenuItem(baseProducts.get(1));
//        restaurant.createMenuItem(baseProducts.get(2));
//
//        MenuItem sandwich = new CompositeProduct("sandwich", 10, baseProducts);
//
//        restaurant.createMenuItem(sandwich);
//
//        restaurant.createOrder(new Order(1), baseProducts);

        View view = new View("Restaurant Application");

        // cooking table not working !!!

        view.setVisible(true);
//
//        restaurant.createOrder(new Order(2), Arrays.asList(sandwich));
    }
}
