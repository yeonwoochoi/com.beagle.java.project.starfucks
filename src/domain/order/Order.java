package domain.order;


import java.util.Queue;

/**
 * class that stores order contents in queue.
 */
public class Order {
    private int order_number;
    private int barista_index;
    private Queue order_content;
    private int total_time;

    public Order (int order_number, int barista_index, Queue order_content) {
        this.order_number = order_number;
        this.order_content = order_content;
        this.barista_index = barista_index;
    }

    public int getOrder_number() {
        return this.order_number;
    }

    public int getBarista_index() {
        return this.barista_index;
    }

    public Queue getOrder_content() {
        return this.order_content;
    }


    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public void setOrder_content(Queue order_content) {
        this.order_content = order_content;
    }

    public void setBarista_index(int barista_index) {
        this.barista_index = barista_index;
    }
}
