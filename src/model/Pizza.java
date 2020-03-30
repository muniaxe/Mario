package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Pizza {

    private int id;
    private String name;
    private String[] toppings;
    private double price;

    public Pizza(int id, String name, String[] toppings, double price) {
        this.id = id;
        this.name = name;
        this.toppings = toppings;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%-3s %-20s %-70s %s", id + ".", name, Arrays.toString(toppings), price);

    }
}
