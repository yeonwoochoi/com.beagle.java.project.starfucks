package utils;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

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
     * Returns a string array created by extracting only the ordered food names from the objects stored in the queue.
     * @param queue
     * @return string array to store food names
     */
    public String[] queueToProductName (Queue queue) {
        Object object;
        String[] strArr = new String[queue.size()];

        for (int i = 0; i < queue.size(); i++) {
            object = ((LinkedList) queue).get(i);
            strArr[i] = ((String[]) object)[0];
        }

        return strArr;
    }

    /**
     * A method that returns an int array created by extracting only the ordered food prices among the objects stored in the queue.
     * @param queue
     * @return int array to store price
     */
    public int[] queueToProductPrice (Queue queue) {
        Object object;
        int[] intArr = new int[queue.size()];

        for (int i = 0; i < queue.size(); i++) {
            object = ((LinkedList) queue).get(i);
            intArr[i] = ((int[]) object)[1];
        }

        return intArr;
    }


    /**
     * Convert the data stored in the text file to a String value using the readFile method. This method converts String data into queue type when inputted to this method.
     * @param file_name
     * @return Queue type of data stored in text file
     */
    public Queue fileTextToQueue(String file_name) {

        String str = null;
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
}
