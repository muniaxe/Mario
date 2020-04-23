package datamapping;

import model.Menu;
import model.Order;
import model.Pizza;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderMapper {
    public boolean saveOrder(Order order) {
        Connection connection = DBConnector.getInstance().getConnection();
        try {
            //IDs:
            //Programmet
            //1 -> 2 -> 3... CRASH //1 -> 2

            //DB
            //1 -> 2 -> ... Crash // -> 3 -> 4

            Statement orderStatement = connection.createStatement();

            String orderQuery = "INSERT INTO orders DEFAULT VALUES";
            int orderID = orderStatement.executeUpdate(orderQuery, orderStatement.RETURN_GENERATED_KEYS);

            for(Pizza pizza : order.getPizzas()) {
                int pizzaID = pizza.getId();
                String ordersPizzasQuery = "INSERT INTO orders_pizzas (orderID, pizzaID) VALUES (" + orderID + ", " + pizzaID + ")";
                orderStatement.executeUpdate(ordersPizzasQuery);
            }

            return true;

        } catch(SQLException e) {
            System.err.println("Fejl: " + e.getMessage());
            return false;
        }
    }
}
