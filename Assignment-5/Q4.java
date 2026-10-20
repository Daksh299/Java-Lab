import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        LocalDate eventDate;
        LocalTime startTime;

        try {
            System.out.print("Enter event date (yyyy-MM-dd): ");
            eventDate = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Enter start time (HH:mm): ");
            startTime = LocalTime.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format");
            scanner.close();
            return;
        }

        System.out.print("Enter venue (press Enter to skip): ");
        String venueInput = scanner.nextLine().trim();
        Optional<String> venue = venueInput.isEmpty() ? Optional.empty() : Optional.of(venueInput);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

        LocalDate today = LocalDate.now();
        LocalTime endTime = startTime.plusHours(2);

        System.out.println();
        System.out.println("Event Details");
        System.out.println("Name: " + eventName);
        System.out.println("Date: " + eventDate.format(dateFormat));
        System.out.println("Start Time: " + startTime.format(timeFormat));
        System.out.println("End Time: " + endTime.format(timeFormat));
        System.out.println("Venue: " + venue.orElse("Venue not specified"));

        if (eventDate.isAfter(today)) {
            long daysRemaining = ChronoUnit.DAYS.between(today, eventDate);
            System.out.println("Status: Event is in the future");
            System.out.println("Days remaining: " + daysRemaining);
        } else if (eventDate.isEqual(today)) {
            System.out.println("Status: Event is today");
            System.out.println("Days remaining: 0");
        } else {
            System.out.println("Status: Event is in the past");
            System.out.println("Days since event: " + ChronoUnit.DAYS.between(eventDate, today));
        }

        scanner.close();
    }
}
