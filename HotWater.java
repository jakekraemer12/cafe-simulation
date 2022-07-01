/**
 * This class is used to return the information of
 * an added topping hot water to an existing coffee order.
 */
public class HotWater extends CoffeeDecorator {
    /**
     * This constructor sets the to hot water and
     * all previous toppings
     *
     * @param coffee a defined coffee order
     */
    public HotWater(Coffee coffee){
        super(coffee);
    }

    /**
     * This method is used to add hot water to the
     * coffee orders toppings
     *
     * @param coffee is the coffee object we add the topping to
     */
    @Override
    public void addTopping(Coffee coffee){
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

    /**
     * Registers the cost to display for a coffee order.
     * The text is not changed because hot water is free.
     *
     * @return coffee.Cost() the cost to display.
     */
    @Override
    public double Cost(){
        return coffee.Cost();
    }

    /**
     * Registers the text to display for a coffee order. The text
     * displays all ingredients of the coffee.
     *
     * @return coffee.printCoffee() the string to display.
     *         The string " with hot water" is added to the text.
     */
    @Override
    public String printCoffee(){
        return coffee.printCoffee() + " with hot water";
    }
}