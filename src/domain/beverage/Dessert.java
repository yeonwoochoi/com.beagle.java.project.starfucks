package domain.beverage;

/**
 * Dessert class that inherits food class and adds type, "Dessert"
 */
public class Dessert extends Food {
    public Dessert(int index, String name, int price, int time) {
        super(index, name, price, time);
        super.setType("Dessert");
    }

    @Override
    public String toString() {

        // 나중에 정의하자
        return super.toString();
    }
}
