package model;

import java.util.ArrayList;
import java.util.Collections;

public class OrderList {
    private ArrayList<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }

    public void showUnfinishedOrders(){
        Collections.sort(orders);
        OrderList unfinishedOrders = new OrderList();
        for(Order order : orders) {
            if(order.isDone()) continue;
            unfinishedOrders.addOrder(order);
        }

        System.out.println(unfinishedOrders.toString());

    }

    @Override
    public String toString() {
        String returnString = "";
        for (Order order : orders) {
            returnString += order;
            returnString += "\n";
        }
        return returnString;
    }

    public static void saveOrders() {
        //Todo: new ExportOrders
    }
}
