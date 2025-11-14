package com.pluralsight.items;

import com.pluralsight.util.OrderItem;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ramen implements OrderItem {
    public enum bowlSize{
        SMALL("Small - 27 fl oz", 10),
        MEDIUM("Medium - 35 fl oz", 15),
        LARGE("Large - 43 fl oz", 20);

        private final String name;
        private final double price;

        bowlSize(String name, double price){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public double getPrice(){
            return price;
        }

        public String toString(){
            return name + " $" + String.format("%.2f",price);
        }
    }
    public enum broth{
        TONKOTSU("Tonkotsu - made from pork bone", 0),
        SHIO("Shio - salty & lighter", 0),
        SHOYU("Shoyu - chicken stock infused with soy sauce",0),
        MISO("Miso - made from fermented soybean paste", 0);

        private final String name;
        private final double price;

        broth(String name, double price){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public double getPrice(){
            return price;
        }

        public String toString(){
            return name;
        }
    }
    public enum noodle{
        REGULAR("Regular ramen noodle - springy, golden wheat", 0),
        WHOLEWHEAT("Whole wheat ramen noodle - thicker", 0),
        CRISPY("Crispy thin ramen noodle - fried", 0),
        UDON("Udon noodle - thickest type", 0),
        SOBA("Soba noodle - thinner but firmer", 0);

        private final String name;
        private final double price;

        noodle(String name, double price){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public double getPrice(){
            return price;
        }

        public String toString(){
            return name;
        }
    }

    //parts of Ramen, add more getters in a bit & maybe make some final?
    private final bowlSize size;
    private final broth brothType;
    private final noodle noodleType;
    private List<Topping.meat> meats;
    private List<Topping.vegetable> vegetables;
    private List<Topping.premium> premiums;
    private boolean spicy;

    //constructor establishing base
    public Ramen(int size, int brothType, int noodleType){
        this.size = bowlSize.values()[size-1];
        this.brothType = broth.values()[brothType-1];
        this.noodleType = noodle.values()[noodleType-1];
    }

    // setters
    public void setSpicy(boolean input){
        this.spicy = input;
    }

    public void setMeats(List<Topping.meat> meats){
        this.meats = meats;
    }

    public void setVegetables(List<Topping.vegetable> vegetables){
        this.vegetables = vegetables;
    }

    public void setPremiums(List<Topping.premium> premiums){
        this.premiums = premiums;
    }

    public List<Topping.meat> getMeats() {
        return meats;
    }

    public List<Topping.vegetable> getVegetables() {
        return vegetables;
    }

    public List<Topping.premium> getPremiums() {
        return premiums;
    }

    public boolean isSpicy(){
        return spicy;
    }

    @Override
    public double price() {
        double basePrice = (size != null ? size.getPrice() : 0) +
                (brothType != null ? brothType.getPrice() : 0) +
                (noodleType != null ? noodleType.getPrice() : 0);

        double meatsPrice = (meats == null) ? 0.0 : meats.stream()
                .filter(Objects::nonNull) // filters out any null entries
                .mapToDouble(Topping.meat::getPrice) // converts all objects in stream to double by getting its price
                .sum();

        double vegPrice = (vegetables == null) ? 0.0 : vegetables.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Topping.vegetable::getPrice)
                .sum();

        double premiumsPrice = (premiums == null) ? 0.0 : premiums.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Topping.premium::getPrice)
                .sum();

        return basePrice + meatsPrice + vegPrice + premiumsPrice;
    }

    @Override
    public String description(){
        String baseDesc = (size != null ? (size.getName() + " - $" + String.format("%.2f", size.getPrice())) : null)
                + "\n" + (brothType != null ? brothType.getName() : null)
                + "\n" + (noodleType != null ? noodleType.getName() : null);

        String meatDesc = (meats == null) ? "No meat." : meats.stream()
                .filter(Objects::nonNull)
                .map(Topping.meat::getName)
                .collect(Collectors.joining("\n"));

        String vegDesc = (vegetables == null) ? "No vegetables." : vegetables.stream()
                .filter(Objects::nonNull)
                .map(Topping.vegetable::getName)
                .collect(Collectors.joining("\n"));

        // .map is the only one using lambda so each line is name and associated price but formated
        String premiumDesc = (premiums == null || premiums.isEmpty()) ? "No premium toppings." : premiums.stream()
                .filter(Objects::nonNull)
                .map(p -> p.getName() + " - $" + String.format("%.2f", p.getPrice()))
                .collect(Collectors.joining("\n"));

        String spicyDesc = (isSpicy()) ? "Spicy." : "Not spicy";

        return baseDesc + "\n" + meatDesc + "\n" + vegDesc + "\n" + premiumDesc + "\n" + spicyDesc;
    }
}