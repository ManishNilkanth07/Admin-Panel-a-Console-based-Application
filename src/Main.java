import Admin.Admin;
import Validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void createUser(Scanner scanner, Admin adminService) {
        String name, email;
        List<String> mobileNumbers;

        System.out.print("Enter Name: ");
        name = scanner.nextLine();

        // Validate Email
        while (true) {
            System.out.print("Enter valid Email: ");
            email = scanner.nextLine();

            if(adminService.isUserPresentByEmail(email))
            {
                System.out.println("User is already exist with this email. Try another one.");
            }
            else {
                if (Validator.isValidEmail(email)) {
                    break;
                }
                else System.out.println("You have entered an invalid email. Try again.");
            }
        }

        // Validate Mobile Numbers
        while (true) {
            System.out.print("Enter valid Mobile Numbers (comma-separated): ");
            mobileNumbers = Arrays.asList(scanner.nextLine().split(","));
            if (Validator.areValidMobileNumbers(mobileNumbers)) {
                break;
            }
            System.out.println("You have entered invalid mobile numbers. Try again.");
        }

        adminService.createUser(name, email, mobileNumbers);
    }


    public static void getUserByEmail(Scanner scanner, Admin adminService)
    {
        System.out.print("Enter Email to Search: ");
        String email = scanner.nextLine();
        adminService.getUserByEmail(email);
    }

    public static void getAllUsers(Admin adminService)
    {
        adminService.getAllUsers();
    }

    public static void updateUserByEmail(Scanner scanner, Admin adminService) {
        String email, newName;
        List<String> newNumbers;

        // Validate Email
        while (true) {
            System.out.print("Enter Email to Update: ");
            email = scanner.nextLine();

            if (Validator.isValidEmail(email)) {
                if(adminService.isUserPresentByEmail(email))
                    break;
                else System.out.println("User is not found with given email. Try again.");
            }
            else System.out.println("You have entered an invalid email. Try again.");
        }

        System.out.print("Enter New Name: ");
        newName = scanner.nextLine();

        // Validate Mobile Numbers
        while (true) {
            System.out.print("Enter New Mobile Numbers (comma-separated): ");
            newNumbers = Arrays.asList(scanner.nextLine().split(","));
            if (Validator.areValidMobileNumbers(newNumbers)) {
                break;
            }
            System.out.println("You have entered invalid mobile numbers. Try again.");
        }

        adminService.updateUserByEmail(email, newName, newNumbers);
        System.out.println("User updated successfully.");
    }


    public static void deleteUserByEmail(Scanner scanner, Admin adminService)
    {
        System.out.print("Enter Email to Delete: ");
        String email = scanner.nextLine();
        adminService.deleteUserByEmail(email);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Admin adminService = new Admin();

        while (true) {
            System.out.println("\n===== Admin Panel =====");
            System.out.println("Press 1 to Create User");
            System.out.println("Press 2 to Fetch User by Email");
            System.out.println("Press 3 to Fetch All Users");
            System.out.println("Press 4 to Update User");
            System.out.println("Press 5 to Delete User");
            System.out.println("Press 6 to Exit");
            System.out.print("\n Choose an option: \n");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser(scanner,adminService);

                case 2 -> getUserByEmail(scanner,adminService);

                case 3 -> getAllUsers(adminService);

                case 4 -> updateUserByEmail(scanner, adminService);

                case 5 -> deleteUserByEmail(scanner, adminService);

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