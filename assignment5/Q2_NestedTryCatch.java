public class Q2_NestedTryCatch {
    public static void main(String[] args) {
        try {
            System.out.println("Outer try started");

            try {
                int a = 10 / 0;
                System.out.println(a);
            } catch (ArithmeticException e) {
                System.out.println("Inner catch: Division by zero");
            }

            try {
                int[] arr = {1, 2, 3};
                System.out.println(arr[5]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: Array index out of bounds");
            }

            String s = null;
            System.out.println(s.length());

        } catch (NullPointerException e) {
            System.out.println("Outer catch: Null pointer exception");
        }

        System.out.println("Program completed");
    }
}
