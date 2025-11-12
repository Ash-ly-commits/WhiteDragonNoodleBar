package com.pluralsight.ui;
import java.util.Scanner;
import com.pluralsight.items.Order;
import com.pluralsight.items.Ramen;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);

    public int askUserInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public int screenMenuValidation(String prompt, int[] validOptions) {
        while (true) {
            int input = askUserInt(prompt);
            for (int option : validOptions) {
                if (input == option) {
                    return input;
                }
            }
            System.out.println("Invalid option, try again.");
        }
    }

    public void homeScreen() {
        String homeScreenMenu = """
                \nWhite Dragon Noodle Bar
                ~ Welcome! ~
                1) New Order
                0) Exit
                Enter option:\t"""; //this can be nicer later lol
        int[] valid = {1, 0};
        while (true) {
            int option = screenMenuValidation(homeScreenMenu, valid);
            if (option == 0) {
                System.out.println("Exiting...");
                break;
            } else if (option == 1) {
                orderMenuScreen();
            }
        }
    }

    public void orderMenuScreen() {
        String orderMenu = """
                \nOrder Options
                1) Add Ramen
                2) Add Drink
                3) Add Appetizer
                4) Checkout
                0) Cancel Order
                Enter option:\t""";
        int[] valid = {0, 1, 2, 3, 4};
        while (true) {
            int option = screenMenuValidation(orderMenu, valid);
            if (option == 0) {
                System.out.println("Guess you're not hungry today...");
                return;
            }
            Order order;
            switch (option) {
                case 1 -> {
                    Ramen ramen = new Ramen(promptForSize(), promptForBroth(), promptForNoodle());
                    ramen.setSpicy(promptForSpicy());
                }
                case 2 -> // add Drink
                case 3 -> // add Appetizer
                case 4 -> // checkout
            }
        }
    }

    public int promptForSize() {
        for (Ramen.bowlSize b : Ramen.bowlSize.values()) {
            System.out.println(b.getName() + "  $" + b.getPrice());
        }
        int[] valid = {1, 2, 3};
        return screenMenuValidation("\tThese are the sizes and prices.\nPick a number: ", valid);
    }

    public int promptForBroth() {
        for (Ramen.broth b : Ramen.broth.values()) {
            System.out.println(b.getName());
        }
        int[] valid = {1, 2, 3, 4};
        return screenMenuValidation("\tThese are the broth types.\nPick a number: ", valid);
    }

    public int promptForNoodle() {
        for (Ramen.noodle b : Ramen.noodle.values()) {
            System.out.println(b.getName());
        }
        int[] valid = {1, 2, 3, 4, 5};
        return screenMenuValidation("\tThese are the noodle types.\nPick a number: ", valid);
    }

    public boolean promptForSpicy() {
        int ask = askUserInt("Would you like your broth spicy?\nPick 1 for yes, 0 for no: ");
        return ask == 1;
    }
}