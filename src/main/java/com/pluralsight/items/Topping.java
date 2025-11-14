package com.pluralsight.items;

public class Topping{
    public enum meat{
        CHASHU("Chashu - thinly sliced braised pork belly", 0),
        KAKUNI("Kakuni - thick-sliced braised pork belly", 0),
        NIKUSOBORO("Niku soboro - ground meat (beef)", 0),
        STEAK("Steak - thinly sliced flank steak", 0);

        private final String name;
        private final double price;

        meat(String name, double price){
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

    public enum vegetable{
        NORI("Nori - shredded, thin seaweed", 0),
        BOKCHOY("Bok choy - type of chinese cabbage", 0),
        SPINACH("Spinach - baby spinach", 0),
        SHIITAKE("Shiitake - mushrooms with strong, smoky flavor", 0);

        private final String name;
        private final double price;

        vegetable(String name, double price){
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

    public enum premium{
        EGG("Egg - soft-boiled", 1),
        KIMCHI("Kimchi - fermented cabbage with red pepper flakes", 1),
        SPAM("Spam - pan-fried ground pork and ham", 1),
        NATTO("Natto - fermented soybeans", 1);

        private final String name;
        private final double price;

        premium(String name, double price){
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
            return name + " (+$" + String.format("%.2f",price) + ")";
        }
    }
}