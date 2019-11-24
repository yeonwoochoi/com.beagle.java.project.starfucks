package domain.beverage;


/**
 * Classes with common constructors for both the Beverage and Dessert classes
 */
public abstract class Food {
    private int index;
    private String name;
    private int price;
    private int time;
    private String type;

    public Food (int index, String name, int price, int time) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.time = time;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }


}
