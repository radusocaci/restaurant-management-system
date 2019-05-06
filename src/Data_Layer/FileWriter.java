package Data_Layer;

import Business_Layer.Order;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileWriter {
    private FileWriter() {
        File directory = new File("bills");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File[] filesInFolder = directory.listFiles();
        if (filesInFolder != null) {
            Arrays.stream(filesInFolder).forEach(File::delete);
        }
    }

    public static FileWriter getInstance() {
        return SingletonHelper.FILE_WRITER;
    }

    public void generateBill(Order order, String billData) {
        File billFile = new File("bills/billOrder" + order.getId() + ".txt");

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(billFile, false)) {
            fileWriter.write(billData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SingletonHelper {
        private static final FileWriter FILE_WRITER = new FileWriter();
    }
}
