package week5;

import org.junit.jupiter.api.Test;
import ru.astondevs.week5.deliveryAdapter.ExternalDeliveryAdapter;
import ru.astondevs.week5.deliveryAdapter.ExternalDeliveryService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdapterTests {
    private String adapterOutputHelper(String orderId) {
        ExternalDeliveryService service = new ExternalDeliveryService();
        ExternalDeliveryAdapter adapter = new ExternalDeliveryAdapter(service);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            adapter.deliver(orderId);
            return outputStream.toString().trim();
        } finally {
            System.setOut(originalOut);
        }
    }

    private void adapterExceptionHelper(String orderId) {
        ExternalDeliveryService service = new ExternalDeliveryService();
        ExternalDeliveryAdapter adapter = new ExternalDeliveryAdapter(service);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        assertDoesNotThrow(() -> adapter.deliver(orderId));
    }

    @Test
    void when_normalOrderId_then_correctOutputFormat() {
        String result = adapterOutputHelper("ORDER-123");
        assertEquals("Send package №ORDER-123", result);
    }

    @Test
    void when_emptyOrderId_then_outputWithEmptyNumber() {
        String result = adapterOutputHelper("");
        assertEquals("Send package №", result);
    }

    @Test
    void when_nullOrderId_then_noExceptionsThrown() {
        adapterExceptionHelper(null);
    }

    @Test
    void when_orderIdWithSpecialChars_then_outputMatchesInput() {
        String result = adapterOutputHelper("ORDER#123/ABC");
        assertEquals("Send package №ORDER#123/ABC", result);
    }

    @Test
    void when_veryLongOrderId_then_outputContainsFullId() {
        String longId = "ORDER-" + "1234567890".repeat(10);
        String result = adapterOutputHelper(longId);
        assertEquals("Send package №" + longId, result);
    }

    @Test
    void when_orderIdWithSpaces_then_outputPreservesSpaces() {
        String result = adapterOutputHelper("ORDER 123");
        assertEquals("Send package №ORDER 123", result);
    }
}
