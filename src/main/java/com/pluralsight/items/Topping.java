package com.pluralsight.items;

public class Topping{
    public enum meat{
        CHASHU("1) Chashu - thinly sliced braised pork belly", 0),
        KAKUNI("2) Kakuni - thick-sliced braised pork belly", 0),
        NIKUSOBORO("3) Niku Soboro - ground meat (beef)", 0),
        STEAK("4) Steak - thinly sliced flank steak", 0);

        private final String name;
        private final int price;

        meat(String name, int price){
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

    public enum vegetable{
        NORI("1) Nori - shredded, thin seaweed", 0),
        BOKCHOY("2) Bok Choy - type of chinese cabbage", 0),
        SPINACH("3) Spinach - baby spinach", 0),
        SHIITAKE("4) Shiitake - mushrooms with strong, smoky flavor", 0);

        private final String name;
        private final int price;

        vegetable(String name, int price){
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

    public enum premium{
        EGG("1) Egg - soft-boiled", 1),
        KIMCHI("2) Kimchi - fermented cabbage with red pepper flakes", 1),
        SPAM("3) Spam - pan-fried ground pork and ham", 1),
        NATTO("4) Natto - fermented soybeans", 1);

        private final String name;
        private final int price;

        premium(String name, int price){
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
}