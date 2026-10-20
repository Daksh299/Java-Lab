import java.util.Optional;

public class Q2 {

    public static void main(String[] args) {
        Optional<String> email = Optional.of("student@university.edu");
        Optional<String> emptyEmail = Optional.empty();

        System.out.print("Email using ifPresent: ");
        email.ifPresent(System.out::println);

        System.out.print("Empty optional using ifPresent: ");
        emptyEmail.ifPresent(System.out::println);
        System.out.println();

        String defaultEmail = emptyEmail.orElse("noemail@university.edu");
        System.out.println("Email using orElse: " + defaultEmail);

        System.out.println("Existing email using orElseThrow: " + email.orElseThrow());

        try {
            String value = emptyEmail.orElseThrow(() -> new IllegalStateException("Email address is missing"));
            System.out.println(value);
        } catch (IllegalStateException e) {
            System.out.println("Exception handled: " + e.getMessage());
        }
    }
}
