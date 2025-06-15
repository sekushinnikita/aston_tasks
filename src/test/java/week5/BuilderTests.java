package week5;

import org.junit.jupiter.api.Test;
import ru.astondevs.week5.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTests {
    private Order createBasicOrderHelper() {
        return new Order.Builder("BASE-ORDER")
                .user("default_user")
                .addItem("default_item")
                .build();
    }

    @Test
    void when_buildOrderWithAllFields_then_allFieldsSetCorrectly() {
        Order order = new Order.Builder("TEST-123")
                .user("john_doe")
                .addItem("Laptop")
                .addItem("Mouse")
                .build();
        assertEquals("TEST-123", order.getId());
        assertEquals("john_doe", order.getUser());
        assertEquals(List.of("Laptop", "Mouse"), order.getItems());
    }

    @Test
    void when_buildOrderWithoutUser_then_userFieldIsNull() {
        Order order = new Order.Builder("NO-USER-ORDER")
                .addItem("Keyboard")
                .build();
        assertNull(order.getUser());
        assertEquals(1, order.getItems().size());
    }

    @Test
    void when_buildOrderWithoutItems_then_itemsListIsEmpty() {
        Order order = new Order.Builder("NO-ITEMS-ORDER")
                .user("test_user")
                .build();
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void when_addMultipleItems_then_allItemsPreservedInOrder() {
        Order order = new Order.Builder("MULTI-ITEM-ORDER")
                .user("multi_user")
                .addItem("Item1")
                .addItem("Item2")
                .addItem("Item3")
                .build();
        assertEquals(3, order.getItems().size());
        assertTrue(order.getItems().contains("Item2"));
    }

    @Test
    void when_chainedMethodCalls_then_buildsCorrectOrder() {
        Order order = new Order.Builder("CHAINED-ORDER")
                .user("chained_user")
                .addItem("First")
                .addItem("Second")
                .addItem("Third")
                .build();
        assertEquals(3, order.getItems().size());
        assertEquals("chained_user", order.getUser());
    }
}
