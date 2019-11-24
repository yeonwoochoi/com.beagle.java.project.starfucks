package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A class that has a method that converts data type while processing data loaded from text file saved in repository package.
 */
public class ConvertUtils {

    /**
     * Method to convert the object of the queue corresponding to the input index into a String
     * @param queue
     * @param index
     * @return a String converting the object corresponding to the input index into a string, "one line"
     */
    public String queueToString(Queue queue, int index, String seperator) {
        String str = "";
        Object[] object;


        object = (Object[]) ((LinkedList) queue).get(index);
        String[] strArr = (String[]) object;
        int length = strArr.length;
        for (int j = 0; j < length; j++) {
            str += strArr[j] + seperator;
        }
        str += "\n";

        return str;
    }

    /**
     * Returns a string array created by extracting only the ordered food names from the objects stored in FoodRepository.txt.
     * @param
     * @return string array to store food names
     */
    public String[] ReadProductName() {
        TextFileUtils textFileUtils = new TextFileUtils();


        String food_str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt"));
        String[] food_arr = food_str.split(";");

        String[] each_food_arr;
        String[] each_food_name_arr = new String[food_arr.length];


        for (int i = 0; i < food_arr.length; i++) {
            each_food_arr = food_arr[i].split("/");
            each_food_name_arr[i] = each_food_arr[1];
        }

        return each_food_name_arr;
    }

    /**
     * A method that returns an int array created by extracting only the ordered food prices among the objects stored in the queue.
     * @param
     * @return int array to store price
     */
    public int[] ReadProductPrice() {
        TextFileUtils textFileUtils = new TextFileUtils();


        String food_str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt"));
        String[] food_arr = food_str.split(";");

        String[] each_food_arr;
        String[] each_food_price_arr = new String[food_arr.length];


        for (int i = 0; i < food_arr.length; i++) {
            each_food_arr = food_arr[i].split("/");
            each_food_price_arr[i] = each_food_arr[2];
        }

        int[] output = StringArrayToIntArray(each_food_price_arr);

        return output;
    }


    /**
     * Convert the data stored in the text file to a String value using the readFile method. This method converts String data into queue type when inputted to this method.
     * @param file_name
     * @return Queue type of data stored in text file
     */
    public Queue fileTextToQueue(String file_name) {

        String str = "";
        Queue queue = new LinkedList();
        String[] strings;
        TextFileUtils textFileUtils = new TextFileUtils();

        if (file_name == "FoodRepository") {
            str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt"));
        }

        if (file_name == "OrderRepository") {
            str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\OrderRepository.txt"));
        }

        if (file_name == "BaristaRepository") {
            str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\BaristaRepository.txt"));
        }



        strings = str.split(";");
        Object strArr;
        for (int i = 0; i < strings.length; i++) {
            strArr = strings[i].split("/");
            boolean success = queue.offer(strArr);
            if (success) {
                strArr = null;
            } else {
                strArr = null;
                queue = null;
            }
        }

        return queue;
    }


    /**
     * Method to convert String array to int array
     * @param input
     * @return (int) array
     */
    public int[] StringArrayToIntArray (String[] input) {
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();
        int[] new_objects = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            new_objects[i] = typeChangeUtils.StringToInt(input[i]);
        }
        return new_objects;
    }


    /**
     * Method to convert int array to string array
     * @param input
     * @return (String) array
     */
    public String[] IntArrayToStringArray (int[] input) {
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();
        String[] new_objects = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            new_objects[i] = typeChangeUtils.intToString(i);
        }
        return new_objects;
    }

    /**
     * Method to delete only empty spaces of elements in string array
     * @param input_str_array
     * @return (String[]) String array with all whitespaces missing
     */
    public String[] ClearArray(String[] input_str_array) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < input_str_array.length; i++) {
            input_str_array[i] = input_str_array[i].replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
        }

        for(String s : input_str_array) {

            if(s != null && s.length() > 0) {
                list.add(s);
            }
        }

        return list.toArray(new String[list.size()]);
    }
}
