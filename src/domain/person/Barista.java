package domain.person;

/**
 * Create this class based on the data in BaristaRepository.txt
 */
public class Barista {
    private int barista_index;
    private int order_count;
    private boolean working;

    public Barista(int barista_index, int order_count, boolean working) {
        this.barista_index = barista_index;
        this.order_count = order_count;
        this.working = working;
    }

    public int getBarista_index() {
        return this.barista_index;
    }

    public int getOrder_count() {
        return this.order_count;
    }

    public boolean isWorking() {
        return this.working;
    }

    public void setBarista_index(int barista_index) {
        this.barista_index = barista_index;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
