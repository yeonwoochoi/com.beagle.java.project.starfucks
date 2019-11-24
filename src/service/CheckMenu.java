package service;

import utils.ConvertUtils;
import utils.TextFileUtils;

import java.io.File;
import java.util.Queue;

/**
 * Class where methods related to menu data are gathered
 */
public class CheckMenu {
    /**
     * Method that returns all data in FoodRepository.txt as string
     * @return String menu
     */
    public String  ShowMenuList () {

        TextFileUtils textFileUtils = new TextFileUtils();


        String beverage_str = "== beverage ==\n\n";
        String dessert_str = "== dessert ==\n\n";

        String food_str = textFileUtils.readFile(new File("C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt"));
        String[] food_arr = food_str.split(";");
        String[] beverage_arr;
        String[] dessert_arr;
        for (int i = 0 ; i < 10; i++) {
            beverage_arr = food_arr[i].split("/");
            for (int j = 0 ; j < beverage_arr.length; j++) {
                beverage_str += beverage_arr[j] + " ";
            }
            beverage_str += "\n";
        }
        beverage_str += "\n\n";

        for (int i = 10; i < food_arr.length; i++) {
            dessert_arr = food_arr[i].split("/");
            for (int j = 0 ; j < dessert_arr.length; j++) {
                dessert_str += dessert_arr[j] + " ";
            }
            dessert_str += "\n";
        }
        dessert_str += "\n\n";


        String output = beverage_str + dessert_str;

        return output;
    }
}
