package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Order implements Comparable<Order> {

    private boolean isDone;
    private int id;
    private static int idCounter = 0;
    private LocalDateTime pickupTime;
    private ArrayList<Pizza> items;
    public static final int DEFAULT_PICKUP_TIME_MINUTES = 20;

    public Order(){
        this.id = idCounter++;
        this.items = new ArrayList<>();
        this.pickupTime = LocalDateTime.now().plusMinutes(DEFAULT_PICKUP_TIME_MINUTES);
    }

    public void addPizza(Pizza pizza){
        this.items.add(pizza);
    }

    public void setPickupTime(int hour, int minute){
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = LocalTime.of(hour, minute);
        this.pickupTime = LocalDateTime.of(now.toLocalDate(),time);
    }

    public void finish(){
        this.isDone = true;
        //TODO: Add pizza til export csv
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public int compareTo(Order o) {
        return pickupTime.compareTo(o.pickupTime);
    }

    @Override
    public String toString() {
        ArrayList<Integer> pizzaIds = new ArrayList<>();
        for(Pizza pizza : items) {
            pizzaIds.add(pizza.getId());
        }
        String time = String.format("%02d:%02d", pickupTime.getHour(), pickupTime.getMinute());

        return String.format("ID: %-4d Pizza: %-50s %s", this.id, pizzaIds.toString(), time);
    }
}
