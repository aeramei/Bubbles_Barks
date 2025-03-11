import objects.Customer;
import objects.ServiceAndPrice;
import objects.PetType;
import objects.ServiceType;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MainMethods {
    Queue<Customer> customers; // Simple FIFO queue using LinkedList
    ServiceAndPrice services;
    Scanner scanner;

    public MainMethods() {
        customers = new LinkedList<>(); // LinkedList as a Queue
        services = new ServiceAndPrice();
        scanner = new Scanner(System.in);
    }

    public void addService() {
        System.out.println("===================================");
        System.out.println("         ADD NEW SERVICE           ");
        System.out.println("===================================");

        // Step 1: Ask for customer name
        String name = "";
        while (true) {
            try {
                System.out.print("Enter customer name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Customer name cannot be empty. Please enter a valid name.");
                }
                break; // Exit the loop if the name is valid
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }

        // Step 2: Ask for contact number
        String contactNumber = "";
        while (true) {
            try {
                System.out.print("Enter contact number: ");
                contactNumber = scanner.nextLine().trim();
                if (contactNumber.isEmpty()) {
                    throw new IllegalArgumentException("Contact number cannot be empty. Please enter a valid number.");
                }
                // Check if the contact number contains only digits
                if (!contactNumber.matches("\\d+")) {
                    throw new IllegalArgumentException("Contact number must contain only digits. Please enter a valid number.");
                }
                break; // Exit the loop if the contact number is valid
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }

        // Step 3: Ask for pet type
        PetType petType = null;
        while (true) {
            try {
                System.out.println("Available Pet Types:");
                for (PetType type : PetType.values()) {
                    System.out.println("- " + type);
                }
                System.out.print("Enter pet type: ");
                String petTypeInput = scanner.nextLine().trim().toUpperCase();
                petType = PetType.valueOf(petTypeInput); // Convert input to PetType enum
                break; // Exit the loop if the pet type is valid
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: Invalid pet type. Please choose from the available options.");
            }
        }

        // Step 4: Ask for service type
        ServiceType serviceType = null;
        while (true) {
            try {
                System.out.println("Available Services:");
                ServiceType[] serviceTypes = ServiceType.values();
                for (int i = 0; i < serviceTypes.length; i++) {
                    System.out.println((i + 1) + ". " + serviceTypes[i] + " ($" + services.getPrice(serviceTypes[i]) + ")");
                }
                System.out.print("Enter the number corresponding to the service type: ");
                int serviceTypeNumber = Integer.parseInt(scanner.nextLine().trim());

                // Validate the service type number
                if (serviceTypeNumber < 1 || serviceTypeNumber > serviceTypes.length) {
                    throw new IllegalArgumentException("Invalid service type number. Please choose a number between 1 and " + serviceTypes.length + ".");
                }

                // Map the number to the corresponding ServiceType
                serviceType = serviceTypes[serviceTypeNumber - 1];
                break; // Exit the loop if the service type is valid
            } catch (NumberFormatException e) {
                System.out.println("\nError: Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }

        // Step 5: Create and add the customer to the queue
        Customer customer = new Customer(name, contactNumber, petType, serviceType);
        customers.add(customer); // Add to the queue (FIFO)

        // Step 6: Display success message and customer details
        System.out.println("\n===================================");
        System.out.println("     Now, service added successfully    ");
        System.out.println("===================================");
        customer.displayInfo();
        System.out.println("===================================");

        // Step 7: Show how many customers are in front of the newly added customer
        int position = ((LinkedList<Customer>) customers).indexOf(customer); // Get position in the queue
        System.out.println("\nNumber of customers in front of you: " + position);
        System.out.println("===================================");

        // Step 8: Show the updated queue list
        showQueueList();
    }

    public void showQueueList() {
        System.out.println("===================================");
        System.out.println("       CUSTOMER QUEUE LIST         ");
        System.out.println("===================================");

        if (customers.isEmpty()) {
            System.out.println("No customers in the queue.");
        } else {
            int count = 1;
            for (Customer customer : customers) {
                System.out.println("Customer #" + count + ":");
                customer.displayInfo();
                System.out.println("-----------------------------------");
                count++;
            }
        }

        System.out.println("===================================");
    }

    public void updateService() {
        try {
            System.out.println("===================================");
            System.out.println("         UPDATE SERVICE            ");
            System.out.println("===================================");

            // Step 1: Ask for customer phone number
            String contactNumber = "";
            while (true) {
                try {
                    System.out.print("Enter the contact number of the customer to update: ");
                    contactNumber = scanner.nextLine().trim();
                    if (contactNumber.isEmpty()) {
                        throw new IllegalArgumentException("Contact number cannot be empty. Please enter a valid number.");
                    }
                    break; // Exit the loop if the contact number is valid
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError: " + e.getMessage());
                }
            }

            // Step 2: Search for the customer in the queue by phone number
            Customer customerToUpdate = null;
            for (Customer customer : customers) {
                if (customer.contactNumber.equals(contactNumber)) {
                    customerToUpdate = customer;
                    break;
                }
            }

            if (customerToUpdate == null) {
                System.out.println("\nCustomer not found in the queue.");
            } else {
                // Step 3: Display current service information
                System.out.println("\nCurrent Service Information:");
                customerToUpdate.displayInfo();

                // Step 4: Ask for the new service type
                ServiceType newServiceType = null;
                while (true) {
                    try {
                        System.out.println("\nAvailable Services:");
                        ServiceType[] serviceTypes = ServiceType.values();
                        for (int i = 0; i < serviceTypes.length; i++) {
                            System.out.println((i + 1) + ". " + serviceTypes[i] + " ($" + services.getPrice(serviceTypes[i]) + ")");
                        }
                        System.out.print("Enter the number corresponding to the new service type: ");
                        int serviceTypeNumber = Integer.parseInt(scanner.nextLine().trim());

                        // Validate the service type number
                        if (serviceTypeNumber < 1 || serviceTypeNumber > serviceTypes.length) {
                            throw new IllegalArgumentException("Invalid service type number. Please choose a number between 1 and " + serviceTypes.length + ".");
                        }

                        // Map the number to the corresponding ServiceType
                        newServiceType = serviceTypes[serviceTypeNumber - 1];
                        break; // Exit the loop if the service type is valid
                    } catch (NumberFormatException e) {
                        System.out.println("\nError: Invalid input. Please enter a number.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nError: " + e.getMessage());
                    }
                }

                // Step 5: Update the customer's service type
                customerToUpdate.serviceType = newServiceType;

                // Step 6: Output the result
                System.out.println("\n===================================");
                System.out.println("     SERVICE IS UPDATED SUCCESSFULLY FOR CUSTOMER. Hee Hee ");
                System.out.println("===================================");
                customerToUpdate.displayInfo();
                System.out.println("===================================");
            }
        } catch (NullPointerException e) {
            System.out.println("\nError: A null value was encountered.");
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        }
    }

    public void doService() {
        try {
            System.out.println("===================================");
            System.out.println("       PROCESS SERVICE REQUEST     ");
            System.out.println("===================================");

            // Step 1: Check if the Queue is Empty
            if (customers.isEmpty()) {
                System.out.println("No service requests to process. The queue is empty.");
                System.out.println("===================================");
                return;
            }

            // Step 2: Retrieve and remove the first customer in the queue (FIFO)
            Customer customer = customers.poll();

            // Step 3: Process the Request
            System.out.println("\nProcessing service request for:");
            customer.displayInfo();

            // Simulate processing (e.g., performing the service)
            System.out.println("\nService in progress...");
            System.out.println("Service completed successfully!");

            // Step 4: Output the Result
            System.out.println("\n===================================");
            System.out.println("     SERVICE REQUEST COMPLETED     ");
            System.out.println("===================================");
            System.out.println("Customer: " + customer.name);
            System.out.println("Service Type: " + customer.serviceType);
            System.out.println("Price: $" + services.getPrice(customer.serviceType));
            System.out.println("===================================");

        } catch (NullPointerException e) {
            System.out.println("\nError: A null value was encountered while processing the request.");
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        }
    }

    public void sortByPrice() {
        try {
            System.out.println("===================================");
            System.out.println("       SORT SERVICE BY PRICE       ");
            System.out.println("===================================");

            // Step 1: Check if the Queue is Empty
            if (customers.isEmpty()) {
                System.out.println("No service requests to sort. The queue is empty.");
                System.out.println("===================================");
                return;
            }

            // Step 2: Convert the Queue to a List for sorting
            LinkedList<Customer> customerList = new LinkedList<>(customers);

            // Step 3: Sort the List by Price using Bubble Sort
            int n = customerList.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    double price1 = services.getPrice(customerList.get(j).serviceType);
                    double price2 = services.getPrice(customerList.get(j + 1).serviceType);
                    if (price1 > price2) {
                        // Swap customers
                        Customer temp = customerList.get(j);
                        customerList.set(j, customerList.get(j + 1));
                        customerList.set(j + 1, temp);
                    }
                }
            }

            // Step 4: Display the Sorted List
            System.out.println("\nSorted Service Requests by Price (Lowest to Highest):");
            int count = 1;
            for (Customer customer : customerList) {
                System.out.println("Customer #" + count + ":");
                customer.displayInfo();
                System.out.println("Price: $" + services.getPrice(customer.serviceType));
                System.out.println("-----------------------------------");
                count++;
            }

            System.out.println("===================================");

        } catch (NullPointerException e) {
            System.out.println("\nError: A null value was encountered while sorting the requests.");
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        }
    }

    public void searchCustomer() {
        try {
            System.out.println("===================================");
            System.out.println("         SEARCH CUSTOMER           ");
            System.out.println("===================================");

            // Step 1: Check if the Queue is Empty
            if (customers.isEmpty()) {
                System.out.println("No customers in the queue to search.");
                System.out.println("===================================");
                return;
            }

            // Step 2: Ask for customer phone number
            String contactNumber = "";
            while (true) {
                try {
                    System.out.print("Enter the contact number of the customer to search: ");
                    contactNumber = scanner.nextLine().trim();
                    if (contactNumber.isEmpty()) {
                        throw new IllegalArgumentException("Contact number cannot be empty. Please enter a valid number.");
                    }
                    break; // Exit the loop if the contact number is valid
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError: " + e.getMessage());
                }
            }

            // Step 3: Perform Linear Search by phone number
            boolean found = false;
            for (Customer customer : customers) {
                if (customer.contactNumber.equals(contactNumber)) {
                    // Step 4: Output the Result (Customer Found)
                    System.out.println("\nCustomer Found:");
                    customer.displayInfo();
                    found = true;
                    break;
                }
            }

            // Step 4: Output the Result (Customer Not Found)
            if (!found) {
                System.out.println("\nCustomer not found in the queue.");
            }

            System.out.println("===================================");

        } catch (NullPointerException e) {
            System.out.println("\nError: A null value was encountered while searching.");
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        }
    }
}
