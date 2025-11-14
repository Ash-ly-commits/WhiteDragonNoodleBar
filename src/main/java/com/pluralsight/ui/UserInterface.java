package com.pluralsight.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pluralsight.items.*;
import com.pluralsight.util.ReceiptWriter;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private final int[] yesNo = {1, 0};

    // asks the user a prompt and validates their response
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

    // prints all enum options & gets a valid user choice
    private <E extends Enum<E>> E chooseEnum(E[] values, String prompt) {
        System.out.print("\n");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i+1) + ") " + values[i].toString());
        }
        int choice = chooseInt(prompt, makeRangeArray(1, values.length));
        return values[choice - 1];
    }

    // creates an array of integers for valid menu options
    private int[] makeRangeArray(int fromInclusive, int toInclusive) {
        int[] a = new int[toInclusive - fromInclusive + 1];
        for (int i = 0; i < a.length; i++) a[i] = fromInclusive + i;
        return a;
    }

    public void homeScreen() {
        String homeScreenMenu = """
                \n\033[0;1m White Dragon Noodle Bar \033[0m
                \033[0;1m ~ Welcome! ~ \033[0m
                -----------------------------------
                1) New Order
                0) Exit
                Enter option:\t""";
        while (chooseInt(homeScreenMenu, yesNo) != 0) orderMenuScreen();
        System.out.println("Exiting...");
    }

    public void orderMenuScreen() {
        String orderMenu = """
                \nOrder Options
                1) Add Signature Ramen
                2) Add Custom Ramen
                3) Add Drink
                4) Add Appetizer
                5) Checkout
                0) Cancel Order
                Enter option:\t""";
        Order order = new Order();
        while (true) {
            int option = chooseInt(orderMenu, makeRangeArray(0, 5));
            switch (option) {
                case 0 -> {
                    System.out.println("Order cancelled.");
                    return;
                }
                case 1 -> {
                    Ramen r = promptForSignature();
                    if (r != null) {
                        order.addRamen(r);
                        System.out.println("Signature ramen added.");
                    }
                }
                case 2 -> {
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
                case 3 -> {
                    Drink d = new Drink(
                            chooseEnum(Drink.drinkType.values(), "Pick a drink: ").ordinal() + 1,
                            chooseEnum(Drink.drinkSize.values(), "Pick a drink size: ").ordinal() + 1
                    );
                    order.addDrink(d);
                    System.out.println("Drink added.");
                }
                case 4 -> {
                    Appetizer a = new Appetizer(chooseEnum(Appetizer.appetizerType.values(), "Pick an appetizer: ").ordinal() + 1);
                    order.addAppetizer(a);
                    System.out.println("Appetizer added.");
                }
                case 5 -> {
                    if (order.getTotal() == 0) continue;
                    System.out.println("\nHere is your order:\n" + order.getOrderSummary());
                    // CheckoutDialog below creates cute & small pop up with order summary
                    if (CheckoutDialog.showCheckout(null, order.getOrderSummary())) {
                        String path = new ReceiptWriter().saveReceipt(order);
                        System.out.println("Order placed. Receipt saved to: " + path);
                    } else {
                        System.out.println("Order cancelled.");
                    }
                    return;
                }
            }
        }
    }

    // Signature ramen selection
    private Ramen promptForSignature() {
        int option = chooseInt("""
                \nHere are our specials!
                -----------------------------------
                - Tonkotsu Deluxe Special -
                Medium bowl
                Tonkotsu broth
                Regular ramen noodle
                Chashu, Shiitake, Nori, and Egg toppings!
                Price: $16
                
                - Spicy Miso Special -
                Medium bowl
                Miso broth
                Regular ramen noodle
                Kakuni, Chashu, Spinach, Nori, Kimchi, and Egg toppings!
                Price: $17
                
                Are you interested in ordering one?
                1) Add Tonkotsu Deluxe
                2) Add Spicy Miso
                0) Back
                Pick an option:\t""", makeRangeArray(0, 2));
        Ramen r = switch (option) {
            case 1 -> new TonkotsuDeluxe();
            case 2 -> new SpicyMiso();
            default -> null;
        };
        return (r == null) ? null : customizeSignature(r);
    }

    // customize the signature ramen
    private Ramen customizeSignature(Ramen r) {
        while (true) {
            System.out.println("\n- Current Signature -\n" + r.description());
            System.out.printf("Price: $%.2f%n", r.price());
            int choice = chooseInt("""
                    \nSignature Options:
                    -----------------------------------
                    1) Remove Meat
                    2) Add Meat
                    3) Remove Vegetable
                    4) Add Vegetable
                    5) Remove Premium
                    6) Add Premium
                    0) Finish
                    Pick an option:\t""", makeRangeArray(0, 6));
            if (choice == 0) break;
            switch (choice) {
                case 1 -> removeTopping(r.getMeats(), "meat");
                case 2 -> addTopping(r.getMeats(), Topping.meat.values(), "meat");
                case 3 -> removeTopping(r.getVegetables(), "vegetable");
                case 4 -> addTopping(r.getVegetables(), Topping.vegetable.values(), "vegetable");
                case 5 -> removeTopping(r.getPremiums(), "premium");
                case 6 -> addTopping(r.getPremiums(), Topping.premium.values(), "premium");
            }
        }
        return r;
    }

    // Generic topping removal
    private <T> void removeTopping(List<T> list, String name) {
        if (list == null || list.isEmpty()) {
            System.out.println("No " + name + "s to remove.");
            return;
        }
        for (int i = 0; i < list.size(); i++) System.out.println((i + 1) + ") " + list.get(i));
        int choice = chooseInt("Pick a " + name + " to remove (0 to cancel): ", makeRangeArray(0, list.size()));
        if (choice != 0) list.remove(choice - 1);
    }

    // Generic topping addition
    private <T extends Enum<T>> void addTopping(List<T> list, T[] values, String name) {
        T picked = chooseEnum(values, "Pick a " + name + " to add: ");
        if (list == null) list = new ArrayList<>();
        list.add(picked);
    }

    // returns list for toppings
    private <T extends Enum<T>> List<T> promptForToppings(T[] values, String prompt) {
        List<T> selected = new ArrayList<>();
        while (true) {
            System.out.print("\n");
            for (int i = 0; i < values.length; i++) {
                System.out.println((i+1) + ") " + values[i].toString());
            }
            int choice = chooseInt(prompt,makeRangeArray(0, values.length));
            if (choice == 0) break;
            T picked = values[choice - 1];
            selected.add(picked);
            if (chooseInt("Want extra?\nPick 1 if yes, 0 if no: ", yesNo) == 1) selected.add(picked);
        }
        return selected;
    }
}