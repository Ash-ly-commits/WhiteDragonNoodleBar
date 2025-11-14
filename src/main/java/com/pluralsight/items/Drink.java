package com.pluralsight.items;

import com.pluralsight.util.OrderItem;

public class Drink implements OrderItem {
    public enum drinkSize{
        SMALL("1) Small - 16 fl oz", 0),
        MEDIUM("2) Medium - 20 fl oz", 0),
        LARGE("3) Large - 24 fl oz", 2);

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
        WATER("1) Water",0),
        COKE("2) Coke (NOT Diet)",3),
        PEPSI("3) Pepsi (NOT Diet)",3),
        MATCHA("4) Matcha - warm",6),
        RAMUNE("5) Ramune - Japanese citrus soft drink",4),
        SAPPORO("6) Sapporo - Japanese lager",7),
        ASAHI("7) Asahi Super Dry - Japanese pale lager",7),
        SAKE("8) Sake - Japanese alcohol",7),
        SOJU("9) Soju - Korean distilled spirit",7);

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

    private drinkType type;
    private drinkSize size;

    public Drink(int type, int size){
        this.type = drinkType.values()[type-1];
        this.size = drinkSize.values()[size-1];
    }

    public drinkType getType() {
        return type;
    }

    public drinkSize getSize() {
        return size;
    }

    @Override
    public double price(){
        // price
    }

    @Override
    public String description(){
        // Drink description
    }
}
