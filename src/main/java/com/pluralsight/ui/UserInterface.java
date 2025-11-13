package com.pluralsight.ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.pluralsight.items.Order;
import com.pluralsight.items.Ramen;
import com.pluralsight.items.Topping;

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
            switch (option) { // cases are work in progress lol
                case 1 -> {
                    Ramen ramen = new Ramen(promptForSize(), promptForBroth(), promptForNoodle());
                    ramen.setSpicy(promptForSpicy());
                    ramen.setMeats(promptForMeats());
                    ramen.setVegetables(promptForVegetables());
                    ramen.setPremiums(promptForPremiumToppings());
                    order.addRamen(ramen);
                }
                case 2 -> // add Drink
                case 3 -> // add Appetizer
                case 4 -> // checkout
            }
        }
    }
// set up for Ramen base
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

    // returns list for toppings
    public List<Topping.meat> promptForMeats(){
        List<Topping.meat> meats = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int[] valid = {0,1,2,3,4};
            for(Topping.meat b: Topping.meat.values()){
                System.out.println(b.getName());
            }
            int option = screenMenuValidation("\tThese are the meat options.\nPick a number or 0 if done: ",valid);
            if (option == 0) {
                System.out.println("Next option->");
                break;
            }
            else {
                meats.add(Topping.meat.values()[option-1]);
                int[] valid2 = {1,0};
                if ((screenMenuValidation("\tWould you like extra?\nPick 1 for yes, 0 for no: ", valid2)) == 1){
                    meats.add(Topping.meat.values()[option-1]);
                }
            }
        }
        return meats;
    }
    public List<Topping.vegetable> promptForVegetables(){
        List<Topping.vegetable> vegetables = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int[] valid = {0,1,2,3,4};
            for(Topping.vegetable b: Topping.vegetable.values()){
                System.out.println(b.getName());
            }
            int option = screenMenuValidation("\tThese are the vegetable options.\nPick a number or 0 if done: ",valid);
            if (option == 0) {
                System.out.println("Next option->");
                break;
            }
            else {
                vegetables.add(Topping.vegetable.values()[option-1]);
                int[] valid2 = {1,0};
                if ((screenMenuValidation("\tWould you like extra?\nPick 1 for yes, 0 for no: ", valid2)) == 1){
                    vegetables.add(Topping.vegetable.values()[option-1]);
                }
            }
        }
        return vegetables;
    }

    public List<Topping.premium> promptForPremiumToppings(){
        List<Topping.premium> premium = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int[] valid = {0,1,2,3,4};
            for(Topping.premium b: Topping.premium.values()){
                System.out.println(b.getName() + "  $" + b.getPrice());
            }
            int option = screenMenuValidation("\tThese are the premium topping options.\nPick a number or 0 if done: ",valid);
            if (option == 0) {
                System.out.println("Next option->");
                break;
            }
            else {
                premium.add(Topping.premium.values()[option-1]);
                int[] valid2 = {1,0};
                if ((screenMenuValidation("\tWould you like extra?\nPick 1 for yes, 0 for no: ", valid2)) == 1){
                    premium.add(Topping.premium.values()[option-1]);
                }
            }
        }
        return premium;
    }
}