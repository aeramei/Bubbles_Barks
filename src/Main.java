import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainMethods mainMethods = new MainMethods();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display the menu
            System.out.println("===================================");
            System.out.println("     Bubbles & Barks - Main Menu    ");
            System.out.println("===================================");
            System.out.println("1. Add a new service request");
            System.out.println("2. Show the current queue list");
            System.out.println("3. Update a service request");
            System.out.println("4. Process the highest-priority service request");
            System.out.println("5. Sort service requests by price");
            System.out.println("6. Search for a customer by phone number");
            System.out.println("7. Exit");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");

            // Read the user's choice
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input! Please enter a number between 1 and 7.");
                choice = 0; // Reset choice to 0 to re-prompt the user
                continue;
            }

            // Perform the selected operation
            switch (choice) {
                case 1:
                    mainMethods.addService();
                    break;
                case 2:
                    mainMethods.showQueueList();
                    break;
                case 3:
                    mainMethods.updateService();
                    break;
                case 4:
                    mainMethods.doService();
                    break;
                case 5:
                    mainMethods.sortByPrice();
                    break;
                case 6:
                    mainMethods.searchCustomer();
                    break;
                case 7:
                    System.out.println("\nExiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please enter a number between 1 and 7.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
