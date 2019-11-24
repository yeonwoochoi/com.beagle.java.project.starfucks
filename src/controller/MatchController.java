package controller;

import utils.ConvertUtils;
import utils.TextFileUtils;

/**
 * Check that the information in the repository matches the information entered.
 */
public class MatchController {

    /**
     * Method to check if input data is a value stored in Food Repository
     * @param order_name
     * @return (boolean) success
     */
    public boolean CheckOrderName(String order_name) {




        // Import data from FoodRepository.txt and create array by subtracting only data related to food name
        ConvertUtils convertUtils = new ConvertUtils();
        String[] product_name_arr = convertUtils.ReadProductName();



        // count the number of times input data and repository data match
        int count = 0;
        for (int i = 0; i < product_name_arr.length; i++) {
            if(product_name_arr[i].equals(order_name)) {
                count += 1;
            }
        }


        // Determine success with i, the number of times input data and repository data match.
        boolean success;
        if (count == 0) {
            success = false;
        } else if (count == 1){
            success = true;
        } else {
            success = false;
        }


        return success;

    }

    /**
     * Method to check whether a guest with input order_number exists in CheckRepository.txt
     * @param order_number
     * @return (String) Return "O" or "X" whether the corresponding guest is holding the vibrating bell
     */
    public String CheckCustomer (String order_number) {
        TextFileUtils textFileUtils = new TextFileUtils();
        String content = textFileUtils.findData(order_number, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\CustomerRepository.txt");
        String[] content_arr = content.split("/");
        return content_arr[0];
    }
}
