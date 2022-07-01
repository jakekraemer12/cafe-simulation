/**
 * This class is used to decorate a coffee object and implements
 * the interface Coffee. THe class will return important
 * information about a desired Coffee.
 */
public class CoffeeDecorator implements Coffee{

    /**
     * A coffee object with a desired order cost
     * and print statement
     */
    protected Coffee coffee;

    /**
     * This constructor sets coffee object given
     * to the class object
     * @param coffee is a coffee object given in Main
     */
    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }

    /**
     * This method adds a desired topping to the coffee object.
     * @param coffee is the coffee object we add the topping to
     */
    @Override
    public void addTopping(Coffee coffee){
        this.coffee.addTopping(coffee);
    }

    /**
     * This method returns the adds the toppings print statement
     * to the coffee order.
     * @return coffee.printCoffee()
     */
    public String printCoffee(){
        return coffee.printCoffee();
    }

    /**
     * This method returns the cost of a topping with added topping.
     * @return coffee.Cost()
     */
    public double Cost(){
        return coffee.Cost();
    }
}

