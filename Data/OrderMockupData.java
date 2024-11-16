package Data;

import Models.Order;

import java.util.List;

public class OrderMockupData {
    public static Order[] orderData = {
            new Order(1, List.of(1, 2, 3), 1),
            new Order(2, List.of(4, 5, 6), 2),
    };
}
