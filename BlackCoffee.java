/**
 * BlackCoffee is the default coffee that is created when a user starts an order.
 * BlackCoffee is used to create a new object that holds the print text and cost of a black coffee
 * order.
 */
public class BlackCoffee extends CoffeeDecorator {

    /**
     * This constructor initializes the coffee object
     * to black coffee and all previous toppings.
     *
     * @param coffee a defined coffee order
     */
    public BlackCoffee(Coffee coffee){
        super(coffee);
    }

    /**
     * This method adds black coffee to the coffee orders toppings.
     *
     * @param coffee is the coffee object we add the topping to.
     */
    @Override
    public void addTopping(Coffee coffee){
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

    /**
     * Registers the double cost to display for a coffee order.
     * The cost of a black coffee is 85 cents.
     *
     * @return cost of black coffee.
     */
    @Override
    public double Cost() {
        return this.coffee.Cost();
    }

    /**
     * Registers the text to display for a coffee order. The text
     * displays all ingredients of the coffee.
     *
     * @return coffee.printCoffee() the string to display.
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee();
    }

}