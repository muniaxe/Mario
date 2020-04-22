package datamapping;

import model.Menu;
import model.Pizza;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PizzaMapper {
    public void getAllPizzas(Menu menu) {
        Pizza tmpPizza;

        Connection connection = DBConnector.getInstance().getConnection();
        try {
            Statement pizzaStatement = connection.createStatement();
            Statement toppingStatement = connection.createStatement();

            ResultSet pizzaResult = pizzaStatement.executeQuery("SELECT * FROM pizzas");
            while(pizzaResult.next()) {
                int id = pizzaResult.getInt("id");
                String name = pizzaResult.getString("name");
                ArrayList<String> toppings = new ArrayList<>();
                ResultSet toppingResult = toppingStatement.executeQuery("SELECT toppings.name AS topping FROM pizzas_toppings INNER JOIN toppings WHERE " + id + " = pizzaID AND toppings.id = toppingID");
                while(toppingResult.next()) {
                    String topping = toppingResult.getString("topping");
                    toppings.add(topping);
                }
                double price = pizzaResult.getDouble("price");

                tmpPizza = new Pizza(id, name, toppings, price);
                menu.addPizza(tmpPizza);
            }
        } catch(SQLException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
    }
}
