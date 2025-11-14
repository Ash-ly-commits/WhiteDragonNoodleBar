package com.pluralsight.items;

import java.util.ArrayList;
import java.util.List;

public class TonkotsuDeluxe extends Ramen{
    public TonkotsuDeluxe() {
        super(2, 1, 1);

        // sets the default toppings
        List<Topping.meat> meats = new ArrayList<>();
        meats.add(Topping.meat.CHASHU);

        List<Topping.vegetable> veggies = new ArrayList<>();
        veggies.add(Topping.vegetable.SHIITAKE);
        veggies.add(Topping.vegetable.NORI);

        List<Topping.premium> premiums = new ArrayList<>();
        premiums.add(Topping.premium.EGG);

        setMeats(meats);
        setVegetables(veggies);
        setPremiums(premiums);

        setSpicy(false);
    }

    // customization helpers
//    public void addMeat(Topping.meat m) {
//        List<Topping.meat> list = getMeats();
//        if (list == null) {
//            list = new ArrayList<>();
//            setMeats(list); }
//        list.add(m);
//    }
//
//    public void removeMeat(Topping.meat m) {
//        List<Topping.meat> list = getMeats();
//        if (list != null) list.remove(m);
//    }
//
//    public void addVegetable(Topping.vegetable v) {
//        List<Topping.vegetable> list = getVegetables();
//        if (list == null) {
//            list = new ArrayList<>();
//            setVegetables(list); }
//        list.add(v);
//    }
//
//    public void removeVegetable(Topping.vegetable v) {
//        List<Topping.vegetable> list = getVegetables();
//        if (list != null) list.remove(v);
//    }
//
//    public void addPremium(Topping.premium p) {
//        List<Topping.premium> list = getPremiums();
//        if (list == null) {
//            list = new ArrayList<>();
//            setPremiums(list); }
//        list.add(p);
//    }
//
//    public void removePremium(Topping.premium p) {
//        List<Topping.premium> list = getPremiums();
//        if (list != null) list.remove(p);
//    }
}
