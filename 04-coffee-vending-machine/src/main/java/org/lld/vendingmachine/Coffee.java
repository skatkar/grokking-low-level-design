package org.lld.vendingmachine;

import java.util.Map;

public class Coffee {
    private String name;
    private double price;
    private Map<Ingredient, Integer> recipes;

    public Coffee(String name, double price, Map<Ingredient, Integer> recipes) {
        this.name = name;
        this.price = price;
        this.recipes = recipes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<Ingredient, Integer> getRecipes() {
        return recipes;
    }

    public void setRecipes(Map<Ingredient, Integer> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", recipes=" + recipes +
                '}';
    }
}
