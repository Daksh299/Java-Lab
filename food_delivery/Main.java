package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.service.OrderService;
import food.utility.OrderUtility;

public class Main {

    public static void main(String[] args) {

        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(101, "rakesh", 500);
        orders[1] = new PremiumOrder(102, "amit", 1000);
        orders[2] = new RegularOrder(103, "neha", 750);
        orders[3] = new PremiumOrder(104, "suraj", 1500);
        orders[4] = new RegularOrder(105, "joshua", 900);
        orders[5] = new PremiumOrder(106, "steve", 2000);

        System.out.println("Restaurant: " + FoodOrder.getRestaurantName());
        System.out.println();

        for (FoodOrder order : orders) {

            if (OrderUtility.validateCustomerName(order.getCustomerName())
                    && OrderUtility.validateAmount(order.getAmount())) {

                System.out.println(OrderUtility.generateOrderSummary(order));
                OrderService.displayBill(order);

            } else {
                System.out.println("Invalid order details for Order ID: "
                        + order.getOrderId());
            }
        }

        FoodOrder.displayTotalOrders();
    }
}
