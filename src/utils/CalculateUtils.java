package utils;

public class CalculateUtils {
    public int TotalTime(String[] order_name, int[] order_count) {

        TextFileUtils textFileUtils = new TextFileUtils();
        TypeChange typeChange = new TypeChange();
        int total_time = 0;
        int each_count;
        int each_total_time;

        for (int i = 0; i < order_name.length; i++) {
            each_count = order_count[i];

            String findStr = textFileUtils.findData(order_name[i], "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt");
            Object[] findArr = findStr.split("/");

            int each_time = typeChange.StringToInt((String) findArr[3]);

            each_total_time = each_time * each_count;

            total_time += each_total_time;

            each_count = 0;
            each_time = 0;
        }

        return total_time;
    }
}
