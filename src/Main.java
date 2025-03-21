import Admin.Admin;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Admin adminService = new Admin();

        while (true) {
            System.out.println("\n===== Admin Panel =====");
            System.out.println("1. Create User");
            System.out.println("2. Fetch User by Email");
            System.out.println("3. Fetch All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Mobile Numbers (comma-separated): ");
                    List<String> mobileNumbers = Arrays.asList(scanner.nextLine().split(","));
                    adminService.createUser(name, email, mobileNumbers);
                }
                case 2 -> {
                    System.out.print("Enter Email to Search: ");
                    String email = scanner.nextLine();
                    adminService.getUserByEmail(email);
                }
                case 3 -> {
                    adminService.getAllUsers();

                }
                case 4 -> {
                    System.out.print("Enter Email to Update: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Mobile Numbers (comma-separated): ");
                    List<String> newNumbers = Arrays.asList(scanner.nextLine().split(","));
                    adminService.updateUserByEmail(email, newName, newNumbers);
                }
                case 5 -> {
                    System.out.print("Enter Email to Delete: ");
                    String email = scanner.nextLine();
                    adminService.deleteUserByEmail(email);
                }
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        }

    }
}