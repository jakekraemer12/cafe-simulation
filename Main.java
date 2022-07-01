import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * This is a cafe simulation. The user will be able to create
 * coffee orders and orders will be recorded on a text file.
 * An active inventory of the cafe is kept within this class using an
 * array of integers amountVals. A receipt with the order cost and ingredients
 * are kept on logFile.txt. An active list of ingredient inventories is
 * kept in inventory.txt. The user can also create employee's for the cafe.
 * The employees are able to log into this application to receive a discount on coffee.
 *
 * @author Jake Kraemer
 * date 6/30/2022
 */
public class Main {
    /**
     * Integer array amountVals holds the inventory of the cafe.
     * Each index represents an element ingredient of coffee.
     */
    public static int[] amountVals = new int[6];

    /**
     * isEmployee is used to check if a user is an employee.
     * isEmployee is false until the user logins using employeeID.
     */
    public static boolean isEmployee = false;;
    /**
     * employees is an ArrayList that holds employee objects used to print
     * employees in log
     */
    public static ArrayList<Employee> employees = new ArrayList<>();

    /**
     * Main is used to guide the cafe application. Options for what
     * the user can do with this simulation are printed in main. The method
     * performs tasks based on user input.
     */
    public static void main(String[] args) {
        ArrayList<String> Item = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> Temp2;
        Stack<String> stack = new Stack<>();


        initializeInventory();
        initializeEmployees();

        //Runs interface for cafe application.
        Scanner userOrders = new Scanner(System.in);
        System.out.println("Cafe Application Running...");
        int input = 0;
        while (input != 1) {
            System.out.println("Press 1: Read Inventory");
            System.out.println("Press 2: Create Coffee Order");
            System.out.println("Press 3: Update Inventory");
            System.out.println("Press 4: Update Log File");
            System.out.println("Press 5: Enter New Employee");
            System.out.println("Press 6: Update Employee Log File");
            System.out.println("Press 7: Exit Application");
            switch (userOrders.nextLine()) {
                case "1":
                    //Read from inventory.txt
                    //Method will return an integer array
                    inventoryReader(userOrders);
                    break;
                case "2":
                    //create coffee order - use create order method
                    //Once a coffee order is complete push order into the
                    Scanner create = new Scanner(System.in);
                    String line = "yes";
                    //Inquires if the user is an employee. If yes the string userID
                    //stores the user inputted ID.
                    System.out.println("Are you an employee? yes or no");
                    if (create.nextLine().equals("yes")) {
                        System.out.println("Please enter you employee ID: ");
                        String userID = create.nextLine();

                        for (int i = 0; i < employees.size(); ++i) {
                            if (userID.equals(employees.get(i).getEmployeeID())) {
                                isEmployee = true;
                                System.out.println("Employee Discount Added! Enjoy 50% off coffee.");
                                break;
                            }
                        }
                    }
                    do {
                        create = new Scanner(System.in);
                        //Only able to create coffee order if black coffee is available.
                        if (amountVals[0] > 0) {
                            //Temp2 is an arrayList of strings which hold the string
                            //that a coffee orders display text followed by its cost display text.
                            Temp2 = CreateOrder();
                            //Each pair of display text are added to the appropriate arrayList.
                            for (int i = 0; i < Temp2.size(); i += 2) {
                                Item.add(Temp2.get(i));
                                price.add(Double.parseDouble(Temp2.get(i + 1)));
                            }
                            } else {
                                System.out.println("No more coffee. Please come again later!");
                            }

                            System.out.println("Do you want to add another coffee to this order? - yes or no");
                        } while (!(line = create.nextLine()).equals("no"));

                        //Adds display text for receipt in stack in the format of printOrder
                        stack.push(PrintOrder(Item, price));

                        //Writes the stack onto cvs file CoffeeOrders.
                        FileWriter output = null;
                        try {
                            File cvsOutputFile = new File("CoffeeOrders");
                            output = new FileWriter(cvsOutputFile);
                            for (String str : stack) {
                                output.write("Writing orders from stack" + "\n");
                                output.write(str);
                            }
                            output.flush();
                            output.close();
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                        break;
                        case "3":
                            //Update inventory.txt files with new values
                            //the method takes the inventory (integer array as input)
                            //and has no return type
                            inventoryWriter(amountVals);

                            break;
                        case "4":
                            //the contents from the stack are push into the
                            //reuse file writer
                            logWriter(stack);
                            break;
                        case "5":
                            //asks user for new employees first and last name
                            // creates unique emploeeID for created Employee
                            Scanner nameScan = new Scanner(System.in);
                            int stop = 0;
                            do {
                                System.out.println("Please enter the new employees first and last name");
                                String thisFirst = nameScan.next();
                                String thisLast = nameScan.next();
                                createEmployee(thisFirst, thisLast);
                                System.out.println("Do you want to add another employee? yes or no");
                                if (nameScan.next().equals("no")) {
                                    stop = 1;
                                }
                            } while (stop != 1);
                            break;
                        case "6":
                            //Updates employee log file new newly created employees
                            //method takes the employee list and has no return type
                            employeeLogWriter(employees);
                            System.out.println("Employee log successfully updated");
                            break;
                        case "7":
                            //perform the action of
                            //case 3: update inventory
                            //case 4: update logFile
                            //case 6: update employeeFile
                            inventoryWriter(amountVals);
                            logWriter(stack);
                            employeeLogWriter(employees);
                            input = 1;
                            break;
                        default:
                            System.out.println("Invalid Selection. Please Try Again");

            }
        }
    }

    /**
     * StringBuilder is initialized with RECEIPT and used to print
     * in an organized fashion an order for coffee.
     */
    public static StringBuilder str = new StringBuilder("RECEIPT\n");

    /**
     * Print order takes the lists of items and cost and formats
     * a string that neatly returns all the items and the total
     * cost to be displayed in logFile.
     *
     * @param Item arrayList with thr items of a coffee order.
     * @param price arrayList with the price of a coffee order.
     * @return str.toString() a string that displays
     * "Item X: XXXX | Cost X.XX"
     * "Total cost of the order: X.XX".
     */
    public static String PrintOrder(ArrayList<String> Item, ArrayList<Double> price){
        double sum = 0.0;
        for(int i = 0; i < Item.size(); ++i){
            str.append("Item " + (i + 1) + ": " + Item.get(i) + " | Cost " + price.get(i) + "\n");
            sum += price.get(i);
        }
        str.append("Total cost of the order: " + sum);

        return str.toString();
    }

    /**
     * Create Order is used to create an arrayList of strings that hold
     * the text to be displayed for a coffee order, followed by the cost as a string.
     * The user is able to select which topping they would like to include. If a topping
     * is used the inventory's array is updated.
     *
     * @return coffeeOrder an arrayList of strings with order text
     * followed by order cost.
     */
    public static ArrayList<String> CreateOrder(){
        Scanner userFeedback = new Scanner(System.in);
        ArrayList<String> coffeeOrder = new ArrayList<String> ();
        //initializes a new coffee object with basic coffee class
        Coffee basicCoffee = new BasicCoffee();

            //Black coffee inventory decreased by one since black coffee
            //is the base of every coffee.
            System.out.println("Coffee order created. Select toppings for the first coffee:");
            amountVals[0] = amountVals[0] - 1;
            int in = 0;
            while (in != 1) {
                //User is instructed to enter topping corresponding to switch statement.
                System.out.println("Enter the following values to add toppings: 1.) milk, 2.) hot water, 3.) espresso, 4.) sugar, 5) whipped cream, e - to complete order");
                switch (userFeedback.nextLine()) {
                    case "1":
                        //If milk is available, milk is added coffee object.
                        if(amountVals[1] > 0) {
                            basicCoffee = new Milk(basicCoffee);
                            amountVals[1] = amountVals[1] - 1;
                        }
                        else {
                            System.out.println("Out of milk. Try a different topping.");
                        }
                        break;
                    case "2":
                        //If hot water is available, hot water is added to the coffee object.
                        if(amountVals[2] > 0) {
                            basicCoffee = new HotWater(basicCoffee);
                            amountVals[2] = amountVals[2] - 1;
                        }
                        else {
                            System.out.println("Out of hot water. Try a different topping.");
                        }
                        break;
                    case "3":
                        //If espresso is available, espresso is added to the coffee object.
                        if(amountVals[3] > 0) {
                            basicCoffee = new espresso(basicCoffee);
                            --amountVals[3];
                        }
                        else {
                            System.out.println("Out of espresso. Try a different topping.");
                        }
                        break;
                    case "4":
                        //If sugar is available, sugar is added to the coffee object.
                        if(amountVals[4] > 0) {
                            basicCoffee = new Sugar(basicCoffee);
                            --amountVals[4];
                        }
                        else {
                            System.out.println("Out of sugar. Try a different topping.");
                        }
                        break;
                    case "5":
                        //If whipped cream is available, whipped cream is added to the coffee object.
                        if(amountVals[5] > 0) {
                            basicCoffee = new WhippedCream(basicCoffee);
                            --amountVals[5];

                        }
                        else {
                            System.out.println("Out of whipped cream. Try a different topping.");
                        }
                        break;
                    case "e":
                        //completes order
                        in = 1;
                        coffeeOrder.add(basicCoffee.printCoffee());
                        //If the user is an employee a discount is added to the order.
                        if(isEmployee) {
                           String cost = " " + (0.5 * basicCoffee.Cost());
                           coffeeOrder.add(cost);
                        }
                        else{
                           String cost = " " + basicCoffee.Cost();
                           coffeeOrder.add(cost);
                        }
                        break;
                    default:
                        //default case has user reenter input
                        System.out.println("Invalid input");
                }

            }

        return coffeeOrder;
    }

    /**
     * This method is used to read and print the items in
     * the inventory to the user. The number of ingredients
     * always follows "= ", thus we display to the user the char
     * that follows. If the file is not found an error is displayed to the user.
     *
     * @param scan is a scanner from main.
     * @return amountVals is an integer array with number of ingredients.
     */
    public static int[] inventoryReader(Scanner scan){
        scan = null;

        try{
            File inventory = new File("inventory.txt");
            scan = new Scanner(inventory);
            int index = 0;
            System.out.println("Current items in inventory:");
            while(scan.hasNextLine()){
                int amount;
                String fileText = scan.nextLine();
                //Amount holds the ingredients inventory which
                //always comes after "= " as an integer value.
                amount = Integer.parseInt(fileText.substring(fileText.indexOf('=') + 2));
                //Prints ingredient amount remaining.
                System.out.println(amount);
                amountVals[index] = amount;
                index++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File does not exist");
        }
        return amountVals;
    }

    /**
     * This method builds the inventory log tp keep track of ingredient
     * inventories. The inventories of each ingredient are adjusted
     * in createOrder when a topping is used.
     *
     * @param newInventory is the same as amountVals.
     */
    public static void inventoryWriter(int[] newInventory){

            FileWriter output = null;
            try{
                File inventory = new File("inventory.txt");
                output = new FileWriter(inventory);
                output.write("Black Coffee = " + newInventory[0] + "\n");
                output.write("Milk = " + newInventory[1]+ "\n");
                output.write("HotWater = " + newInventory[2]+ "\n");
                output.write("Espresso = " + newInventory[3]+ "\n");
                output.write("Sugar = " + newInventory[4] + "\n");
                output.write("WhippedCream = " + newInventory[5]+ "\n");
                output.close();
            }
            catch(IOException e){
                System.out.println("Error");
            }
            System.out.println("Successfully updated the inventory");
    }

    /**
     * This method amends the text in LogFile to add
     * new receipts from the stack. The updated log holds the date
     * updated and all coffee orders.
     *
     * @param stack is receipts to display order and cost.
     */
    public static void logWriter(Stack<String> stack){
        FileWriter myWriter = null;
        try{
            File logFile = new File("LogFile.txt");
            myWriter = new FileWriter(logFile, true);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write( "\n\nWriting orders from stack " + formatter.format(date) + "\n");
            while(!stack.empty()){
                myWriter.write(stack.pop());
            }
            System.out.println("Successfully updated the log file.");
            System.out.println("Nothing to log stack is empty");
            myWriter.close();
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }

    /**
     * This method creates a new employee object and adds the
     * employee to the arrayList of employees. The employees
     * new ID number is displayed to the user.
     *
     * @param firstName the employee's first name.
     * @param lastName the employee's last name.
     */
    public static void createEmployee(String firstName, String lastName){
            Employee newEmployee = new Employee(firstName, lastName);
            employees.add(newEmployee);
            System.out.println(firstName + " has been added as a new employee!");
            System.out.println(firstName + "'s new employee ID is: " + newEmployee.getEmployeeID());
    }

    /**
     * This method updates the employeeLog file. The date the file was updated,
     * as the text "Name: XXXX, XXXX || ID: XXXXXXXXX" for each employee
     * is added to the text file.
     *
     * @param employees a list of employee fields
     */
    public static void employeeLogWriter(ArrayList<Employee> employees){
        Scanner employeeScan = new Scanner(System.in);
        FileWriter employeeWriter = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        try{
            File employeeLog = new File("EmployeeLog.txt");
            employeeWriter = new FileWriter(employeeLog);
            employeeWriter.write("Cafe employee log || " + formatter.format(date) + "\n");
            for(int i = 0; i < employees.size(); ++i){
                employeeWriter.write("Name: " + employees.get(i).getName() + " || ID: " + employees.get(i).getEmployeeID() + "\n\n");
            }
            employeeWriter.flush();
            employeeWriter.close();
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }

    /**
     * Initializes the integer array amountVals which
     * contains the inventory. The method finds the
     * inventory of ingredients by reading the inventory
     * file and storing the integer always found after a "= "
     * in the text file. If the file is not found an error
     * statement is issued to the user.
     */
    public static void initializeInventory(){
        Scanner scan;
        scan = null;
        try{
            File inventory = new File("inventory.txt");
            scan = new Scanner(inventory);
            // Index corresponds to the ingredients as such
            // index 0 = Black Coffee
            // index 1 = Milk
            // index 2 = Hot Water
            // index 3 = Espresso
            // index 4 = Sugar
            // index 5 = Whipped Cream
            int index = 0;
            // Reads through inventory.txt until no more text
            while(scan.hasNextLine()){
                int amount;
                String fileText = scan.nextLine();
                //Amount holds the ingredients inventory which
                //always comes after "= " as an integer value.
                amount = Integer.parseInt(fileText.substring(fileText.lastIndexOf('=') + 2));
                amountVals[index] = amount;
                index++;
            }
            scan.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Inventory file not found");
        }
    }

    /**
     * This method is used to create the list of employee objects at the beginning
     * of main. The first and last name of an employee is grabbed from the employee log.
     * This file ensures that old employees are kept on the log.
     * */
    public static void initializeEmployees(){
        String first;
        String last;
        int start;
        int end;
        Scanner emp = null;
        try{
            File employeeLog = new File("EmployeeLog.txt");
            emp = new Scanner(employeeLog);
            while(emp.hasNextLine()){
                String fileText = emp.nextLine();
                if(fileText.contains("Name")) {
                    start = fileText.indexOf(',') + 2;
                    end =  fileText.indexOf('|') - 1;
                    first = fileText.substring(start, end);
                    start = fileText.indexOf(':') + 2;
                    end = fileText.indexOf(',');
                    last = fileText.substring(start, end);
                    Employee newEmployee = new Employee(first, last);
                    employees.add(newEmployee);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Employee file not found");
        }
    }

}