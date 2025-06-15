package week5;

import org.junit.jupiter.api.Test;
import ru.astondevs.week5.Order;
import ru.astondevs.week5.deliveryChainOfResponsibility.OrderHandler;
import ru.astondevs.week5.deliveryChainOfResponsibility.PaymentCheckHandler;
import ru.astondevs.week5.deliveryChainOfResponsibility.StockCheckHandler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChainOfResponsibilityTests {
    private Order createTestOrderHelper(String user, String... items) {
        Order.Builder builder = new Order.Builder("TEST-ORDER");
        builder.user(user);
        for (String item : items) {
            builder.addItem(item);
        }
        return builder.build();
    }

    private OrderHandler createHandlerChainHelper() {
        OrderHandler stockHandler = new StockCheckHandler();
        OrderHandler paymentHandler = new PaymentCheckHandler();
        stockHandler.linkWith(paymentHandler);
        return stockHandler;
    }

    @Test
    void when_orderWithItemsAndSolventUser_then_approved() {
        Order order = createTestOrderHelper("SOLVENT_USER", "Laptop", "Phone");
        OrderHandler chain = createHandlerChainHelper();
        assertTrue(chain.check(order));
    }

    @Test
    void when_orderWithoutItems_then_rejected() {
        Order order = createTestOrderHelper("SOLVENT_USER");
        OrderHandler chain = createHandlerChainHelper();
        assertFalse(chain.check(order));
    }

    @Test
    void when_orderWithNonSolventUser_then_rejected() {
        Order order = createTestOrderHelper("NON_SOLVENT", "Book");
        OrderHandler chain = createHandlerChainHelper();
        assertTrue(chain.check(order));
    }

    @Test
    void when_emptyOrder_then_rejected() {
        Order order = createTestOrderHelper("");
        OrderHandler chain = createHandlerChainHelper();
        assertFalse(chain.check(order));
    }

    @Test
    void when_onlyStockCheckPasses_then_approved() {
        Order order = createTestOrderHelper("VIP_USER", "Monitor");
        OrderHandler stockHandler = new StockCheckHandler();
        assertTrue(stockHandler.check(order));
    }
}
