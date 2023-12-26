package RR;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize Hibernate SessionFactory
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        MobileApplicationDAO mobileAppDAO = new MobileApplicationDAO(sessionFactory);
        UserDAO userDAO = new UserDAO(sessionFactory);

        while (true) {
            System.out.println("\n=== CRUD Operations ===");
            System.out.println("1. Add New Mobile Application");
            System.out.println("2. View All Mobile Applications");
            System.out.println("3. Update Mobile Application Version");
            System.out.println("4. Delete Mobile Application");
            System.out.println("5. Add New User");
            System.out.println("6. View All Users");
            System.out.println("7. Update User Password");
            System.out.println("8. Delete User");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput(scanner);

            switch (choice) {
                case 1:
                    addNewMobileApplication(scanner, mobileAppDAO);
                    break;
                case 2:
                    viewAllMobileApplications(mobileAppDAO);
                    break;
                case 3:
                	updateMobileApplication(scanner, mobileAppDAO);
                    break;
                case 4:
                    deleteMobileApplication(scanner, mobileAppDAO);
                    break;
                case 5:
                    addNewUser(scanner, userDAO);
                    break;
                case 6:
                    viewAllUsers(userDAO);
                    break;
                case 7:
                	updateUserData(scanner, userDAO);
                    break;
                case 8:
                    deleteUser(scanner, userDAO);
                    break;
                case 9:
                    sessionFactory.close();
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewMobileApplication(Scanner scanner, MobileApplicationDAO mobileAppDAO) {
        System.out.println("\n=== Add New Mobile Application ===");
        MobileApplication newApp = new MobileApplication();

        System.out.print("Enter application name: ");
        newApp.setName(scanner.nextLine());

        System.out.print("Enter developer name: ");
        newApp.setDeveloper(scanner.nextLine());

        System.out.print("Enter application version: ");
        newApp.setVersion(scanner.nextLine());

        mobileAppDAO.saveOrUpdateMobileApplication(newApp);
        System.out.println("Mobile application added successfully!");
    }

    private static void viewAllMobileApplications(MobileApplicationDAO mobileAppDAO) {
        System.out.println("\n=== View All Mobile Applications ===");
        List<MobileApplication> allApplications = mobileAppDAO.getAllMobileApplications();

        if (!allApplications.isEmpty()) {
            System.out.println("All Mobile Applications:");
            for (MobileApplication app : allApplications) {
                System.out.println(app);
            }
        } else {
            System.out.println("No mobile applications found.");
        }
    }

    private static void updateMobileApplication(Scanner scanner, MobileApplicationDAO mobileAppDAO) {
        System.out.println("\n=== Update Mobile Application ===");
        System.out.print("Enter application ID to update: ");
        Long appId = getValidLongInput(scanner);

        MobileApplication appToUpdate = mobileAppDAO.getMobileApplicationById(appId);

        if (appToUpdate != null) {
            System.out.print("Enter new name (press Enter to keep the current name): ");
            String newName = scanner.nextLine();

            System.out.print("Enter new developer (press Enter to keep the current developer): ");
            String newDeveloper = scanner.nextLine();

            System.out.print("Enter new version (press Enter to keep the current version): ");
            String newVersion = scanner.nextLine();

            if (!newName.isEmpty()) {
                appToUpdate.setName(newName);
            }

            if (!newDeveloper.isEmpty()) {
                appToUpdate.setDeveloper(newDeveloper);
            }

            if (!newVersion.isEmpty()) {
                appToUpdate.setVersion(newVersion);
            }

            mobileAppDAO.saveOrUpdateMobileApplication(appToUpdate);
            System.out.println("Mobile application updated successfully!");
        } else {
            System.out.println("Mobile application not found with ID: " + appId);
        }
    }



    private static void deleteMobileApplication(Scanner scanner, MobileApplicationDAO mobileAppDAO) {
        System.out.println("\n=== Delete Mobile Application ===");
        System.out.print("Enter application ID to delete: ");
        Long appId = getValidLongInput(scanner);

        MobileApplication appToDelete = mobileAppDAO.getMobileApplicationById(appId);

        if (appToDelete != null) {
            mobileAppDAO.deleteMobileApplication(appId);
            System.out.println("Mobile application deleted successfully!");
        } else {
            System.out.println("Mobile application not found with ID: " + appId);
        }
    }

    private static void addNewUser(Scanner scanner, UserDAO userDAO) {
        System.out.println("\n=== Add New User ===");
        User newUser = new User();

        System.out.print("Enter username: ");
        newUser.setUsername(scanner.nextLine());

        System.out.print("Enter email: ");
        newUser.setEmail(scanner.nextLine());

        System.out.print("Enter password: ");
        newUser.setPassword(scanner.nextLine());

        userDAO.saveOrUpdateUser(newUser);
        System.out.println("User added successfully!");
    }

    private static void viewAllUsers(UserDAO userDAO) {
        System.out.println("\n=== View All Users ===");
        List<User> allUsers = userDAO.getAllUsers();

        if (!allUsers.isEmpty()) {
            System.out.println("All Users:");
            for (User user : allUsers) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found.");
        }
    }

    private static void updateUserData(Scanner scanner, UserDAO userDAO) {
        System.out.println("\n=== Update User Data ===");
        System.out.print("Enter user ID to update: ");
        Long userId = getValidLongInput(scanner);

        User userToUpdate = userDAO.getUserById(userId);

        if (userToUpdate != null) {
            System.out.print("Enter new username (press Enter to keep the current username): ");
            String newUsername = scanner.nextLine();

            System.out.print("Enter new email (press Enter to keep the current email): ");
            String newEmail = scanner.nextLine();

            System.out.print("Enter new password (press Enter to keep the current password): ");
            String newPassword = scanner.nextLine();

            if (!newUsername.isEmpty()) {
                userToUpdate.setUsername(newUsername);
            }

            if (!newEmail.isEmpty()) {
                userToUpdate.setEmail(newEmail);
            }

            if (!newPassword.isEmpty()) {
                userToUpdate.setPassword(newPassword);
            }

            userDAO.saveOrUpdateUser(userToUpdate);
            System.out.println("User data updated successfully!");
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }


    private static void deleteUser(Scanner scanner, UserDAO userDAO) {
        System.out.println("\n=== Delete User ===");
        System.out.print("Enter user ID to delete: ");
        Long userId = getValidLongInput(scanner);

        User userToDelete = userDAO.getUserById(userId);

        if (userToDelete != null) {
            userDAO.deleteUser(userId);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }

    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static Long getValidLongInput(Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
