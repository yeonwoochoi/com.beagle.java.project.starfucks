package service;

import utils.ConvertUtils;
import utils.TextFileUtils;
import utils.UtilMethods;

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
        ConvertUtils convertUtils = new ConvertUtils();

        Queue queueBeverage = convertUtils.fileTextToQueue("BeverageRepository");
        Queue queueDessert = convertUtils.fileTextToQueue("DessertRepository");

        String beverageStr = "";
        String dessertStr = "";

        for (int i = 0; i < queueBeverage.size(); i++) {
            beverageStr += convertUtils.queueToString(queueBeverage, i, " ") + "\n";
            dessertStr += convertUtils.queueToString(queueDessert, i, " ") + "\n";
        }

        String output = "== Beverage == +\n\n" + beverageStr + "\n" + "Dessert" + "\n\n" + dessertStr;

        return output;
    }
}
