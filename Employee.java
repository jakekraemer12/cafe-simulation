/**
 * Employee is used to create a new employee object that holds a first name,
 * last name and employeeID. An employee object will be created when an employee's
 * information is needed.
 */
public class Employee {
    /**
     * The unique employee ID that is used to get employee discount.
     */
    private String employeeID;
    /**
     * Holds first name of the employee.
     */
    private String firstName;
    /**
     * Holds last name of the employee.
     */
    private String lastName;
    /**
     * Count is iterated to create unique ID
     */
    private static int count = 1;

    /**
     * Constructor initializing an employee's first and last name.
     * Employee initializes the employee's ID using the first letter of the employees first name,
     * their full last name, and count formatted to print 3 digits.
     *
     * @param firstName first name of employee
     * @param lastName last name of employee
     */
    public Employee(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        String ID = firstName.charAt(0) + lastName +  String.format("%03d", count);
        employeeID = ID.toLowerCase();
        ++count;
    }

    /**
     * Returns the employee's unique ID text. The text is
     * formatted "firstInitial + last name + XXX" where
     * XXX is a simple iterated count.
     *
     * @return employeeID the employee's unique ID.
     */
    public String getEmployeeID(){
        return employeeID;
    }

    /**
     * Returns the employee's name formatted "last, first"
     *
     * @return lastName + ", " + firstName
     */
    public String getName(){
        return lastName + ", " + firstName;
    }

}
