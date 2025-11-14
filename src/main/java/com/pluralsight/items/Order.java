package com.pluralsight.items;

import com.pluralsight.util.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    Order(){
        items = new ArrayList<>();
    }

    public void addRamen(Ramen r){
        if (r != null) items.add(r);
    }

    public void addDrink(Drink d){
        if (d != null) items.add(d);
    }

    public void addAppetizer(Appetizer a){
        if (a != null) items.add(a);
    }
}