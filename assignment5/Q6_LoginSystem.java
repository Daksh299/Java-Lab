import java.util.Scanner;

class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

public class Q6_LoginSystem {
    static String correctUsername = "admin";
    static String correctPassword = "1234";
    static int attempts = 0;

    static void login(String username, String password)
            throws InvalidUsernameException, InvalidPasswordException, AccountLockedException {

        if (attempts >= 3) {
            throw new AccountLockedException("Account is locked");
        }

        if (!username.equals(correctUsername)) {
            attempts++;
            throw new InvalidUsernameException("Invalid username");
        }

        if (!password.equals(correctPassword)) {
            attempts++;
            throw new InvalidPasswordException("Invalid password");
        }

        attempts = 0;
        System.out.println("Login successful");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 4; i++) {
            try {
                System.out.print("Enter username: ");
                String username = sc.nextLine();

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                login(username, password);
                break;

            } catch (InvalidUsernameException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidPasswordException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (AccountLockedException e) {
                System.out.println("Error: " + e.getMessage());
                break;
            } finally {
                System.out.println("Login attempt completed");
            }
        }

        sc.close();
    }
}
