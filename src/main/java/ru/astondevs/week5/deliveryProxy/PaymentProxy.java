package ru.astondevs.week5.deliveryProxy;

import ru.astondevs.week5.PaymentService;
import java.util.Set;

public class PaymentProxy implements PaymentService {
    private DeliveryPaymentService service;
    private final Set<String> blacklist = Set.of("blocked_user");

    @Override
    public void pay(String orderId, double amount) {
        if (blacklist.contains(orderId.split("-")[0])) {
            System.out.println("Access denied for order: " + orderId);
            return;
        }
        if (service == null) service = new DeliveryPaymentService();
        service.pay(orderId, amount);
    }
}
