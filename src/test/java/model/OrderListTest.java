package model;

import file.ImportPizza;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class OrderListTest {
    public static Menu menu = new Menu();

    @Test
    public void test(){
        populateMenu();

        //Priser for 22, 22, 10
        double expectedValue = 65+65+61;

        Order tmpOrder = new Order();
        String[] pizzaIds = "22, 22, 10".split(", ");
        tmpOrder.addPizzasByStringOfIds(pizzaIds,menu);

        assertTrue("Prisen for odreren var ikke den forventede pris.", expectedValue == tmpOrder.getTotalPrice());
    }

    private void populateMenu() {
        ImportPizza importPizza = new ImportPizza();
        String fileName = "data/Pizzaer.csv";
        try {
            importPizza.importMenu(fileName, menu);
        } catch (FileNotFoundException e) {
            System.err.println("Vi kunne ikke loade menuen.");
            System.err.println("Filen: " + fileName + ", fandtes ikke.");
        }
    }

}