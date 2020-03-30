import file.ImportPizza;
import model.Menu;
import model.Order;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        //Creating new menu
        Menu menu = new Menu();

        //Import pizza CSV
        ImportPizza importPizza = new ImportPizza();
        try {
            importPizza.importMenu("data/Pizzaer.csv", menu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Print menu
        //System.out.println(menu.toString());

        Order myOrder = new Order();
        myOrder.setPickupTime(20, 30);
        orders.add(myOrder);

        Order myOrder1 = new Order();
        myOrder1.setPickupTime(12, 00);
        orders.add(myOrder1);

        Order myOrder2 = new Order();
        myOrder2.setPickupTime(12, 45);
        orders.add(myOrder2);

        Order myOrder3 = new Order();
        orders.add(myOrder3);

        showOrders();


    }

    public static void showOrders(){
        Collections.sort(orders);
        System.out.println(orders.toString());

    }

}
