package com.pluralsight.items;

import java.util.List;

public class Ramen{
    public enum bowlSize{
        SMALL("Small - 27 fl oz", 10),
        MEDIUM("Medium - 35 fl oz", 15),
        LARGE("Large - 43 fl oz", 20);

        private final String name;
        private final int price;

        bowlSize(String name, int price){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public int getPrice(){
            return price;
        }
    }
    public enum broth{
        TONKOTSU("Tonkotsu - made from pork bone", 0),
        SHIO("Shio - salty & lighter", 0),
        SHOYU("Shoyu - chicken stock infused with soy sauce",0),
        MISO("Miso - made from fermented soybean paste", 0);

        private final String name;
        private final int price;

        broth(String name, int price){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public int getPrice(){
            return price;
        }
    }
    public enum noodle{
        REGULAR("Regular ramen noodle - springy, golden wheat", 0),
        WHOLEWHEAT("Whole wheat ramen noodle - thicker", 0),
        CRISPY("Crispy thin ramen noodle - fried", 0),
        UDON("Udon noodle - thickest type", 0),
        SOBA("Soba noodle - thinner but firmer", 0);

        private final String name;
        private final int price;

        noodle(String name, int price){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public int getPrice(){
            return price;
        }
    }

    private bowlSize size;
    private broth broth;
    private noodle noodle;
    private List<Topping.meat> meats;
    private List<Topping.vegetable> vegetables;
    private List<Topping.premium> premiums;
    private boolean spicy;

    public Ramen(){
    }
}