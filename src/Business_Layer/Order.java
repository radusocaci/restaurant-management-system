package Business_Layer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Socaci Radu Andrei
 */
public class Order implements Serializable {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    private static final long serialversionUID = 91238213L;

    private int id;
    private Date date;
    private int table;

    /**
     * Creates a new Order instance
     *
     * @param table table number
     */
    public Order(int table) {
        this.table = table;

        this.date = new Date();
        this.id = idGenerator.getAndIncrement();
    }

    /**
     * returns the table number
     *
     * @return table number
     */
    public int getTable() {
        return table;
    }

    /**
     * returns the id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                table == order.table &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, table);
    }

    @Override
    public String toString() {
        return "Order ID: " + id + "\n" +
                "Order Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) + "\n" +
                "Table: " + table + "\n";
    }
}
