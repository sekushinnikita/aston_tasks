package ru.astondevs.week5.deliveryChainOfResponsibility;

import ru.astondevs.week5.Order;

public abstract class OrderHandler {
    private OrderHandler next;

    public void linkWith(OrderHandler next) {
        this.next = next;
    }

    public abstract boolean check(Order order);

    protected boolean checkNext(Order order) {
        return next == null || next.check(order);
    }
}
