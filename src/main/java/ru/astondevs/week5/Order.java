package ru.astondevs.week5;

import ru.astondevs.week5.deliveryStrategy.DeliveryStrategy;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private final String user;
    private final List<String> items;
    private DeliveryStrategy delivery;

    private Order(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.items = builder.items;
    }

    public String getId() { return id; }
    public String getUser() { return user; }
    public List<String> getItems() { return items; }

    public void setDelivery(DeliveryStrategy delivery) {
        this.delivery = delivery;
    }
    public void deliver() {
        delivery.deliver(id);
    }

    public static class Builder {
        private final String id;
        private String user;
        private final List<String> items = new ArrayList<>();

        public Builder(String id) { this.id = id; }
        public Builder user(String user) { this.user = user; return this; }
        public Builder addItem(String item) { items.add(item); return this; }
        public Order build() { return new Order(this); }
    }
}
