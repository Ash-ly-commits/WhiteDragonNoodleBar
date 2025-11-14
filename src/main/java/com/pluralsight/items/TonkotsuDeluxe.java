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
}
