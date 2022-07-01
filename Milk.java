/**
 * This class is used to return the information of
 * an added topping milk to an existing coffee order.
 */
public class Milk extends CoffeeDecorator{

    /**
     * This constructor sets the Coffee object to milk and all
     * previous toppings.
     *
     * @param coffee a defined coffee order
     */
    public Milk(Coffee coffee){
        super(coffee);
    }

    /**
     * This method adds milk to the coffee orders toppings.
     *
     * @param coffee is the coffee object we add the topping to
     */
    @Override
    public void addTopping(Coffee coffee){
        coffee.addTopping(this.coffee);
        this.coffee = this.coffee;
    }

    /**
     * Registers the text to display for a coffee order. The text
     * displays all ingredients of the coffee.
     *
     * @return coffee.printCoffee() the string to display.
     *         The string " with milk" is added to the text.
     */
    @Override
    public String printCoffee(){
        return this.coffee.printCoffee() + " with milk";
    }

    /**
     * Registers the cost to display of a coffee order based on ingredients.
     * 15 cents is added to the cost of the coffee order, the price of milk.
     *
     * @return coffee.Cost() the cost to display.
     */
    @Override
    public double Cost(){
        return this.coffee.Cost() + 0.15;
    }
}
