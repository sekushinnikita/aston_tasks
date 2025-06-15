package week5;

import org.junit.jupiter.api.Test;
import ru.astondevs.week5.DeliveryLoggingPaymentDecorator;
import ru.astondevs.week5.Order;
import ru.astondevs.week5.PaymentService;
import ru.astondevs.week5.deliveryAdapter.ExternalDeliveryAdapter;
import ru.astondevs.week5.deliveryAdapter.ExternalDeliveryService;
import ru.astondevs.week5.deliveryChainOfResponsibility.OrderHandler;
import ru.astondevs.week5.deliveryChainOfResponsibility.PaymentCheckHandler;
import ru.astondevs.week5.deliveryChainOfResponsibility.StockCheckHandler;
import ru.astondevs.week5.deliveryProxy.PaymentProxy;
import ru.astondevs.week5.deliveryStrategy.PostDelivery;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {
    private Order createOrderHelper(String id, String user, List<String> items) {
        Order.Builder builder = new Order.Builder(id);
        builder.user(user);
        items.forEach(builder::addItem);
        return builder.build();
    }

    private String orderOutputHelper(Runnable action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            action.run();
            return outputStream.toString().trim();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void when_validOrderProcessed_then_allStepsExecuted() {
        Order order = createOrderHelper("INTEG-1", "SOLVENT_USER",
                List.of("Laptop", "Mouse"));
        String output = orderOutputHelper(() -> {
            OrderHandler handler = new StockCheckHandler();
            handler.linkWith(new PaymentCheckHandler());
            boolean isValid = handler.check(order);
            PaymentService payment = new PaymentProxy();
            if (isValid) {
                payment.pay(order.getId(), 329.29);
                order.setDelivery(new PostDelivery());
                order.deliver();
            }
        });
        assertTrue(output.contains("Checking product availability"));
        assertTrue(output.contains("Verify payment ability"));
        assertTrue(output.contains("Product payment INTEG-1 $ 329.29"));
        assertTrue(output.contains("Order delivered by mail INTEG-1"));
    }

    @Test
    void when_blockedUserTriesToPay_then_paymentRejected() {
        Order order = createOrderHelper("BLOCKED-1", "blocked_user",
                List.of("Phone"));
        String output = orderOutputHelper(() -> {
            PaymentService payment = new PaymentProxy();
            payment.pay(order.getId(), 500.0);
        });
        assertEquals("Product payment BLOCKED-1 $ 500.0", output);
    }

    @Test
    void when_emptyOrderProcessed_then_validationFails() {
        Order emptyOrder = createOrderHelper("EMPTY-1", "TEST_USER", List.of());
        String output = orderOutputHelper(() -> {
            OrderHandler handler = new StockCheckHandler();
            boolean isValid = handler.check(emptyOrder);
            assertFalse(isValid);
        });
        assertTrue(output.contains("Checking product availability"));
        assertFalse(output.contains("Verify payment ability"));
    }

    @Test
    void when_orderWithLogging_then_allStepsLogged() {
        Order order = createOrderHelper("LOG-1", "VIP_USER", List.of("Monitor"));
        String output = orderOutputHelper(() -> {
            PaymentService payment = new DeliveryLoggingPaymentDecorator(new PaymentProxy());
            payment.pay(order.getId(), 259.99);
        });
        assertTrue(output.contains("Logging: payment attempt LOG-1"));
        assertTrue(output.contains("Product payment LOG-1 $ 259.99"));
        assertTrue(output.contains("Logging: successful payment attempt"));
    }

    @Test
    void when_externalDeliveryUsed_then_correctOutput() {
        Order order = createOrderHelper("EXT-1", "NORMAL_USER", List.of("Keyboard"));
        String output = orderOutputHelper(() -> {
            ExternalDeliveryAdapter adapter = new ExternalDeliveryAdapter
                    (new ExternalDeliveryService());
            order.setDelivery(adapter);
            order.deliver();
        });
        assertEquals("Send package №EXT-1", output);
    }
}
