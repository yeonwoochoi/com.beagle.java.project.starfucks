package domain.beverage;

/**
 * Beverage class that inherits food class and adds type, "Beverage"
 */
public class Beverage extends Food {
    public Beverage(int index, String name, int price, int time) {
        super(index, name, price, time);
        super.setType("Beverage");
    }

    @Override
    public String toString() {

        // 나중에 정의하자
        return super.toString();
    }
}
