package service;

import domain.order.Order;
import utils.TextFileUtils;
import utils.TypeChange;
import utils.UtilMethods;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OrderProcess {
    public boolean createOrder(String[] order_name, int[] order_price, int[] order_count) {
        Queue each_queue = new LinkedList();
        TextFileUtils textFileUtils = new TextFileUtils();
        String each_name;
        int each_price;
        int each_count;
        int total_time = 0;
        for (int i = 0; i < order_name.length; i++) {
            each_name = order_name[i];
            each_price = order_price[i];
            each_count = order_count[i];

            String findStr = textFileUtils.findData(order_name[i], "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt");
            Object[] findArr = findStr.split("/");
            if (findArr[3] instanceof String) {
                TypeChange typeChange = new TypeChange();
                int each_time = typeChange.StringToInt((String) findArr[3]);
                total_time += each_time;
            }

            Object[] each_object = new Object[order_name.length];

            each_queue.offer(each_object);
        }
        //Order order = new Order();
        //order.setBarista_index(1);
        return true;
    }
}
