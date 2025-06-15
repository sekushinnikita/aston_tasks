package ru.astondevs.week5.deliveryStrategy;

public class PostDelivery implements DeliveryStrategy {
    @Override
    public void deliver(String orderId) {
        System.out.println("Order delivered by mail " + orderId);
    }
}