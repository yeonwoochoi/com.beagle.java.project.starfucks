import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xpath.internal.operations.Or;
import controller.BaristaController;
import controller.MatchController;
import controller.OrderController;
import domain.order.Order;
import domain.order.Receipt;
import domain.person.Barista;
import service.CheckMenu;
import service.LeavingCustomer;
import service.OrderProcess;
import service.ReadOrders;
import utils.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Required method preload
        Scanner scanner = new Scanner(System.in);

        // classes related service
        CheckMenu checkMenu = new CheckMenu();
        ReadOrders readOrders = new ReadOrders();
        LeavingCustomer leavingCustomer = new LeavingCustomer();
        OrderProcess orderProcess = new OrderProcess();

        // classes related utils
        CalculateUtils calculateUtils = new CalculateUtils();
        ConvertUtils convertUtils = new ConvertUtils();
        TextFileUtils textFileUtils = new TextFileUtils();
        TypeChangeUtils typeChangeUtils = new TypeChangeUtils();

        // classes related controller
        BaristaController baristaController = new BaristaController();
        MatchController matchController = new MatchController();
        OrderController orderController = new OrderController();

        // Queue to store order lists

        Queue queue_order_list = new LinkedList();


        // main function
        boolean run = true;

        while (run) {
            Main main = new Main();
            System.out.println(main.printMenu());
            int inputInt = scanner.nextInt();


            // order process
            if (inputInt == 1) {


                // variation declaration
                String[] order_name;
                int[] order_price;
                int [] order_count;

                boolean run2 = true;
                String each_order_name = "";
                String each_order_price = "";
                String each_order_count = "";

                String input_order_name;
                String input_order_price = "";
                String input_order_count = "";



                // Get orders
                while (run2) {

                    boolean run3 = true;

                    while (run3) {

                        System.out.println("== 메뉴 ==\n" +
                                checkMenu.ShowMenuList() + "\n음식 번호>> ");
                        String input_order_number = scanner.next() + "/";
                        String input_order = textFileUtils.findData(input_order_number, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt");
                        input_order_name = input_order.split("/")[1];
                        // Determine the adequacy of the ordered menu
                        boolean ismatch1 = matchController.CheckOrderName(input_order_name);


                        if (ismatch1) {
                            // When food is on the order menu

                            // Name data storage
                            each_order_name += input_order_name + "/";

                            // Enter quantity and check adequacy
                            System.out.println("\n\n주문 수량 >> ");
                            input_order_count = scanner.next();
                            boolean adequacy1 = orderController.AdequacyOfQuantity(input_order_count);

                            boolean run4 = true;
                            while (run4) {
                                if (adequacy1) {
                                    //Quantity input is appropriate

                                    // Quantity data storage
                                    each_order_count += input_order_count + "/";


                                    // find price data
                                    String order_info = textFileUtils.findData(input_order_name, "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\FoodRepository.txt");
                                    String[] order_arr = order_info.split("/");
                                    input_order_price = order_arr[2];

                                    // Price data storage
                                    each_order_price += input_order_price + "/";
                                    run4 = false;

                                } else {
                                    System.out.println("잘못된 수량입니다.");
                                    run4 = true;
                                }
                            }
                            run3 = false;
                        } else {
                            System.out.println("그런 메뉴는 저희 매장에선 팔지 않습니다.");
                            run3 = true;
                        }
                    }



                    // Find out if you still want to order
                    boolean run5 = true;
                    while (run5) {

                        // Attach a description of the value to enter to determine whether to continue ordering
                        System.out.println("1. 주문 계속하기\n" +
                                "2. 그만하기\n" +
                                "숫자 입력 >> ");
                        int keep_going = scanner.nextInt();

                        if (keep_going == 2) {
                            run2 = false;
                            run5 = false;
                        } else if (keep_going == 1) {
                            run2 = true;
                            run5 = false;
                        } else {
                            System.out.println("1,2 중 하나만 입력해 주십시오.");
                            run5 = true;
                        }
                    }
                }

                // Receiving orders is complete






                // Save orders received
                order_name = each_order_name.split("/");
                order_price = convertUtils.StringArrayToIntArray(each_order_price.split("/"));
                order_count = convertUtils.StringArrayToIntArray(each_order_count.split("/"));
                Order order = orderProcess.createOrder(order_name, order_price, order_count);



                // print receipt
                String receipt =  orderProcess.MakeReceipt(order);

                System.out.println("== 영수증 ==\n\n" +
                        receipt);


                // Save order data to queue to save order list
                queue_order_list.offer(order);



                // Completed Order Processing
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        queue_order_list.remove(order);
                        //((ArrayList) queue_order_list).trimToSize();
                        orderProcess.DeleteOrder(order);
                    }
                };
                timer.schedule(timerTask, order.getTotal_time());



            } else if (inputInt == 2) {
                System.out.println("== 메뉴 ==\n" + checkMenu.ShowMenuList() + "\n");
            } else if (inputInt == 3) {
                System.out.println("== 주문 목록==\n" + readOrders.ReadOrderList() + "\n");
            } else if (inputInt == 4) {
                System.out.println("== 카페 나가기 ==\n" +
                        "주문 번호 >> ");
                String order_number = scanner.next();
                String holding_bell = matchController.CheckCustomer(order_number);
                if (holding_bell.equals("O")) {

                    boolean run2 = true;
                    while (run2) {

                        System.out.println("아직 주문한 음료가 나오지 않았습니다. 그래도 나가시겠습니까?\n" +
                                "1. 나간다\n" +
                                "2. 기다린다\n" + "숫자 선택 >> ");
                        int input_number = scanner.nextInt();
                        if (input_number == 1) {
                            run2 = false;
                            String old_data = order_number + "/" + "O;";
                            textFileUtils.updateFile(old_data, "", "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\CustomerRepository.txt");
                        } else if (input_number == 2) {
                            System.out.println("안녕히 가십시오\n");
                            run2 = false;
                        } else {
                            System.out.println("1,2 중 하나만 입력해 주십시오.");
                        }
                    }
                } else if (holding_bell.equals("X")){
                    String old_data = order_number + "/" + "X;";
                    textFileUtils.updateFile(old_data, "", "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\CustomerRepository.txt");
                } else {
                    System.out.println("이미 나가셨습니다.");
                }

            } else if (inputInt == 5) {
                System.out.println("== 프로그램 종료 ==\n");
                run = false;
            } else {
                System.out.println("1,2,3,4,5 중 하나만 입력하시요.");
            }
        }
    }

    public String printMenu() {
        String str;
        str = "== Welcome to StarFucks ==\n" +
                "1. 주문하기\n" +
                "2. 메뉴 보기\n" +
                "3. 주문 목록 보기\n" +
                "4. 카페 나가기\n" +
                "5. 프로그램 종료\n" +
                "---------------------------------\n" +
                "입력 >> ";

        return str;
    }
}
