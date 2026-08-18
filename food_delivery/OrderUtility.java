package food.utility;

import food.model.FoodOrder;

public class OrderUtility {

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String generateOrderSummary(FoodOrder order) {
        return "Order ID: " + order.getOrderId()
                + ", Customer: " + order.getCustomerName()
                + ", Amount: Rs. " + order.getAmount();
    }
}
