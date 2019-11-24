package service;

import controller.BaristaController;
import domain.order.Order;
import domain.order.Receipt;
import utils.CalculateUtils;
import utils.ConvertUtils;
import utils.TextFileUtils;
import utils.TypeChangeUtils;

import java.io.File;
import java.util.ArrayList;




/**
 * A class with methods that handle the required processes in the ordering process
 */
public class OrderProcess {

    /**
     * Method that creates data of Order type and saves data in OrderRepository.txt when order is received
     * @param order_name
     * @param order_price
     * @param order_count
     * @return (Order) Order type of received order data
     */
    public Order createOrder(String[] order_name, int[] order_price, int[] order_count) {
        ArrayList arrayList = new ArrayList();
        CalculateUtils calculateUtils = new CalculateUtils();
        TextFileUtils textFileUtils = new TextFileUtils();
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();
        ConvertUtils convertUtils = new ConvertUtils();

        String each_name;
        String each_price;
        String each_count;

        // get consumed total_time
        int total_time = calculateUtils.TotalTime(order_name, order_count);

        // create ArrayList order_content
        for (int i = 0; i < order_name.length; i++) {
            each_name = order_name[i];
            each_price = typeChangeUtils.intToString(order_price[i]);
            each_count = typeChangeUtils.intToString(order_count[i]);

            String[] object = {each_name, each_price, each_count};


            arrayList.add(object);
            arrayList.trimToSize();
        }

        // get order_number
        String order_number_str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\OrderNumberRepository.txt"));
        int order_number = typeChangeUtils.StringToInt(order_number_str);

        // get barista_index
        BaristaController baristaController = new BaristaController();
        int barista_number = baristaController.GivingBaristaNumber();


        // Final creation of order of order type
        Order order = new Order(order_number, barista_number, arrayList);
        order.setTotal_time(total_time);

        // Store received order in OrderRepository.txt
        String input_str = order.getOrder_number() + "/" + order.getTotal_time() + ";";
        textFileUtils.saveToFile(input_str, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\OrderRepository.txt");

        // Giving customer a vibrating bell
        String input_str2 = order.getOrder_number() + "/" + "O;";
        textFileUtils.saveToFile(input_str2, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\CustomerRepository.txt");

        int new_order_num = order.getOrder_number() + 1;
        String old_order_num_str = typeChangeUtils.intToString(order.getOrder_number());
        String new_order_num_str = typeChangeUtils.intToString(new_order_num);

        textFileUtils.updateFile(old_order_num_str, new_order_num_str, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\OrderNumberRepository.txt");

        return order;
    }



    /**
     * Runs when a received order is processed, retrieves the vibration bell of the customer data stored in the customerRepository, and removes the data stored in the orderRepository
     * @param order
     * @return (boolean) success
     */
    public boolean DeleteOrder(Order order) {

        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();

        // Remove processed order from OrderRepository
        String order_number = typeChangeUtils.intToString(order.getOrder_number());
        String old_str1 = order_number + "/" + order.getTotal_time() + ";";
        String new_str1 = "";
        TextFileUtils textFileUtils = new TextFileUtils();
        boolean success1 = textFileUtils.updateFile(old_str1, new_str1, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\OrderRepository.txt");

        // withdraw Vibration bell of customer who received food
        String old_str2 = order_number + "/" + "O;";
        String new_str2 = order_number + "/" + "X;";
        boolean success2 = textFileUtils.updateFile(old_str2, new_str2, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\CustomerRepository.txt");



        // update Barista order count
        String str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt"));
        String[] str_arr = str.split(";");
        String[] each_arr;
        String[] each_arr2;
        String each_str = "";
        String index_str = "";
        String count_str = "";
        String is_working_str = "";

        for (int i = 0; i < str_arr.length; i++) {
            each_arr = str_arr[i].split("/");
            if (each_arr[0].equals(typeChangeUtils.intToString(order.getBarista_index()))) {
                each_str = str_arr[i];
            }
        }
        each_arr2 = each_str.split("/");
        index_str = each_arr2[0];
        count_str = each_arr2[1];
        is_working_str = each_arr2[2];


        int index = typeChangeUtils.StringToInt(index_str);
        int count = typeChangeUtils.StringToInt(count_str);

        String input_index = "";
        String input_count = "";
        String input_is_working = "";
        if (count > 1) {
            input_index = typeChangeUtils.intToString(index);
            input_count = typeChangeUtils.intToString(count-1);
            input_is_working = "1";
        } else if (count == 1) {
            input_index = typeChangeUtils.intToString(index);
            input_count = typeChangeUtils.intToString(count-1);
            input_is_working = "0";
        }


        String old_str3 = index_str + "/" + count_str + "/" + is_working_str + ";";
        String new_str3 = input_index + "/" + input_count + "/" + input_is_working + ";";
        boolean success3 = textFileUtils.updateFile(old_str3, new_str3, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt");

        // Success on data update
        boolean success;
        if (success1 && success2 && success3) {
            success = true;
        } else {
            success = false;
        }

        return success;
    }


    /**
     * Method to make receipt of input order
     * @param order
     * @return (String) Converting the receipt to a string using the toString method of the Receipt class
     */
    public String MakeReceipt(Order order) {

        // Load data stored in Order object and declare it as variable
        ConvertUtils convertUtils = new ConvertUtils();
        int order_number = order.getOrder_number();
        ArrayList arrayList = order.getOrder_content();
        arrayList.trimToSize();

        // Declare String array to store ordered food name and price
        String[] name_arr = new String[arrayList.size()];
        String[] price_arr = new String[arrayList.size()];
        String[] count_arr = new String[arrayList.size()];

        // Save the ordered food name and price
        for (int i = 0; i < arrayList.size(); i++) {
            String[] each_str_arr = (String[]) arrayList.get(i);
            name_arr[i] = each_str_arr[0];
        }

        for (int i = 0; i < arrayList.size(); i++) {
            String[] each_str_arr = (String[]) arrayList.get(i);
            price_arr[i] = each_str_arr[1];
        }

        for (int i = 0; i < arrayList.size(); i++) {
            String[] each_str_arr = (String[]) arrayList.get(i);
            count_arr[i] = each_str_arr[2];
        }

        int[] new_price_arr = convertUtils.StringArrayToIntArray(price_arr);
        int[] new_count_arr = convertUtils.StringArrayToIntArray(count_arr);


        // Save order data in Receipt class and call toString method
        Receipt receipt = new Receipt(order_number, name_arr, new_price_arr, new_count_arr);

        return receipt.toString();
    }
}
