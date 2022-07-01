/**
 * This class is used to return the information of
 * an added topping whipped cream to an existing coffee order.
 */
public class WhippedCream extends CoffeeDecorator{

    /**
     * This constructor sets coffee the coffee object to whipped cream and
     * all previous toppings
     *
     * @param coffee a defined coffee order
     */
    public WhippedCream(Coffee coffee){
        super(coffee);
    }

    /**
     * This method is used to add whipped cream to the
     * coffee orders toppings
     * @param coffee is the coffee object we add the topping to
     */
    @Override
    public void addTopping(Coffee coffee){
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

     /**
     * Registers the cost to display for a coffee order.
     * 10 cents is added to the cost for whipped cream.
     *
     * @return coffee.Cost() the cost to display.
     */
    @Override
    public double Cost() {
        return this.coffee.Cost() + 0.10;
    }

    /**
     * Registers the text to display for a coffee order. The text
     * displays all ingredients of the coffee.
     *
     * @return coffee.printCoffee() the string to display.
     *         The string " with whipped cream" is added to the text.
     */
    @Override
    public String printCoffee(){
        return this.coffee.printCoffee() + " with whipped cream";
    }
}
