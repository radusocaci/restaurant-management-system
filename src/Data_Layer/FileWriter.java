package Data_Layer;

import Business_Layer.Order;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Socaci Radu Andrei
 */
public class FileWriter {
    /**
     * private constructor for singleton
     */
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

    /**
     * returns a FileWriter instance
     *
     * @return FileWriter
     */
    public static FileWriter getInstance() {
        return SingletonHelper.FILE_WRITER;
    }

    /**
     * generates a bill for a given order
     *
     * @param order    order
     * @param billData string to print
     */
    public void generateBill(Order order, String billData) {
        File billFile = new File("bills/billOrder" + order.getId() + ".txt");

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(billFile, false)) {
            fileWriter.write(billData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper class for singleton
     *
     * @author Socaci Radu Andrei
     */
    private static class SingletonHelper {
        private static final FileWriter FILE_WRITER = new FileWriter();
    }
}
