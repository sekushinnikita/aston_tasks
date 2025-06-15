package ru.astondevs.week5;

public class DeliveryLoggingPaymentDecorator implements PaymentService {
    private PaymentService wrapped;

    public DeliveryLoggingPaymentDecorator(PaymentService wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Logging: payment attempt " + orderId);
        wrapped.pay(orderId, amount);
        System.out.println("Logging: successful payment attempt");
    }
}
