import file.ImportPizza;
import model.Menu;
import model.Order;
import model.OrderList;
import model.Pizza;

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
            String command = getCommand();
            executeCommand(command);
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
        System.out.println("ordre");
        System.out.println("\tny");
        System.out.println("\tændre <id>");
        System.out.println("\tliste");
        System.out.println("menu");
        System.out.println("afslut");
    }

    public static void printCommands(String command) {
        if(command.equalsIgnoreCase("ordre")) {
            System.out.println("Vælg en af følgende underkommandoer for ordrebehandling:");
            System.out.println("\tny");
            System.out.println("\tændre <id>");
            System.out.println("\tliste");
        }
    }

    public static String getCommand() {
        String command = "";
        try {
            command = INPUT.nextLine();
        } catch(Exception e) {
            System.err.println(commandNotFound(command));
        }
        return command;
    }

    public static void executeCommand(String command) {
        if (command.equalsIgnoreCase("ordre")) {
            printCommands(command);
            String subCommand = getCommand();
            if(subCommand.equalsIgnoreCase("ny")) {
                System.out.println("ny ordre");
            }
        }
        else if (command.equalsIgnoreCase("menu")) {
            //printCommands(command);
            printMenu();
        }
        else if (command.equalsIgnoreCase("afslut")) {
            exitProgram();
        }
        else {
            System.err.println(commandNotFound(command));
        }
    }


    private static void printMenu() {
        System.out.println(menu);
    }

    private static void commandOrder(String[] args) {
        String subCommand = args[0];
        if(subCommand.equalsIgnoreCase("ny")) {
            Order tmpOrder = new Order();

            System.out.println("Vælg pizzaer separeret af komma. Eksempel: 22, 22, 10");
            String[] pizzaIds = INPUT.nextLine().split(", ");
            for(String pizzaId : pizzaIds) {
                int id = Integer.parseInt(pizzaId);
                //TODO: Få pizza fra menu med ID, og tilføj til odreren.
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
        else if(subCommand.equalsIgnoreCase("liste")) {

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
