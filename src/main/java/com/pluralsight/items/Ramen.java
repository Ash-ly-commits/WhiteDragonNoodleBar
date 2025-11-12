package com.pluralsight.items;

import java.util.List;

public class Ramen{
    public enum bowlSize{
        SMALL("1) Small - 27 fl oz", 10),
        MEDIUM("2) Medium - 35 fl oz", 15),
        LARGE("3) Large - 43 fl oz", 20);

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
        TONKOTSU("1) Tonkotsu - made from pork bone", 0),
        SHIO("2) Shio - salty & lighter", 0),
        SHOYU("3) Shoyu - chicken stock infused with soy sauce",0),
        MISO("4) Miso - made from fermented soybean paste", 0);

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
        REGULAR("1) Regular Ramen Noodle - springy, golden wheat", 0),
        WHOLEWHEAT("2) Whole Wheat Ramen Noodle - thicker", 0),
        CRISPY("3) Crispy Ramen Noodle - thin & fried", 0),
        UDON("4) Udon Noodle - thickest type", 0),
        SOBA("5) Soba Noodle - thinner but firmer", 0);

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
    private broth brothType;
    private noodle noodleType;
    private List<Topping.meat> meats;
    private List<Topping.vegetable> vegetables;
    private List<Topping.premium> premiums;
    private boolean spicy;

    public Ramen(int size, int brothType, int noodleType){
        this.size = bowlSize.values()[size-1];
        this.brothType = broth.values()[brothType-1];
        this.noodleType = noodle.values()[noodleType-1];
    }

    public void setSpice(boolean input){
        this.spicy = input;
    }

    public bowlSize getSize() {
        return size;
    }

    public broth getBrothType() {
        return brothType;
    }

    public noodle getNoodleType() {
        return noodleType;
    }

    public boolean isSpicy() {
        return spicy;
    }
}