package model;

import java.util.ArrayList;

/*
@Authors
Robert Pallesen
Mathias Hvid
Emil Dyrhøi Tolderlund Jørgensen
Jack Hagedorn Jensen
 */

public class Menu {

    private ArrayList<Pizza> pizzaArray = new ArrayList<>();

    public void addPizza(Pizza pizza) {
        pizzaArray.add(pizza);
    }

    @Override
    public String toString() {
        String returnString = "";
        for (Pizza pizza : pizzaArray) {
            returnString += pizza;
            returnString += "\n";
        }
        return returnString;
    }

    public Pizza getPizzaById(int id) {
        for (Pizza pizza : pizzaArray) {
            if (pizza.getId() == id) {
                return pizza;
            }
        }
        return null;
    }
}
