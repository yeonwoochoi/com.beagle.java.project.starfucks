package domain.order;

import java.util.Date;

/**
 * Receipt class created together when Order class is created
 */
public class Receipt {
    private int order_number;
    private String[] order_name_list;
    private int[] order_price_list;
    private int total;

    public Receipt(int order_number, String[] order_name_list, int[] order_price_list) {
        this.order_number = order_number;
        this.order_name_list = order_name_list;
        this.order_price_list = order_price_list;
    }

    public int getOrder_number() {
        return this.order_number;
    }


    public String[] getOrder_name_list() {
        return this.order_name_list;
    }

    public int[] getOrder_price_list() {
        return this.order_price_list;
    }

    public int getTotal() {
        return this.total;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public void setOrder_name_list(String[] order_name_list) {
        this.order_name_list = order_name_list;
    }

    public void setOrder_price_list(int[] order_price_list) {
        this.order_price_list = order_price_list;
    }


    public void setTotal() {
        int[] price_list = getOrder_price_list();
        int total = 0;
        for (int i = 0; i < price_list.length; i++) {
            total += price_list[i];
        }
        this.total = total;
    }

    @Override
    public String toString() {
        String str;
        String content = "";
        String[] name_list = getOrder_name_list();
        int[] price_list = getOrder_price_list();

        for (int i = 0; i < name_list.length; i++) {
            content += name_list[i] + "  " + price_list[i] + "\n";
        }

        str = "order number : " + getOrder_number() + "\n\n" + content + "\n" + "total :  " + getTotal();

        return str;
    }
}
