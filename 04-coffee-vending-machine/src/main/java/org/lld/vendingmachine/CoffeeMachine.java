package org.lld.vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {

    private static final CoffeeMachine instance = new CoffeeMachine();
    private List<Coffee> coffeeMenu;
    private Map<String, Ingredient> ingredientMap;

    private CoffeeMachine() {
        this.coffeeMenu = new ArrayList<>();
        this.ingredientMap = new HashMap<>();

        initializeIngredients();
        initializeCoffeeMenu();
    }

    public static CoffeeMachine getInstance(){
        return instance;
    }

    private void initializeCoffeeMenu() {
        Map<Ingredient, Integer> espressoRecipe = new HashMap<>();
        espressoRecipe.put(ingredientMap.get("Coffee"), 1);
        espressoRecipe.put(ingredientMap.get("Milk"), 1);
        espressoRecipe.put(ingredientMap.get("Water"), 1);
        coffeeMenu.add(new Coffee("Espresso", 2.5, espressoRecipe));

        Map<Ingredient, Integer> cappuccinoRecipe = new HashMap<>();
        cappuccinoRecipe.put(ingredientMap.get("Coffee"), 2);
        cappuccinoRecipe.put(ingredientMap.get("Water"), 1);
        cappuccinoRecipe.put(ingredientMap.get("Milk"), 1);
        coffeeMenu.add(new Coffee("Cappuccino", 3.5, cappuccinoRecipe));

        Map<Ingredient, Integer> latteRecipe = new HashMap<>();
        latteRecipe.put(ingredientMap.get("Coffee"), 1);
        latteRecipe.put(ingredientMap.get("Water"), 1);
        latteRecipe.put(ingredientMap.get("Milk"), 2);
        coffeeMenu.add(new Coffee("Latte", 4.0, latteRecipe));
    }

    private void initializeIngredients() {
        ingredientMap.put("Coffee", new Ingredient("Coffee", 10));
        ingredientMap.put("Milk", new Ingredient("Milk", 10));
        ingredientMap.put("Water", new Ingredient("Water", 10));
    }
    
    public void displayMenu() {
        System.out.println("Coffee menu => ");
        for(Coffee coffee : coffeeMenu) {
            System.out.println("coffee = " + coffee);
        }
    }

    public synchronized Coffee selectCoffee(String coffee) {
        for(Coffee availableCoffee : coffeeMenu) {
            if(coffee.equalsIgnoreCase(availableCoffee.getName())){
                return availableCoffee;
            }
        }
        return null;
    }


    public synchronized void dispenseCoffee(Coffee coffee, Payment payment) {
        if(payment.getAmount() >= coffee.getPrice()){
            if(hasEnoughQuantity(coffee)){
                updateIngredients(coffee);
                System.out.println("Dispensing coffee = " + coffee);

                double change = payment.getAmount() - coffee.getPrice();
                if(change > 0)
                    System.out.println("Here is your change = " + change);
            }else{
                System.out.println("We are running low on the inventory");
            }
        }else{
            System.out.println("Insufficient amount paid. Can't dispense");
        }
    }

    private void updateIngredients(Coffee coffee) {
        for(Map.Entry<Ingredient, Integer> entry : coffee.getRecipes().entrySet()) {
            Ingredient ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            ingredient.updateQuantity(-requiredQuantity);
            if(ingredient.getQuantity() < 3){
                System.out.println("Running low on = " + ingredient.getName());
            }
        }
    }

    private boolean hasEnoughQuantity(Coffee coffee) {
        for(Map.Entry<Ingredient, Integer> entry : coffee.getRecipes().entrySet()) {
            Ingredient ingredient = entry.getKey();
            int recipeQuantity = entry.getValue();
            if(ingredient.getQuantity() < recipeQuantity){
                return false;
            }
        }
        return true;
    }
}
