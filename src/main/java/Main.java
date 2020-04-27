import datamapping.OrderMapper;
import datamapping.PizzaMapper;
import file.ExportPizza;
import file.ImportPizza;
import model.Menu;
import model.Order;
import model.OrderList;
import model.Pizza;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

/*
@Authors
Robert Pallesen
Mathias Hvid
Emil Dyrhøi Tolderlund Jørgensen
Jack Hagedorn Jensen
 */

public class Main {

    //Initiate orderlist and menu.
    public static OrderList orders = new OrderList();
    public static Menu menu = new Menu();
    public static OrderMapper orderMapper = new OrderMapper();
    public static PizzaMapper pizzaMapper = new PizzaMapper();
    public static final Scanner INPUT = new Scanner(System.in);
    public static boolean running = true;

    public static void main(String[] args) {
        populateMenu();
        printWelcome();

        while (running) {
            printCommands();
            String command = getCommand();
            executeCommand(command);
        }

        System.out.println("Tak for i dag!");
    }

    public static void populateMenu() {
        /*
        ImportPizza importPizza = new ImportPizza();
        String fileName = "data/Pizzaer.csv";
        try {
            importPizza.importMenu(fileName, menu);
        } catch (FileNotFoundException e) {
            System.err.println("Vi kunne ikke loade menuen.");
            System.err.println("Filen: " + fileName + ", fandtes ikke.");
        }
        */
        pizzaMapper.getAllPizzas(menu);
    }

    public static void printWelcome() {
        System.out.println("Velkommen Mario & Alfonso, til jeres ordrebehandlingssystem!");
        System.out.println("------------------------------------------------------------");
    }

    public static void printCommands() {
        System.out.println("Vælg menupunkt");
        System.out.println("ordre");
        System.out.println("menu");
        System.out.println("afslut");
    }

    public static void printCommands(String command) {
        if (command.equalsIgnoreCase("ordre")) {
            System.out.println("Vælg en af følgende underkommandoer for ordrebehandling:");
            System.out.println("ny - laver ny ordre");
            System.out.println("liste - viser liste over bestillinger");
            System.out.println("færdiggør <id> - færdiggøre ordre");
        }
    }

    public static String getCommand() {
        String command = "";
        try {
            command = INPUT.nextLine();
        } catch (Exception e) {
            System.err.println(commandNotFound(command));
        }
        return command;
    }

    public static void executeCommand(String command) {

        if (command.equalsIgnoreCase("ordre")) {
            printCommands(command);
            String tmpString = getCommand();
            String subCommand = tmpString.split(" ")[0];

            if (subCommand.equalsIgnoreCase("ny")) {
                startNewOrder();
            } else if (subCommand.equalsIgnoreCase("færdiggør")) {
                if (tmpString.split(" ").length == 2) {
                    finishOrder(tmpString);
                } else {
                    System.err.println("Forkert formatering. færdiggør <id> ");
                }
            } else if (subCommand.equalsIgnoreCase("liste")) {
                orders.showUnfinishedOrders();
            }
            else {
                System.err.println(commandNotFound(subCommand));
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

    public static void finishOrder(String tmpString) {
        try {
            int id = Integer.parseInt(tmpString.split(" ")[1]);
            Order order = orders.getOrderById(id);
            if (order != null && !order.isDone()) {
                if(orderMapper.saveOrder(order)) {
                    order.finish();
                    System.out.println("Ordrenummer " + id + " er sat til at være færdig");
                }
                else {
                    System.err.println("Der skete en fejl, da vi prøvede at gemme dataen.");
                }
            } else {
                System.err.println("Orderen du ville færdiggøre eksisterer ikke");
            }
        } catch (NumberFormatException e) {
            System.err.println("Dit id skal være et heltal.");
        }
    }


    public static void printMenu() {
        System.out.println(menu);
    }

    public static void startNewOrder() {
        Order tmpOrder = new Order();
        boolean finished;
        do {

            finished = true;
            tmpOrder.clearPizzas();
            System.out.println("Vælg pizzaer separeret af komma. Eksempel: 22, 22, 10");
            try {
                String[] pizzaIds = INPUT.nextLine().split(", ");
                if(!tmpOrder.addPizzasByStringOfIds(pizzaIds, menu)) {
                    finished = false;
                }
            } catch (Exception e) {
                System.err.println("Der var en fejl med din formatering. Prøv igen");
                finished = false;
            }

        } while (!finished);

        do {

            finished = true;
            System.out.println(
                    "Hvilket tidspunkt? Eksempel: 18:30 * OBS: Ved intet indtastet tidspunkt, er ordren sat til om "
                    + Order.DEFAULT_PICKUP_TIME_MINUTES + " minutter."
            );

            String timeInput = INPUT.nextLine();
            if(timeInput.length() > 0) {
                try {
                    String[] time = timeInput.split(":");
                    tmpOrder.setPickupTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                } catch (Exception e) {
                    System.err.println("Fejl i det indtastede tidspunkt");
                    finished = false;
                }
            }

        } while (!finished);

        System.out.println("Er du sikker på at denne ordre er korrekt?:");
        String pizzaIds = "";
        for(Pizza pizza : tmpOrder.getPizzas()) {
            pizzaIds += pizza.getId() + ", ";
        }
        System.out.println(
                "Pizzaer: " + pizzaIds + " tidspunkt: "
                + tmpOrder.getPickupTime().getHour() + ":" + tmpOrder.getPickupTime().getMinute()
        );
        boolean finishOrder = INPUT.nextLine().equalsIgnoreCase("ja");
        if(finishOrder) {
            orders.addOrder(tmpOrder);
            //Removed for DB rework.
            //saveOrders();
            System.out.println(
                    "Ordre " + tmpOrder.getId()
                    + " blev oprettet til tidspunktet "
                    + tmpOrder.getPickupTime().getHour() + ":" + tmpOrder.getPickupTime().getMinute()
            );
        }
        else {
            System.out.println("Annullerer ordre.");
        }

    }

    public static void saveOrders() {
        ExportPizza export = new ExportPizza();
        try {
            export.exportTodaysOrders(orders);
        } catch(Exception e) {
            System.err.println("Der skete en fejl ved at gemme ordrerne.");
            System.err.println(e.toString());
        }
    }

    public static void exitProgram() {
        System.out.println("Er du sikker på du vil afslutte? Alle ordrer vil blive gemt.");
        System.out.println("Muligheder: Afslut | Nej");
        boolean exit = INPUT.nextLine().equalsIgnoreCase("afslut");
        if (exit) {
            running = false;
        }
    }

    public static String commandNotFound(String command) {
        return "Kommandoen: \"" + command + "\" eksisterer ikke.";
    }

}
