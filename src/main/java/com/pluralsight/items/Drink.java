package com.pluralsight.items;

import com.pluralsight.util.OrderItem;

public class Drink implements OrderItem {
    public enum drinkSize{
        SMALL("Small - 16 fl oz", 0),
        MEDIUM("Medium - 20 fl oz", 0),
        LARGE("Large - 24 fl oz", 2);

        private final String name;
        private final double price;

        drinkSize(String name, double price){
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
            return name + ((price>0) ? " (+$" + String.format("%.2f",price) + ")" : ""); }
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
        private final double price;

        drinkType(String name, double price){
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

    private final drinkType type;
    private final drinkSize size;

    public Drink(int type, int size){
        this.type = drinkType.values()[type-1];
        this.size = drinkSize.values()[size-1];
    }

    @Override
    public double price(){
        return (type != null? type.getPrice() : 0.0) +
                (size != null? size.getPrice() : 0.0);
    }

    @Override
    public String description(){
        return (type == null)? "No drink." :
                (type.getName() + " - $" + String.format("%.2f",type.getPrice())) + ",\n" +
                (size.getName() + ((size.getPrice()>0) ? " - $" + String.format("%.2f", size.getPrice()) : ""));
    }
}
