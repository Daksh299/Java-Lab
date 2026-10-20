import java.util.Objects;
import java.util.Optional;

class Utility {

    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5 {

    public static void main(String[] args) {
        System.out.println("isEqual(\"java\", \"java\"): " + Utility.isEqual("java", "java"));
        System.out.println("isEqual(10, 20): " + Utility.isEqual(10, 20));
        System.out.println("isEqual(3.5, 3.5): " + Utility.isEqual(3.5, 3.5));
        System.out.println("isEqual(null, null): " + Utility.isEqual(null, null));
        System.out.println();

        Optional<String> name = Optional.of("Alice");
        Optional<String> noName = Optional.empty();
        Optional<Integer> noNumber = Optional.empty();
        System.out.println("getDefault(present): " + Utility.getDefault(name, "Unknown"));
        System.out.println("getDefault(empty string): " + Utility.getDefault(noName, "Unknown"));
        System.out.println("getDefault(empty integer): " + Utility.getDefault(noNumber, 0));
        System.out.println();

        System.out.println("max(\"apple\", \"banana\"): " + Utility.max("apple", "banana"));
        System.out.println("max(25, 17): " + Utility.max(25, 17));
        System.out.println("max(4.2, 9.8): " + Utility.max(4.2, 9.8));
        System.out.println("max('a', 'z'): " + Utility.max('a', 'z'));
        System.out.println();

        String text = null;
        System.out.println("isNull(null): " + Utility.isNull(text));
        System.out.println("isNull(\"hello\"): " + Utility.isNull("hello"));
        System.out.println("isNull(100): " + Utility.isNull(100));
    }
}
