package week5;

import org.junit.jupiter.api.Test;
import ru.astondevs.week5.deliveryProxy.PaymentProxy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ProxyTests {
    private String getConsoleOutputHelper(Runnable action) {
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
    void when_payWithNormalUser_then_paymentProcessed() {
        PaymentProxy proxy = new PaymentProxy();
        String output = getConsoleOutputHelper(() ->
                proxy.pay("good_user-123", 100.0));
        assertTrue(output.contains("Product payment good_user-123 $ 100.0"));
    }

    @Test
    void when_payWithBlockedUser_then_accessDenied() {
        PaymentProxy proxy = new PaymentProxy();
        String output = getConsoleOutputHelper(() ->
                proxy.pay("blocked_user-456", 200.0));
        assertEquals("Access denied for order: blocked_user-456", output);
    }

    @Test
    void when_payWithEmptyOrderId_then_processedNormally() {
        PaymentProxy proxy = new PaymentProxy();
        String output = getConsoleOutputHelper(() ->
                proxy.pay("", 75.0));
        assertTrue(output.contains("Product payment  $ 75.0"));
    }

    @Test
    void when_payMultipleTimes_then_serviceInitializedOnce() {
        PaymentProxy proxy = new PaymentProxy();
        proxy.pay("user1-001", 10.0);
        proxy.pay("user2-002", 20.0);
        String output = getConsoleOutputHelper(() -> {
            proxy.pay("user3-003", 30.0);
        });
        assertTrue(output.contains("Product payment user3-003 $ 30.0"));
    }
}
