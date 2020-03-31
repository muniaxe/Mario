package file;

/*
@Authors
Robert Pallesen
Mathias Hvid
Emil Dyrhøi Tolderlund Jørgensen
Jack Hagedorn Jensen
 */

import model.Order;
import model.OrderList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class ExportPizza {
    public void exportTodaysOrders(OrderList orders) throws IOException {
        LocalDateTime date = now();
        String fileName = "orders/" + date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + "_orders.txt";
        File file = new File(fileName);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
            Order order = orders.getOrders().get(orders.getOrders().size() - 1);
            bw.write(order.toCSV());
        }
    }
}
