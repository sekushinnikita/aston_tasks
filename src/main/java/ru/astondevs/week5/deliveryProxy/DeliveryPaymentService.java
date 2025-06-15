package ru.astondevs.week5.deliveryProxy;

import ru.astondevs.week5.PaymentService;

class DeliveryPaymentService implements PaymentService {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Product payment " + orderId + " $ " + amount);
    }
}
