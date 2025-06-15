package ru.astondevs.week5.deliveryChainOfResponsibility;

import ru.astondevs.week5.Order;

public class StockCheckHandler extends OrderHandler {
    @Override
    public boolean check(Order order) {
        System.out.println("Checking product availability");
        return !order.getItems().isEmpty() && checkNext(order);
    }
}
