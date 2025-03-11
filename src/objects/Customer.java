package objects;

public class Customer implements Comparable<Customer> {
    public String name;
    public String contactNumber;
    public PetType petType;
    public ServiceType serviceType;

    public Customer(String name, String contactNumber, PetType petType, ServiceType serviceType) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.petType = petType;
        this.serviceType = serviceType;
    }

    // Method to display customer information
    public void displayInfo() {
        System.out.println("===================================");
        System.out.println("        CUSTOMER INFORMATION       ");
        System.out.println("===================================");
        System.out.println("Name:            " + name);
        System.out.println("Contact Number:  " + contactNumber);
        System.out.println("Pet Type:        " + petType);
        System.out.println("Service Type:    " + serviceType);
        System.out.println("===================================");
    }

    // Method to display a voucher for the service
    public void displayVoucher(ServiceAndPrice serviceAndPrice) {
        System.out.println("\n===================================");
        System.out.println("           SERVICE VOUCHER         ");
        System.out.println("===================================");
        System.out.println("Customer Name:   " + name);
        System.out.println("Pet Type:        " + petType);
        System.out.println("Service:         " + serviceType);
        System.out.println("Price:          $" + serviceAndPrice.getPrice(serviceType));
        System.out.println("-----------------------------------");
        System.out.println("Thank you for choosing our service!");
        System.out.println("===================================");
    }

    // Implement the Comparable interface to define natural ordering
    @Override
    public int compareTo(Customer other) {
        // Compare customers based on service price (lower price has higher priority)
        double thisPrice = ServiceAndPrice.getPrice(this.serviceType);
        double otherPrice = ServiceAndPrice.getPrice(other.serviceType);
        return Double.compare(thisPrice, otherPrice);
    }
}
