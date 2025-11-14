package com.pluralsight.items;

public class Drink{
    public enum drinkSize{
        SMALL("Small - 16 fl oz", 0),
        MEDIUM("Medium - 20 fl oz", 0),
        LARGE("Large - 24 fl oz", 2);

        private final String name;
        private final int price;

        drinkSize(String name, int price){
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

    public enum drinkType{
        WATER("Water",0),
        COKE("Coke (NOT Diet)",3),
        PEPSI("Pepsi (NOT Diet)",3),
        MATCHA("Matcha - warm",6),
        RAMUNE("Ramune - Japanese citrus soft drink",4),
        SAPPORO("Sapporo - Japanese lager",7),
        ASAHI("Asahi Super Dry - Japanese pale lager",7),
        SAKE("Sake - Japanese alcohol",7),
        SOJU("Soju - Korean distilled spirit",7);

        private final String name;
        private final int price;

        drinkType(String name, int price){
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
