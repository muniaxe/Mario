package model;

import file.ExportPizza;

import java.util.ArrayList;
import java.util.Collections;

/*
@Authors
Robert Pallesen
Mathias Hvid
Emil Dyrhøi Tolderlund Jørgensen
Jack Hagedorn Jensen
 */

public class OrderList {
    private ArrayList<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void showUnfinishedOrders() {
        Collections.sort(orders);
        OrderList unfinishedOrders = new OrderList();
        for (Order order : orders) {
            if (order.isDone()) continue;
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

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }
}
