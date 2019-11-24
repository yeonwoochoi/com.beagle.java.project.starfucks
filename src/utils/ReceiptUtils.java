package utils;

import domain.order.Order;
import domain.order.Receipt;

import java.util.Queue;

public class ReceiptUtils {
    /**
     * Method to make receipt of input order
     * @param order
     * @return Converting the receipt to a string using the toString method of the Receipt class
     */
    public String MakeReceipt(Order order) {
        ConvertUtils convertUtils = new ConvertUtils();
        int order_number = order.getOrder_number();
        Queue queue = order.getOrder_content();

        Receipt receipt = new Receipt(order_number, convertUtils.queueToProductName(queue), convertUtils.queueToProductPrice(queue));

        return receipt.toString();
    }
}
