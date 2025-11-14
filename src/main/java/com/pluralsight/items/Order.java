package com.pluralsight.items;

import com.pluralsight.util.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public double getTotal(){
        return (items == null) ? 0.0 : items.stream()
                .filter(Objects::nonNull)
                .mapToDouble(OrderItem::price)
                .sum();
    }

    // these use lambda cause IntelliJ didn't recommend I use method reference this time lol
    public List<Ramen> getRamen() {
        return items.stream()
                .filter(i -> i instanceof Ramen)
                .map(i -> (Ramen) i) // casts OrderItem to Ramen
                .collect(Collectors.toList());
    }

    public List<Drink> getDrinks(){
        return items.stream()
                .filter(i -> i instanceof Drink)
                .map(i -> (Drink) i)
                .collect(Collectors.toList());
    }

    public List<Appetizer> getAppetizers(){
        return items.stream()
                .filter(i -> i instanceof Appetizer)
                .map(i -> (Appetizer) i)
                .collect(Collectors.toList());
    }

    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (OrderItem i : items) {
            sb.append(count).append(") ").append(i.description()).append("\n");
            count++;
        }
        sb.append("\nTotal: $").append(String.format("%.2f", getTotal()));
        return sb.toString();
    }
}