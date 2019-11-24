package service;

import domain.order.Order;
import utils.TextFileUtils;


/**
 * Class with methods to handle when a customer leaves the cafe
 */
public class LeavingCustomer {

    /**
     * Method to remove corresponding data from CustomerRepository.txt when the customer leaves the cafe
     * @param order
     * @return (boolean) success
     */
    public boolean RemoveCustomer(Order order) {
        TextFileUtils textFileUtils = new TextFileUtils();
        String old_str = order.getOrder_number() + "/" + "X;";
        String new_str = "";
        boolean success = textFileUtils.updateFile(old_str, new_str, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\CustomerRepository.txt");

        return success;
    }
}
