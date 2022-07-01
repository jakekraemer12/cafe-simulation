/**
 * This class is used to return the information of
 * an added topping espresso to an existing coffee order.
 */
public class espresso extends CoffeeDecorator{
    /**
     * This constructor sets the Coffee object to Espresso and all
     * previous toppings.
     * @param coffee a defined coffee order
     */
    public espresso(Coffee coffee){
        super(coffee);
    }

    /**
     * This method adds espresso to the coffee orders toppings.
     * @param coffee is the coffee object we add the topping to
     */
    @Override
    public void addTopping(Coffee coffee){
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

     /**
     * Registers the cost to display for a coffee order.
     * The cost has 35 cents added for espresso.
     *
     * @return coffee.Cost() the cost to display.
     */
    @Override
    public double Cost(){
        return this.coffee.Cost() + 0.35;
    }

    /**
     * Registers the text to display for a coffee order. The text
     * displays all ingredients of the coffee.
     *
     * @return coffee.printCoffee() the string to display.
     *         The string " espresso" is added to the text.
     */
    @Override
    public String printCoffee(){
        return this.coffee.printCoffee() + " with espresso";
    }

}
