import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

public class Q4_StudentResult {
    static void checkMarks(int marks) throws InvalidMarksException {
        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException("Marks must be between 0 and 100");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter marks for subject 1: ");
            int m1 = Integer.parseInt(sc.nextLine());
            checkMarks(m1);

            System.out.print("Enter marks for subject 2: ");
            int m2 = Integer.parseInt(sc.nextLine());
            checkMarks(m2);

            System.out.print("Enter marks for subject 3: ");
            int m3 = Integer.parseInt(sc.nextLine());
            checkMarks(m3);

            int total = m1 + m2 + m3;
            double percentage = total / 3.0;
            String grade;

            if (percentage >= 90) {
                grade = "A+";
            } else if (percentage >= 80) {
                grade = "A";
            } else if (percentage >= 70) {
                grade = "B";
            } else if (percentage >= 60) {
                grade = "C";
            } else if (percentage >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }

            System.out.println("Total = " + total);
            System.out.println("Percentage = " + percentage);
            System.out.println("Grade = " + grade);

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid integer marks");
        }

        sc.close();
    }
}
