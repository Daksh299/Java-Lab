class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class Q5_BankingSystem {
    static BankAccount findAccount(String number) throws AccountNotFoundException {
        if (!number.equals("1001")) {
            throw new AccountNotFoundException("Account not found");
        }
        return new BankAccount("1001", 5000);
    }

    static void makeWithdrawal(String number, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        BankAccount account = findAccount(number);
        account.withdraw(amount);
        System.out.println("Withdrawal successful");
        System.out.println("Balance = " + account.getBalance());
    }

    public static void main(String[] args) {
        try {
            BankAccount account = findAccount("1001");

            account.deposit(1000);
            System.out.println("After deposit = " + account.getBalance());

            makeWithdrawal("1001", 3000);

            account.withdraw(5000);

        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            findAccount("9999");
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
