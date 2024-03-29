package utils;

/**
 * A class with a method to calculate how long it takes to prepare an ordered menu.
 */
public class CalculateUtils {

    /**
     * Method to calculate total time to prepare ordered menus.
     * @param order_name
     * @param order_count
     * @return (int) total time taken
     */
    public int TotalTime(String[] order_name, int[] order_count) {

        // variable declaration
        TextFileUtils textFileUtils = new TextFileUtils();
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();
        int total_time = 0;
        int each_count;
        int each_total_time;

        // Get time info for each food from FoodRepository.txt and merge all together
        for (int i = 0; i < order_name.length; i++) {
            each_count = order_count[i];

            String findStr = textFileUtils.findData(order_name[i], "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt");
            Object[] findArr = findStr.split("/");

            int each_time = typeChangeUtils.StringToInt((String) findArr[3]);

            each_total_time = each_time * each_count;

            total_time += each_total_time;

            each_count = 0;
            each_time = 0;
        }

        return total_time;
    }
}
