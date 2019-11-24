package controller;

import utils.ConvertUtils;
import utils.TextFileUtils;
import utils.TypeChangeUtils;

import java.io.File;

/**
 * Check if the barista is performing more than 10 orders. If you input specific Barista, this will return the number of orders.
 */
public class BaristaController {

    /**
     * A method that returns the barista number to execute the order received when creating a new Order.
     * @return (int) Barista number to fulfill the received order
     */
    public int GivingBaristaNumber() {

        // variable declaration
        TextFileUtils textFileUtils = new TextFileUtils();
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();
        String output;

        // Get all barista info as String
        String barista_number_str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt"));

        // Declared with the last declared barista String array
        String[] objects = barista_number_str.split(";");
        String last_str = objects[objects.length-1];
        String[] last_arr = last_str.split("/");

        int last_arr_count = typeChangeUtils.StringToInt(last_arr[1]);
        if (last_arr_count >= 10) {
            // When you're doing 10 things until the last barista

            output = createNewBarista();
        } else {
            // When there is a barista who can still afford

            output = updateBarista();
        }
        int output_int = typeChangeUtils.StringToInt(output);
        return output_int;
    }

    /**
     * A method that calls a new barista when all baristas are performing 10 orders.
     * @return (String) Barista index assigned new order
     */
    public String createNewBarista() {

        // variable declaration
        TextFileUtils textFileUtils = new TextFileUtils();
        ConvertUtils convertUtils = new ConvertUtils();
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();

        // get all barista info as string array
        String str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt"));
        String[] str_arr = str.split(";");

        // Get index of last barista
        String last_str = str_arr[str_arr.length - 1];
        String[] new_last_arr_str = last_str.split("/");
        int[] new_last_arr = convertUtils.StringArrayToIntArray(new_last_arr_str);

        // The index of the barista to be created
        int add_index_int = new_last_arr[0] + 1;
        String add_index_str = typeChangeUtils.intToString(add_index_int);


        // return the index of the barista to be created
        String input = add_index_str + "/1/1;";
        if (textFileUtils.saveToFile(input, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt")) {
            return typeChangeUtils.intToString(add_index_int);
        } else {
            return "";
        }
    }

    /**
     * assigns an order to that barista when there is a free barista
     * @return (String) Barista index assigned new order
     */
    public String updateBarista() {

        // variables declaration
        TextFileUtils textFileUtils = new TextFileUtils();
        ConvertUtils convertUtils = new ConvertUtils();
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();

        // get all barista info as string array
        String str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt"));
        String[] str_arr = str.split(";");

        // variables declaration
        String[] each_arr_str;
        int[] each_arr;

        int new_count = 0;
        int old_count = 0;
        String old_count_str = "";
        String new_count_str = "";

        String old_working_str = "";
        String new_working_str = "";

        String new_index_str = "";


        int i = 0;
        boolean run = true;
        while (i < str_arr.length && run) {

            // Declare each barista info as an array of strings
            each_arr_str = str_arr[i].split("/");
            each_arr = convertUtils.StringArrayToIntArray(each_arr_str);

            // Each barista has less than 10 orders
            if (each_arr[1] < 10 && each_arr[1] >= 0) {

                // Update the number of things doing
                old_count = each_arr[1];
                new_count = each_arr[1] + 1;
                old_count_str = typeChangeUtils.intToString(old_count);
                new_count_str = typeChangeUtils.intToString(new_count);


                // Changing the value of isWorking
                if (each_arr[2] == 0) {
                    old_working_str = "0";
                    new_working_str = "1";
                } else {
                    old_working_str = "1";
                    new_working_str = "1";
                }

                // terminate while statement to update
                run = false;

                // index of updated barista
                new_index_str = typeChangeUtils.intToString(each_arr[0]);
            } else {
                i++;
            }
        }

        // Create new data and old data
        String old_data = new_index_str + "/" + old_count_str + "/" + old_working_str + ";";
        String new_data = new_index_str + "/" + new_count_str + "/" + new_working_str + ";";


        // update data in BaristaRepository.txt
        textFileUtils.updateFile(old_data, new_data, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt");


        // return the index of the barista to be updated
        return new_index_str;
    }
}
