package RR;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Initialize Hibernate SessionFactory (you can use a framework like Spring to manage this)
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        MobileApplicationDAO mobileAppDAO = new MobileApplicationDAO(sessionFactory);

        while (true) {
            System.out.println("\n=== Mobile Application CRUD Operations ===");
            System.out.println("1. Add New Mobile Application");
            System.out.println("2. View All Mobile Applications");
            System.out.println("3. Update Mobile Application Version");
            System.out.println("4. Delete Mobile Application");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addNewMobileApplication(scanner, mobileAppDAO);
                    break;
                case 2:
                    viewAllMobileApplications(mobileAppDAO);
                    break;
                case 3:
                    updateMobileApplicationVersion(scanner, mobileAppDAO);
                    break;
                case 4:
                    deleteMobileApplication(scanner, mobileAppDAO);
                    break;
                case 5:
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

    private static void viewAllMobileApplications(MobileApplicationDAO mobileAppDAO) 
    {
        System.out.println("\n=== View All Mobile Applications ===");
        List<MobileApplication> allApplications = mobileAppDAO.getAllMobileApplications();

        if (!allApplications.isEmpty()) 
        {
            System.out.println("All Mobile Applications:");
            for (MobileApplication app : allApplications) 
            {
                System.out.println(app);
            }
        } 
        else 
        {
            System.out.println("No mobile applications found.");
        }
    }

    private static void updateMobileApplicationVersion(Scanner scanner, MobileApplicationDAO mobileAppDAO) {
        System.out.println("\n=== Update Mobile Application Version ===");
        System.out.print("Enter application ID to update: ");
        Long appId = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        MobileApplication appToUpdate = mobileAppDAO.getMobileApplicationById(appId);

        if (appToUpdate != null) {
            System.out.print("Enter new version: ");
            String newVersion = scanner.nextLine();

            appToUpdate.setVersion(newVersion);
            mobileAppDAO.saveOrUpdateMobileApplication(appToUpdate);
            System.out.println("Mobile application updated successfully!");
        } else {
            System.out.println("Mobile application not found with ID: " + appId);
        }
    }

    private static void deleteMobileApplication(Scanner scanner, MobileApplicationDAO mobileAppDAO) 
    {
        System.out.println("\n=== Delete Mobile Application ===");
        System.out.print("Enter application ID to delete: ");
        Long appId = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        MobileApplication appToDelete = mobileAppDAO.getMobileApplicationById(appId);

        if (appToDelete != null) 
        {
            mobileAppDAO.deleteMobileApplication(appId);
            System.out.println("Mobile application deleted successfully!");
        } 
        else 
        {
            System.out.println("Mobile application not found with ID: " + appId);
        }
    }
}
