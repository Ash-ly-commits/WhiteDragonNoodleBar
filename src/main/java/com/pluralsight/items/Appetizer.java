package com.pluralsight.items;

import com.pluralsight.util.OrderItem;

public class Appetizer implements OrderItem {
    public enum appetizerType{
        EDAMAME("Edamame - steamed young soybeans",4),
        YAKITORI("Yakitori - grilled, skewered chicken",6),
        TEMPURA("Shrimp tempura - deep-fried shrimp",6),
        GYOZA("Gyoza - Japanese dumplings",6),
        TOFU("Tofu - soybean based",3),
        ONIGIRI("Onigiri - Japanese rice balls with tuna and salmon",3),
        FRIEDRICE("Fried Rice - stir-fried rice with eggs and vegetables",6);

        private final String name;
        private final double price;

        appetizerType(String name, double price){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public double getPrice(){
            return price;
        }

        public String toString() {
            return name + " $" + String.format("%.2f",price);
        }
    }

    private final appetizerType type;

    public Appetizer(int type){
        this.type = appetizerType.values()[type-1];
    }

    @Override
    public double price(){
        return (type != null? type.getPrice() : 0.0);
    }
    @Override
    public String description() {
        return type.getName() + " - " + String.format("$%.2f", type.getPrice());
    }
}