package objects;

public class Customer {
    public String name;
    public String contactNumber;
    public PetType petType;


    public Customer(String name, String contactNumber, PetType petType) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.petType = petType;
    }

    public void displayInfo() {
        System.out.println("objects.Customer Name: " + name);
        System.out.println("Contact Number: " + contactNumber);
    }
}