/**
 * Class BasicCoffee implements interface Coffee and is
 * used to create a basic coffee object which has a
 * defined cost of 85 cents and print statement "A black
 * coffee". A Coffee object must always be defined as a new
 * BasicCoffee first.
 */
public class BasicCoffee implements Coffee{

    /**
     * This method is required by interface Coffee.
     *
     * @param coffee is the coffee object we add the topping to
     */
    @Override
    public void addTopping(Coffee coffee){

    }

    /**
     * Registers the double cost to display for a coffee order.
     * The cost of a basic coffee is 85 cents.
     *
     * @return 0.85 the cost of a plain coffee.
     */
    @Override
    public double Cost() {
        return 0.85;
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
        return "A black coffee";
    }
}
