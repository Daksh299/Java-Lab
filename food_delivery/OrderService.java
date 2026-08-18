package food.service;

import food.model.Discountable;
import food.model.FoodOrder;

public class OrderService {

    public static double getDiscount(FoodOrder order) {
        Discountable discountable = (Discountable) order;
        return discountable.applyDiscount();
    }

    public static double getFinalAmount(FoodOrder order) {
        double discount = getDiscount(order);
        double deliveryCharge = order.calculateDeliveryCharge();

        return order.getAmount() - discount + deliveryCharge;
    }

    public static void displayBill(FoodOrder order) {
        double discount = getDiscount(order);
        double deliveryCharge = order.calculateDeliveryCharge();
        double finalAmount = getFinalAmount(order);

        System.out.println("Order ID        : " + order.getOrderId());
        System.out.println("Customer Name   : " + order.getCustomerName());
        System.out.println("Food Amount     : Rs. " + order.getAmount());
        System.out.println("Discount        : Rs. " + discount);
        System.out.println("Delivery Charge : Rs. " + deliveryCharge);
        System.out.println("Final Payable   : Rs. " + finalAmount);
        System.out.println("----------------------------------");
    }
}
