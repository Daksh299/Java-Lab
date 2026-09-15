import java.util.ArrayList;
import java.util.Scanner;

class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}

class ProductException extends ApplicationException {
    public ProductException(String message) {
        super(message);
    }
}

class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class OutOfStockException extends ProductException {
    public OutOfStockException(String message) {
        super(message);
    }
}

class PaymentException extends ApplicationException {
    public PaymentException(String message) {
        super(message);
    }
}

class InvalidPaymentException extends PaymentException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends PaymentException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class OrderException extends ApplicationException {
    public OrderException(String message) {
        super(message);
    }
}

class EmptyCartException extends OrderException {
    public EmptyCartException(String message) {
        super(message);
    }
}

class Product {
    String name;
    double price;
    int stock;

    Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

public class Q7_ShoppingCart {
    static ArrayList<Product> products = new ArrayList<Product>();
    static ArrayList<Product> cart = new ArrayList<Product>();

    static void searchProduct(String name) throws ProductNotFoundException {
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                System.out.println("Product found: " + p.name + " - " + p.price);
                return;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    static void addProduct(String name) throws ProductNotFoundException, OutOfStockException {
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                if (p.stock <= 0) {
                    throw new OutOfStockException("Product is out of stock");
                }
                cart.add(p);
                p.stock--;
                System.out.println("Product added to cart");
                return;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    static void removeProduct(String name) throws ProductNotFoundException {
        for (Product p : cart) {
            if (p.name.equalsIgnoreCase(name)) {
                cart.remove(p);
                p.stock++;
                System.out.println("Product removed from cart");
                return;
            }
        }
        throw new ProductNotFoundException("Product is not in cart");
    }

    static double getTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.price;
        }
        return total;
    }

    static void pay(double amount)
            throws EmptyCartException, InvalidPaymentException, InsufficientFundsException {

        if (cart.size() == 0) {
            throw new EmptyCartException("Cart is empty");
        }

        double total = getTotal();

        if (amount <= 0) {
            throw new InvalidPaymentException("Payment must be positive");
        }

        if (amount < total) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        System.out.println("Payment successful");
        System.out.println("Total = " + total);
        System.out.println("Change = " + (amount - total));
        cart.clear();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        products.add(new Product("Laptop", 50000, 2));
        products.add(new Product("Mouse", 1000, 5));
        products.add(new Product("Keyboard", 2000, 3));

        try {
            searchProduct("Laptop");
            addProduct("Laptop");
            addProduct("Mouse");
            System.out.println("Cart total = " + getTotal());
            removeProduct("Mouse");
            System.out.println("Cart total after removal = " + getTotal());
            pay(60000);
        } catch (ProductNotFoundException e) {
            System.out.println("Product Error: " + e.getMessage());
        } catch (OutOfStockException e) {
            System.out.println("Stock Error: " + e.getMessage());
        } catch (EmptyCartException e) {
            System.out.println("Order Error: " + e.getMessage());
        } catch (InvalidPaymentException e) {
            System.out.println("Payment Error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Payment Error: " + e.getMessage());
        } finally {
            System.out.println("Shopping operation completed");
        }

        sc.close();
    }
}
