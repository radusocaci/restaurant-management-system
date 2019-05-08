package Data_Layer;

import Business_Layer.Restaurant;

import java.io.*;

/**
 * @author Socaci Radu Andrei
 */
public class RestaurantSerializator {
    /**
     * saves a restaurant instance in a file (serialization)
     *
     * @param restaurant restaurant
     */
    public static void save(Restaurant restaurant) {
        try (FileOutputStream outputStream = new FileOutputStream("restaurant.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(restaurant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads a restaurant from a file (deserialization)
     *
     * @return restaurant instance
     */
    public static Restaurant load() {
        try (FileInputStream inputStream = new FileInputStream("restaurant.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (Restaurant) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
