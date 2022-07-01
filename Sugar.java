/**
 * This class is used to return the information of
 * an added topping sugar to an existing coffee order.
 */
public class Sugar extends CoffeeDecorator{

    /**
     * This constructor sets the Coffee object to sugar and all
     * previous toppings.
     * @param coffee a defined coffee order
     */
    public Sugar(Coffee coffee){
        super(coffee);
    }

    /**
     * This method adds sugar to the coffee orders toppings.
     * @param coffee is the coffee object we add the topping to
     */
    @Override
    public void addTopping(Coffee coffee){
        coffee.addTopping(coffee);
        this.coffee = coffee;
    }

    /**
     * This method returns the total cost of all ingredients
     * of this coffee. 5 cents is added to the cost for sugar.
     *
     * @return coffee.Cost()
     */
    @Override
    public double Cost() {
        return this.coffee.Cost() + 0.05;
    }

    /**
     * Registers the text to display for a coffee order. The text
     * displays all ingredients of the coffee.
     *
     * @return coffee.printCoffee() the string to display.
     *         The string " with sugar" is added to the text.
     */
    @Override
    public String printCoffee(){
        return this.coffee.printCoffee() + " with sugar";
    }
}
