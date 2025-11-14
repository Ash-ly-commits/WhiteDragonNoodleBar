package com.pluralsight.ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pluralsight.items.*;
import com.pluralsight.util.ReceiptWriter;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private final int[] yesNo = {1, 0};

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
            Order order = new Order();
            switch (option) { // cases are work in progress lol
                case 1 -> {
                    Ramen ramen = new Ramen(promptForSize(), promptForBroth(), promptForNoodle());
                    ramen.setSpicy(promptForSpicy());
                    ramen.setMeats(promptForMeats());
                    ramen.setVegetables(promptForVegetables());
                    ramen.setPremiums(promptForPremiumToppings());
                    order.addRamen(ramen);
                }
                case 2 -> {
                    Drink drink = new Drink(promptForDrinkType(), promptForDrinkSize());
                    order.addDrink(drink);
                }
                case 3 -> {
                    Appetizer appetizer = new Appetizer(promptForDrinkType());
                    order.addAppetizer(appetizer);
                }
                case 4 -> {
                    if(order.getTotal() != 0.0){
                        System.out.println("Here is your order:\n" + order.getOrderSummary());
                        if ((screenMenuValidation("Would you like to place your order now?\nPick 1 to confirm, 0 to cancel: ", yesNo)) == 1){
                            ReceiptWriter receiptWriter = new ReceiptWriter();
                            receiptWriter.saveReceipt(order);
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

// set up for Ramen base
    public int promptForSize() {
        for (Ramen.bowlSize s : Ramen.bowlSize.values()) {
            System.out.println(s.getName() + "  $" + s.getPrice());
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
        for (Ramen.noodle n : Ramen.noodle.values()) {
            System.out.println(n.getName());
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
            for(Topping.meat m: Topping.meat.values()){
                System.out.println(m.getName());
            }
            int option = screenMenuValidation("\tThese are the meat options.\nPick a number or 0 if done: ",valid);
            if (option == 0) {
                System.out.println("Next option->");
                break;
            }
            else {
                meats.add(Topping.meat.values()[option-1]);
                if ((screenMenuValidation("\tWould you like extra?\nPick 1 for yes, 0 for no: ", yesNo)) == 1){
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
            for(Topping.vegetable v: Topping.vegetable.values()){
                System.out.println(v.getName());
            }
            int option = screenMenuValidation("\tThese are the vegetable options.\nPick a number or 0 if done: ",valid);
            if (option == 0) {
                System.out.println("Next option->");
                break;
            }
            else {
                vegetables.add(Topping.vegetable.values()[option-1]);
                if ((screenMenuValidation("\tWould you like extra?\nPick 1 for yes, 0 for no: ", yesNo)) == 1){
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
            for(Topping.premium p: Topping.premium.values()){
                System.out.println(p.getName() + "  $" + p.getPrice());
            }
            int option = screenMenuValidation("\tThese are the premium topping options.\nPick a number or 0 if done: ",valid);
            if (option == 0) {
                System.out.println("Next option->");
                break;
            }
            else {
                premium.add(Topping.premium.values()[option-1]);
                if ((screenMenuValidation("\tWould you like extra?\nPick 1 for yes, 0 for no: ", yesNo)) == 1){
                    premium.add(Topping.premium.values()[option-1]);
                }
            }
        }
        return premium;
    }

    // set up for drinks
    public int promptForDrinkType(){
        for(Drink.drinkType d: Drink.drinkType.values()){
            System.out.println(d.getName() + " $" + d.getPrice());
        }
        int[] valid = {1,2,3};
        return screenMenuValidation("\tThese are the drink options and cost.\nPick a number: ",valid);
    }

    public int promptForDrinkSize(){
        for(Drink.drinkSize d: Drink.drinkSize.values()){
            System.out.println(d.getName() + " Additional $" + d.getPrice());
        }
        int[] valid = {1,2,3};
        return screenMenuValidation("\tThese are the sizes and additional costs.\nPick a number: ",valid);
    }

    // set up for appetizers
    public int promptForAppetizer(){
        for(Appetizer.appetizerType a: Appetizer.appetizerType.values()){
            System.out.println(a.getName() + " $" + a.getPrice());
        }
        int[] valid = {1,2,3,4,5,6,7,8,9};
        return screenMenuValidation("\tThese are the appetizer options and cost.\nPick a number: ",valid);
    }
}