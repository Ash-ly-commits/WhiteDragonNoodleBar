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

    public void addDrink(){}

    public void addAppetizer(){}
}