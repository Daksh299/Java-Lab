package library.model;

public class Book extends LibraryResource implements Printable {

    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 5;
    }

    @Override
    public void printDetails() {
        System.out.println("Type        : Book");
        displayBasicDetails();
        System.out.println("Fine Rate   : Rs. 5/day");
    }
}
