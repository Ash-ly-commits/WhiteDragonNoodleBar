package com.pluralsight.items;

import com.pluralsight.util.OrderItem;

public class Appetizer implements OrderItem {
    public enum appetizerType{
        EDAMAME("1) Edamame - steamed young soybeans",4),
        YAKITORI("2) Yakitori - grilled, skewered chicken",6),
        TEMPURA("3) Shrimp tempura - deep-fried shrimp",6),
        GYOZA("4) Gyoza - Japanese dumplings",6),
        TOFU("5) Tofu - soybean based",3),
        ONIGIRI("6) Onigiri - Japanese rice balls with tuna and salmon",3),
        FRIEDRICE("7) Fried Rice - stir-fried rice with eggs and vegetables",6);

        private final String name;
        private final int price;

        appetizerType(String name, int price){
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

    private appetizerType type;

    public Appetizer(int type){
        this.type = appetizerType.values()[type-1];
    }

    public appetizerType getType() {
        return type;
    }

    @Override
    public double price(){
        return (type != null? type.getPrice() : 0.0);
    }

    @Override
    public String description(){
        // description of app
    }
}