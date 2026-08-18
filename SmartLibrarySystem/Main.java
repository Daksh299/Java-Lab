package library.main;

import library.model.Book;
import library.model.DigitalResource;
import library.model.LibraryResource;
import library.service.LibraryService;
import library.util.InputValidator;

public class Main {

    public static void main(String[] args) {

        LibraryResource[] resources = new LibraryResource[5];

        resources[0] = new Book(101, "Java Programming", "James Gosling");
        resources[1] = new Book(102, "Data Structures", "Mark Allen");
        resources[2] = new DigitalResource(103, "Java Tutorial", "Oracle");
        resources[3] = new DigitalResource(104, "Database Course", "John Smith");
        resources[4] = new Book(105, "Operating Systems", "Abraham Silberschatz");

        int[] overdueDays = {4, 0, 6, 3, 5};

        System.out.println("Library Name: " + LibraryResource.getLibraryName());
        System.out.println();

        double totalFine = 0;

        for (int i = 0; i < resources.length; i++) {

            if (!InputValidator.validateResourceId(resources[i].getResourceId())) {
                System.out.println("Invalid Resource ID.");
                continue;
            }

            if (!InputValidator.validateFineDays(overdueDays[i])) {
                System.out.println("Invalid overdue days.");
                continue;
            }

            resources[i].printDetails();

            double fine = resources[i].calculateFine(overdueDays[i]);

            System.out.println("Overdue Days: " + overdueDays[i]);
            System.out.println("Fine        : Rs. " + fine);
            System.out.println("----------------------------------");

            totalFine += fine;
        }

        System.out.println("Total Fine of All Resources: Rs. " + totalFine);
        System.out.println();

        // The service class can also calculate the total fine.
        double serviceTotal = LibraryService.calculateTotalFine(resources, overdueDays);
        System.out.println("Total Fine using LibraryService: Rs. " + serviceTotal);

        LibraryResource.displayTotalResources();
    }
}
