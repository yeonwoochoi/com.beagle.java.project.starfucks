package domain.person;

/**
 * Class created with order class and automatically deleted when order class is processed and order class is deleted
 */
public class Customer {
    private int order_number;
    private boolean customer_holdingBell;


    public Customer (int order_index, boolean customer_holdingBell) {
        this.order_number = order_index;
        this.customer_holdingBell = customer_holdingBell;;
    }

    public int getOrder_number() {
        return this.order_number;
    }

    public boolean isCustomer_holdingBell() {
        return this.customer_holdingBell;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public void setCustomer_holdingBell(boolean customer_holdingBell) {
        this.customer_holdingBell = customer_holdingBell;
    }
}
