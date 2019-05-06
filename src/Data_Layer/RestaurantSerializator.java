package Data_Layer;

import Business_Layer.Restaurant;

import java.io.*;

public class RestaurantSerializator {
    public static void save(Restaurant restaurant) {
        try (FileOutputStream outputStream = new FileOutputStream("restaurant.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(restaurant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
