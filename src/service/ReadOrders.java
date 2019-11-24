package service;

import utils.TextFileUtils;
import java.io.File;


/**
 * Class with method showing list of orders not yet processed
 */
public class ReadOrders {

    /**
     * Method that loads data from OrderRepository.txt and displays it in String form.
     * @return String list of active orders
     */
    public String ReadOrderList() {
        TextFileUtils textFileUtils = new TextFileUtils();
        String order_str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\OrderRepository.txt"));
        String[] arr1 = order_str.split(";");
        String output = "";
        if (order_str.length() > 0) {
            for (int i = 0; i < arr1.length; i++) {
                String[] arr2 = arr1[i].split("/");
                output += arr2[0] + " " + arr2[1] + '\n';
            }
        }
        return output;
    }
}
