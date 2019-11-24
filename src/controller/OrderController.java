package controller;

import utils.TypeChangeUtils;

/**
 * Check whether the ordered menu is on the menu.
 */
public class OrderController {

    /**
     * Method to check if input quantity is natural number greater than 0
     * @param input_quantity
     * @return (boolean) success
     */
    public boolean AdequacyOfQuantity(String input_quantity) {
        boolean adequacy;
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();
        int input_quantity_int = typeChangeUtils.StringToInt(input_quantity);

        if (input_quantity_int > 0) {
            adequacy = true;
        } else {
            adequacy = false;
        }

        return adequacy;
    }
}
