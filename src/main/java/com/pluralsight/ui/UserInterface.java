package com.pluralsight.ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pluralsight.items.*;
import com.pluralsight.util.ReceiptWriter;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private final int[] yesNo = {1, 0};

    //Combines my previous menuScreenValidation() with askUserInt()
    private int chooseInt(String prompt, int[] validOptions) {
        while (true) {
            try {
                System.out.print(prompt);
                int v = scanner.nextInt();
                scanner.nextLine();
                for (int opt : validOptions) if (opt == v) return v;
                System.out.println("Invalid option, try again.");
            } catch (Exception ex) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // prints all enum options & gets valid user choice
    private <E extends Enum<E>> E chooseEnum(E[] values, String prompt) {
        for (E value : values) {
            try {
                System.out.println(value.getClass().getMethod("getName").invoke(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int[] validOptions = makeRangeArray(1, values.length);
        int choice = chooseInt(prompt, validOptions);
        return values[choice - 1];
    }

    // so I dont have to manually write each array of integers for valid menu options
    private int[] makeRangeArray(int fromInclusive, int toInclusive) {
        int[] a = new int[toInclusive - fromInclusive + 1];
        for (int i = 0; i < a.length; i++) a[i] = fromInclusive + i;
        return a;
    }

    public void homeScreen() {
        String homeScreenMenu = """
                \nWhite Dragon Noodle Bar
                ~ Welcome! ~
                1) New Order
                0) Exit
                Enter option:\t"""; //this can be nicer later lol
        while (chooseInt(homeScreenMenu, yesNo) != 0) orderMenuScreen();
        System.out.println("Exiting...");
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
        Order order = new Order();
        while (true) {
            int option = chooseInt(orderMenu, makeRangeArray(0,4));
            switch (option) {
                case 0 -> { System.out.println("Order cancelled."); return; }
                case 1 -> {
                    Ramen r = new Ramen(
                            chooseEnum(Ramen.bowlSize.values(), "Pick a bowl size: ").ordinal() + 1,
                            chooseEnum(Ramen.broth.values(), "Pick a broth type: ").ordinal() + 1,
                            chooseEnum(Ramen.noodle.values(), "Pick a noodle type: ").ordinal() + 1
                    );
                    r.setSpicy(chooseInt("Want it spicy?\nPick 1 if yes, 0 if no: ", yesNo) == 1);
                    r.setMeats(promptForToppings(Topping.meat.values(), "Pick meat toppings (0 to stop): "));
                    r.setVegetables(promptForToppings(Topping.vegetable.values(), "Pick vegetable toppings (0 to stop): "));
                    r.setPremiums(promptForToppings(Topping.premium.values(), "Pick premium toppings (0 to stop): "));
                    order.addRamen(r);
                    System.out.println("Ramen order added.");
                }
                case 2 -> {
                    Drink d = new Drink(
                            chooseEnum(Drink.drinkType.values(), "Pick a drink: ").ordinal() + 1,
                            chooseEnum(Drink.drinkSize.values(), "Pick a drink size: ").ordinal() + 1
                    );
                    order.addDrink(d);
                    System.out.println("Drink added.");
                }
                case 3 -> {
                    Appetizer a = new Appetizer(chooseEnum(Appetizer.appetizerType.values(), "Pick an appetizer: ").ordinal() + 1);
                    order.addAppetizer(a);
                    System.out.println("Appetizer added.");
                }
                case 4 -> {
                    if (order.getTotal() == 0) continue;
                    System.out.println("Here is your order:\n" + order.getOrderSummary());
                    int confirm = chooseInt("Place order?\nPick 1 if yes, 0 if no: ", yesNo);
                    if (confirm == 1) {
                        new ReceiptWriter().saveReceipt(order);
                        System.out.println("Order placed. Thank you!");
                    } else System.out.println("Order cancelled.");
                    return;
                }
            }
        }
    }

    // returns list for toppings (any of em its generic YIPPEEEE)
    private <T extends Enum<T>> List<T> promptForToppings(T[] values, String prompt) {
        List<T> selected = new ArrayList<>();
        while (true) {
            for (T value : values) {
                try { System.out.println(value.getClass().getMethod("getName").invoke(value)); }
                catch (Exception e) {
                    e.printStackTrace(); }
            }
            int[] valid = makeRangeArray(0, values.length);
            int choice = chooseInt(prompt, valid);
            if (choice == 0) break;
            T picked = values[choice - 1];
            selected.add(picked);
            if (chooseInt("Want extra?\nPick 1 if yes, 0 if no: ", yesNo) == 1) selected.add(picked);
        }
        return selected;
    }

    // set up for drinks
    public int promptForDrinkType() {
        for (Drink.drinkType d : Drink.drinkType.values())
            System.out.println(d.getName() + " $" + d.getPrice());
        return chooseInt("\tThese are the drink options and cost.\nPick a number: ", new int[]{1,2,3,4,5,6,7,8,9});
    }

    public int promptForDrinkSize() {
        for (Drink.drinkSize d : Drink.drinkSize.values())
            System.out.println(d.getName() + " Additional $" + d.getPrice());
        return chooseInt("\tThese are the sizes and additional costs.\nPick a number: ", new int[]{1,2,3});
    }

    // set up for appetizers
    public int promptForAppetizer() {
        for (Appetizer.appetizerType a : Appetizer.appetizerType.values())
            System.out.println(a.getName() + " $" + a.getPrice());
        return chooseInt("\tThese are the appetizer options and cost.\nPick a number: ", makeRangeArray(1, Appetizer.appetizerType.values().length));
    }
}