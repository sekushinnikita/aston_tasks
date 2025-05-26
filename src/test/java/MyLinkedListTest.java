import org.junit.jupiter.api.Test;
import ru.astondevs.week2.task1.MyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    void testAddAndSize() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());

        list.add(1);
        assertEquals(1, list.size());

        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    void testContains() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        assertTrue(list.contains("a"));
        assertTrue(list.contains("b"));
        assertTrue(list.contains("c"));
        assertFalse(list.contains("d"));

        // Проверка на null
        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    void testRemoveHead() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        boolean removed = list.remove(1);
        assertTrue(removed);
        assertEquals(2, list.size());
        assertFalse(list.contains(1));
    }

    @Test
    void testRemoveMiddle() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        boolean removed = list.remove(2);
        assertTrue(removed);
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test
    void testRemoveTail() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);

        boolean removed = list.remove(2);
        assertTrue(removed);
        assertEquals(1, list.size());

        // Удаление несуществующего элемента
        boolean notFound = list.remove(100);
        assertFalse(notFound);
    }

    @Test
    void testRemoveNonExistent() {
        MyLinkedList<String> list = new MyLinkedList<>();

        // Удаление из пустого списка
        assertFalse(list.remove("a"));

        // Добавляем один элемент и пытаемся удалить другой
        list.add("b");

        boolean result = list.remove("c");
        assertFalse(result);

    }

    @Test
    void testClearAll() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        assertEquals(2, list.size());

        list.clearAll();

        assertEquals(0, list.size());
        assertFalse(list.contains(10));
        assertFalse(list.contains(20));
        //head null
    }

    @Test
    void testClone() {
        MyLinkedList<String> original = new MyLinkedList<>();
        original.add("x");
        original.add("y");

        MyLinkedList<String> clone = original.clone();

        // Неравенство по ссылке
        assertNotSame(original, clone);

        // Содержимое
        assertTrue(clone.contains("x"));
        assertTrue(clone.contains("y"));
        assertEquals(original.size(), clone.size());

        // Разница значение clone и original
        clone.remove("x");
        assertFalse(clone.contains("x"));
        assertTrue(original.contains("x"));
    }

    @Test
    void testNullValues() {
        MyLinkedList<String> list = new MyLinkedList<>();

        // Добавляем null и проверяем наличие
        list.add(null);
        assertTrue(list.contains(null));

        // Удаляем null и проверяем что удалено успешно
        boolean removedNull = list.remove(null);
        assertTrue(removedNull);

        // После удаления null список должен быть пустым
        assertFalse(list.contains(null));
    }
}
