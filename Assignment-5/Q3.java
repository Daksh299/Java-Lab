import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {
    private final String studentName;
    private final T marks;
    private final double totalMarks;
    private final double passPercentage;

    public Result(String studentName, T marks, double totalMarks, double passPercentage) {
        this.studentName = Objects.requireNonNull(studentName, "Student name must not be null");
        this.marks = marks;
        if (totalMarks <= 0) {
            throw new IllegalArgumentException("Total marks must be greater than zero");
        }
        this.totalMarks = totalMarks;
        this.passPercentage = passPercentage;
    }

    public Optional<T> getMarks() {
        return Optional.ofNullable(marks);
    }

    public Optional<Double> calculatePercentage() {
        return getMarks().map(m -> (m.doubleValue() / totalMarks) * 100);
    }

    public Optional<Boolean> hasPassed() {
        return calculatePercentage().map(p -> p >= passPercentage);
    }

    public void display() {
        System.out.println("Student: " + studentName);
        getMarks().ifPresentOrElse(
            m -> {
                System.out.println("Marks: " + m + " / " + totalMarks);
                System.out.printf("Percentage: %.2f%%%n", calculatePercentage().get());
                System.out.println("Status: " + (hasPassed().get() ? "Pass" : "Fail"));
            },
            () -> System.out.println("Marks are not available")
        );
        System.out.println();
    }
}

public class Q3 {

    public static void main(String[] args) {
        Result<Integer> r1 = new Result<>("Alice", 78, 100, 40);
        Result<Double> r2 = new Result<>("Bob", 32.5, 100, 40);
        Result<Float> r3 = new Result<>("Charlie", 410.5f, 500, 40);
        Result<Integer> r4 = new Result<>("David", null, 100, 40);

        r1.display();
        r2.display();
        r3.display();
        r4.display();

        try {
            new Result<Integer>(null, 50, 100, 40);
        } catch (NullPointerException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
