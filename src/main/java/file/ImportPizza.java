package file;

import model.Menu;
import model.Pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportPizza {




    public void importMenu(String filepath, Menu menu) throws FileNotFoundException {
        File file = new File(filepath);


        Scanner sc = new Scanner(file);

        //Skipping header
        sc.nextLine();
        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            String[] lineArr = line.split(",");

            int id = Integer.parseInt(lineArr[0]);
            String name = lineArr[1];
            String[] toppings = lineArr[2].split("\\|");
            double price = Double.parseDouble(lineArr[3]);

            Pizza pizza = new Pizza(id, name, toppings, price);
            menu.addPizza(pizza);
            //System.out.println(pizza);
        }

    }

}