import objects.Customer;

public class Main {
    public static void main(String[] args) {

        // Create a customer
        Customer customer = new Customer("Alice Smith", "555-1234");


        // Display information
        System.out.println("objects.Customer Information:");
        customer.displayInfo();
        System.out.println("\nobjects.Pet Information:");
        System.out.println("\nGrooming Service Information:");
    }
}