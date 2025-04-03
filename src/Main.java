import Admin.AdminServiceImpl;
import Validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main{

    public static boolean goToMainMenu(Scanner scanner) {
        System.out.println("Press 1 for Main Menu or Press 2 to continue");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice == 1;
    }


    public static void createUser(Scanner scanner, AdminServiceImpl adminService) {
        String name, email;
        List<String> mobileNumbers;

        System.out.print("Enter Name: ");
        name = scanner.nextLine();

        // Validate Email
        while (true) {
            System.out.print("Enter valid Email: ");
            email = scanner.nextLine();

            if (adminService.isUserPresentByEmail(email)) {

                System.out.println("\nUser already exists with this email. Try another one.\n");

                if (goToMainMenu(scanner)) {
                    return;
                }
            }
            else {
                if (Validator.isValidEmail(email)) {
                    break;
                } else {
                    System.out.println("You have entered an invalid email. Try again.");
                    if (goToMainMenu(scanner)) {
                        return;
                    }
                }
            }
        }


        while (true) {
            System.out.print("\nEnter valid Mobile Numbers (comma-separated): \n");
            mobileNumbers = Arrays.asList(scanner.nextLine().split(","));
            if (Validator.areValidMobileNumbers(mobileNumbers)) {
                break;
            }
            System.out.println("You have entered invalid mobile numbers. Try again.");
            if (goToMainMenu(scanner)) {
                return;
            }
        }


        adminService.createUser(name, email, mobileNumbers);
    }


    public static void getUserByEmail(Scanner scanner, AdminServiceImpl adminService) {
        System.out.print("Enter Email to Search: ");
        String email = scanner.nextLine();
        adminService.getUserByEmail(email);
    }


    public static void getAllUsers(AdminServiceImpl adminService) {
        adminService.getAllUsers();
    }


    public static void updateUserByEmail(Scanner scanner, AdminServiceImpl adminService) {
        String email;

        while (true) {
            System.out.print("Enter Email to Update: ");
            email = scanner.nextLine();

            if (Validator.isValidEmail(email)) {
                if (adminService.isUserPresentByEmail(email)) {
                    break;
                }
                else {
                    System.out.println("User not found with the given email. Try again.");
                    if (goToMainMenu(scanner)) {
                        return;
                    }
                }
            }
            else {
                System.out.println("You have entered an invalid email. Try again.");
                if (goToMainMenu(scanner)) {
                    return;
                }
            }
        }

        System.out.println("Press 1 to update Name");
        System.out.println("Press 2 to update Mobile Numbers");
        System.out.println("Press 3 to update both");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> updateName(scanner,email,adminService);

            case 2 -> updateNumbers(scanner,email,adminService);

            case 3 -> updateNameAndMobileNumber(scanner,email,adminService);

            default -> System.out.println("Invalid choice! Please select a valid option.");
        }

    }

    private static void updateNameAndMobileNumber(Scanner scanner,String email, AdminServiceImpl adminService) {
       updateName(scanner, email, adminService);
       updateNumbers(scanner,email,adminService);
    }

    private static void updateNumbers(Scanner scanner ,String email, AdminServiceImpl adminService) {
        List<String> newNumbers;

        while (true) {
            System.out.print("Enter New Mobile Numbers (comma-separated): ");
            newNumbers = Arrays.asList(scanner.nextLine().split(","));
            if (Validator.areValidMobileNumbers(newNumbers)) {
                break;
            }
            System.out.println("You have entered invalid mobile numbers. Try again.");
            if (goToMainMenu(scanner)) {
                return;
            }
        }
        adminService.updateUserByEmail(email,null,newNumbers);
    }

    private static void updateName(Scanner scanner ,String email, AdminServiceImpl adminService) {
        System.out.println("Enter New Name to Update :");
        String newName = scanner.nextLine();

        adminService.updateUserByEmail(email,newName,null);
    }


    public static void deleteUserByEmail(Scanner scanner, AdminServiceImpl adminService) {
        System.out.print("Enter Email to Delete: ");
        String email = scanner.nextLine();
        adminService.deleteUserByEmail(email);
        System.out.println("User deleted successfully.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminServiceImpl adminService = new AdminServiceImpl();

        while (true) {
            System.out.println("\n===== Admin Panel =====");
            System.out.println("Press 1 to Create User");
            System.out.println("Press 2 to Fetch User by Email");
            System.out.println("Press 3 to Fetch All Users");
            System.out.println("Press 4 to Update User");
            System.out.println("Press 5 to Delete User");
            System.out.println("Press 6 to Exit");
            System.out.print("\nChoose an option: \n");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1 -> createUser(scanner, adminService);

                case 2 -> getUserByEmail(scanner, adminService);

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
