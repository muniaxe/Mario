package model;

import java.util.ArrayList;
import java.util.Arrays;

/*
@Authors
Robert Pallesen
Mathias Hvid
Emil Dyrhøi Tolderlund Jørgensen
Jack Hagedorn Jensen
 */

public class Pizza {

    private int id;
    private String name;
    private ArrayList<String> toppings;
    private double price;

    public Pizza(int id, String name, ArrayList<String> toppings, double price) {
        this.id = id;
        this.name = name;
        this.toppings = toppings;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%-3s %-20s %-70s %s", id + ".", name, toppings.toString(), price);

    }
}
