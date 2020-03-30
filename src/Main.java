import file.ImportPizza;
import model.Menu;
import model.Order;
import model.OrderList;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    //Initiate orderlist and menu.
    static OrderList orders = new OrderList();
    static Menu menu = new Menu();
    static final Scanner INPUT = new Scanner(System.in);
    static boolean running = true;

    public static void main(String[] args) {
        populateMenu();
        printWelcome();
        while(running) {
            printCommands();
            getCommands();
        }

        System.out.println("Tak for i dag!");


    }

    public static void populateMenu() {
        ImportPizza importPizza = new ImportPizza();
        String fileName = "data/Pizzaer.csv";
        try {
            importPizza.importMenu(fileName, menu);
        } catch (FileNotFoundException e) {
            System.err.println("Vi kunne ikke loade menuen.");
            System.err.println("Filen: " + fileName + ", fandtes ikke.");
        }
    }

    private static void printWelcome() {
        System.out.println("Velkommen Mario & Alfonso, til jeres ordrebehandlingssystem!");
        System.out.println("------------------------------------------------------------");
    }

    public static void printCommands() {
        System.out.println("Eksempel kommando: \"ordre ny\"");
        System.out.println("ordre");
        System.out.println("\tny");
        System.out.println("\tændre <id>");
        System.out.println("\tliste");
        System.out.println("menu");
        System.out.println("afslut");
    }

    public static void getCommands() {
        String commandInput = "";
        try {
            commandInput = INPUT.nextLine();

            String command = commandInput.split(" ")[0];
            String[] args = null;
            if(commandInput.split(" ").length > 1)
                args = commandInput.substring(command.length() + 1).split(" ");

            if (command.equalsIgnoreCase("ordre")) {
                commandOrder(args);
            } else if (command.equalsIgnoreCase("menu")) {
                commandMenu(args);
            } else if (command.equalsIgnoreCase("afslut")) {
                exitProgram();
            } else {
                System.err.println(commandNotFound(commandInput));
            }
        } catch(Exception e) {
            System.err.println(commandNotFound(commandInput));
        }
    }

    private static void commandMenu(String[] args) {
    }

    private static void commandOrder(String[] args) {
        String subCommand = args[0];
        if(subCommand.equalsIgnoreCase("ny")) {
            Order tmpOrder = new Order();

            System.out.println("Vælg pizzaer separeret af komma. Eksempel: 22, 22, 10");
            int[] pizzaIds = INPUT.nextLine().split(", ");
            for(int pizzaId : pizzaIds) {

            }

            System.out.println(
                    "Hvilket tidspunkt? Eksempel: 18:30 * OBS: Ved intet indtastet tidspunkt, er ordren sat til om "
                    + Order.DEFAULT_PICKUP_TIME_MINUTES + " minutter."
            );
            String[] time = INPUT.nextLine().split(":");
            tmpOrder.setPickupTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]));

            orders.addOrder(tmpOrder);
        }
        else if(subCommand.equalsIgnoreCase("ændre")) {

        }
        else if(subCommand.equalsIgnoreCase("")) {

        }
    }

    private static void exitProgram() {
        System.out.println("Er du sikker på du vil afslutte? Alle ordrer vil blive gemt.");
        System.out.println("Muligheder: Afslut | Nej");
        boolean exit = INPUT.nextLine().equalsIgnoreCase("afslut");
        if(exit) {
            running = false;
        }
    }

    private static String commandNotFound(String command) {
        return "Kommandoen: \"" + command + "\" eksisterer ikke.";
    }

}
