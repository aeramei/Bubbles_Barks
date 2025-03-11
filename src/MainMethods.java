import objects.Customer;
import objects.ServiceAndPrice;
import objects.PetType;
import objects.ServiceType;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class MainMethods {
    PriorityQueue<Customer> customers;
    ServiceAndPrice services;
    Scanner scanner;

    public MainMethods() {
        customers = new PriorityQueue<>();
        services = new ServiceAndPrice();
        scanner = new Scanner(System.in);
    }

    public void addService() {
        try {
            // Ask for customer information
            System.out.println("===================================");
            System.out.println("         ADD NEW SERVICE           ");
            System.out.println("===================================");
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter contact number: ");
            String contactNumber = scanner.nextLine().trim();

            System.out.println("Available Pet Types:");
            for (PetType type : PetType.values()) {
                System.out.println("- " + type);
            }
            System.out.print("Enter pet type: ");
            String petTypeInput = scanner.nextLine().trim().toUpperCase();
            PetType petType = PetType.valueOf(petTypeInput);

            System.out.println("Available Services:");
            for (ServiceType type : ServiceType.values()) {
                System.out.println("- " + type + " ($" + services.getPrice(type) + ")");
            }
            System.out.print("Enter service type: ");
            String serviceTypeInput = scanner.nextLine().trim().toUpperCase();
            ServiceType serviceType = ServiceType.valueOf(serviceTypeInput);

            Customer customer = new Customer(name, contactNumber, petType, serviceType);
            customers.add(customer);

            // the result
            System.out.println("\n===================================");
            System.out.println("     Now, service added successfully    ");
            System.out.println("===================================");
            customer.displayInfo();
            System.out.println("===================================");
        } catch (IllegalArgumentException e) {
            System.out.println("\nInvalid input! Please enter a valid pet type or service type.");
        } catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
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

            // Ask for customer information to identify the customer
            System.out.print("Enter the name of the customer to update: ");
            String name = scanner.nextLine().trim();

            // Search for the customer in the queue
            Customer customerToUpdate = null;
            for (Customer customer : customers) {
                if (customer.name.equalsIgnoreCase(name)) {
                    customerToUpdate = customer;
                    break;
                }
            }

            if (customerToUpdate == null) {
                System.out.println("\nCustomer not found in the queue.");
            } else {
                // Display current service information
                System.out.println("\nCurrent Service Information:");
                customerToUpdate.displayInfo();

                // Ask for the new service type
                System.out.println("\nAvailable Services:");
                for (ServiceType type : ServiceType.values()) {
                    System.out.println("- " + type + " ($" + services.getPrice(type) + ")");
                }
                System.out.print("Enter the new service type: ");
                String serviceTypeInput = scanner.nextLine().trim().toUpperCase();
                ServiceType newServiceType = ServiceType.valueOf(serviceTypeInput);

                // Update the customer's service type
                customerToUpdate.serviceType = newServiceType;

                // Output the result
                System.out.println("\n===================================");
                System.out.println("     SERVICE IS UPDATED SUCCESSFULLY FOR CUSTOMER. Hee Hee ");
                System.out.println("===================================");
                customerToUpdate.displayInfo();
                System.out.println("===================================");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nInvalid input! Please enter a valid service type.");
        } catch (NullPointerException e) {
            System.out.println("\nError: A null value was encountered.");
        } catch (ConcurrentModificationException e) {
            System.out.println("\nError: The queue was modified while being iterated.");
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

            // Step 2: Retrieve the Highest-Priority Request
            Customer highestPriorityCustomer = customers.poll(); // Retrieves and removes the highest-priority customer

            // Step 3: Process the Request
            System.out.println("\nProcessing service request for:");
            highestPriorityCustomer.displayInfo();

            // Simulate processing (e.g., performing the service)
            System.out.println("\nService in progress...");
            System.out.println("Service completed successfully!");

            // Step 4: Remove the Processed Request from the Queue
            // Already handled by `poll()` above

            // Step 5: Output the Result
            System.out.println("\n===================================");
            System.out.println("     SERVICE REQUEST COMPLETED     ");
            System.out.println("===================================");
            System.out.println("Customer: " + highestPriorityCustomer.name);
            System.out.println("Service Type: " + highestPriorityCustomer.serviceType);
            System.out.println("Price: $" + services.getPrice(highestPriorityCustomer.serviceType));
            System.out.println("===================================");

        } catch (NullPointerException e) {
            System.out.println("\nError: A null value was encountered while processing the request.");
        } catch (IllegalStateException e) {
            System.out.println("\nError: The queue is in an invalid state for processing.");
        } catch (ConcurrentModificationException e) {
            System.out.println("\nError: The queue was modified while being processed.");
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

            // Retrieve All Requests from the Queue
            List<Customer> customerList = new ArrayList<>(customers);

            // Sort the Requests by Price using Bubble Sort
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

            // Re-add the Sorted Requests to the Queue (Optional)
            customers.clear();
            customers.addAll(customerList);

            // Display the Sorted List
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
        } catch (IllegalStateException e) {
            System.out.println("\nError: The queue is in an invalid state for sorting.");
        } catch (ConcurrentModificationException e) {
            System.out.println("\nError: The queue was modified while being sorted.");
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

            // Step 2: Ask for Required Information
            System.out.print("Enter the name of the customer to search: ");
            String name = scanner.nextLine().trim();

            // Step 3: Perform Linear Search
            boolean found = false;
            for (Customer customer : customers) {
                if (customer.name.equalsIgnoreCase(name)) {
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