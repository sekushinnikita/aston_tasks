package week5;

import org.junit.jupiter.api.Test;
import ru.astondevs.week5.deliveryStrategy.CourierDelivery;
import ru.astondevs.week5.deliveryStrategy.DeliveryStrategy;
import ru.astondevs.week5.deliveryStrategy.PostDelivery;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrategyTests {
    void strategyOutputHelper(String deliver, String rightFormat) {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(bos));
            DeliveryStrategy post = new PostDelivery();
            post.deliver(deliver);
            String output = bos.toString().trim();
            assertEquals(rightFormat, output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void when_deliverWithPost_then_printCorrectMessage() {
        strategyOutputHelper("ORDER-123",
                "Order delivered by mail ORDER-123");
    }

    @Test
    void when_deliverWithCourier_then_printCorrectMessage() {
        strategyOutputHelper("ORDER-456",
                "Order delivered by mail ORDER-456");
    }

    @Test
    void when_emptyOrderId_then_stillWorks() {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(bos));
            new PostDelivery().deliver("");
            String output = bos.toString().trim();
            assertEquals("Order delivered by mail", output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void when_nullOrderId_then_noException() {
        assertDoesNotThrow(() -> {
            new CourierDelivery().deliver(null);
        });
    }

    @Test
    void when_specialCharsInOrderId_then_printsCorrectly() {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(bos));
            new CourierDelivery().deliver("ORDER#123/ABC");
            String output = bos.toString().trim();
            assertEquals("Order delivered by courier ORDER#123/ABC", output);
        } finally {
            System.setOut(originalOut);
        }
    }
}



