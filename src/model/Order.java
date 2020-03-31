package model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/*
@Authors
Robert Pallesen
Mathias Hvid
Emil Dyrhøi Tolderlund Jørgensen
Jack Hagedorn Jensen
 */

public class Order implements Comparable<Order> {

    private boolean isDone;
    private int id;
    private static int idCounter = 1;
    private LocalDateTime pickupTime;
    private ArrayList<Pizza> items;
    public static final int DEFAULT_PICKUP_TIME_MINUTES = 20;

    public Order() {
        this.id = idCounter++;
        this.items = new ArrayList<>();
        this.pickupTime = LocalDateTime.now().plusMinutes(DEFAULT_PICKUP_TIME_MINUTES);
    }

    public void addPizza(Pizza pizza) {
        this.items.add(pizza);
    }

    public void clearPizzas() {
        this.items = new ArrayList<>();
    }

    public void setPickupTime(int hour, int minute) {
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = LocalTime.of(hour, minute);
        this.pickupTime = LocalDateTime.of(now.toLocalDate(), time);
    }

    public void finish() {
        this.isDone = true;
    }

    public int getId() {
        return id;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public int compareTo(Order o) {
        return pickupTime.compareTo(o.pickupTime);
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    @Override
    public String toString() {
        ArrayList<Integer> pizzaIds = new ArrayList<>();
        double price = 0;
        for (Pizza pizza : items) {
            pizzaIds.add(pizza.getId());
            price += pizza.getPrice();
        }
        String time = String.format("%02d:%02d", pickupTime.getHour(), pickupTime.getMinute());

        return String.format("ID: %-4d Pizza: %-35s %-10s %.2f,- DKK", this.id, pizzaIds.toString(), time, price);
    }

    public String toCSV() {
        char splitter = ';';
        double price = 0;
        String pizzaIds = "";
        for (Pizza pizza : items) {
            pizzaIds += pizza.getId() + "|";
            price += pizza.getPrice();
        }
        String time = String.format("%02d:%02d", pickupTime.getHour(), pickupTime.getMinute());

        return String.format("%d"+ splitter + "%s"+ splitter + "%s"+ splitter + "%.2f%n", this.id, pizzaIds.substring(0,pizzaIds.length()-1), time, price);
    }
}
