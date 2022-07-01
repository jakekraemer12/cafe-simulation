/**
 * Interface coffee constructs what methods all classes that
 * implement coffee must have. These methods are basis behind
 * what information we are seeking from each Coffee object.
 */
public interface Coffee {
        /**
         * This method adds a topping to the provided coffee object.
         * @param coffee is the coffee object we add the topping to
         */
        public void addTopping(Coffee coffee);

        /**
         * This method returns a String that contains the order
         * with appropriate toppings.
         */
        public String printCoffee();

        /**
         * This method returns a double with the price of an order of coffee
         * based on coffee's toppings.
         */
        public double Cost();
}
