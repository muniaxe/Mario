package model;

import java.util.ArrayList;

public class Menu {

    private ArrayList<Pizza> pizzaArray = new ArrayList<>();

    public void addPizza(Pizza pizza){
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
}
