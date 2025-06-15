package ru.astondevs.week5.deliveryAdapter;

import ru.astondevs.week5.deliveryStrategy.DeliveryStrategy;

public class ExternalDeliveryAdapter implements DeliveryStrategy {
    private final ExternalDeliveryService externalService;

    public ExternalDeliveryAdapter(ExternalDeliveryService externalService) {
        this.externalService = externalService;
    }

    @Override
    public void deliver(String orderId) {
        externalService.shipPackage(orderId);
    }
}
