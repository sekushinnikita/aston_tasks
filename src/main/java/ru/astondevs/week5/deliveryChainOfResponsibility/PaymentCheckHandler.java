package ru.astondevs.week5.deliveryChainOfResponsibility;

import ru.astondevs.week5.Order;

public class PaymentCheckHandler extends OrderHandler {
    @Override
    public boolean check(Order order) {
        System.out.println("Verify payment ability");
        return order.getUser().contains("SOLVENT") || checkNext(order);
    }
}
