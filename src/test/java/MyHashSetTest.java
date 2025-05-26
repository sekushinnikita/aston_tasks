import org.junit.jupiter.api.Test;
import ru.astondevs.week2.task1.MyHashSet;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashSetTest {

    @Test
    public void testAddAndContains() {
        MyHashSet<String> set = new MyHashSet<>();
        assertTrue(set.add("A"));
        assertTrue(set.contains("A"));
        assertFalse(set.add("A")); // добавление дубликата
    }

    @Test
    public void testRemove() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(2);
        assertTrue(set.remove(1));
        assertFalse(set.contains(1));
        assertFalse(set.remove(3)); // удаление отсутствующего элемента
    }

    @Test
    public void testSizeAndIsEmpty() {
        MyHashSet<Integer> set = new MyHashSet<>();
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
        set.add(10);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
    }

    @Test
    public void testClearAll() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(2);
        set.clearAll();
        assertEquals(0, set.size());
        assertFalse(set.contains(1));
        assertFalse(set.contains(2));
    }

    @Test
    public void testClone() {
        MyHashSet<String> original = new MyHashSet<>();
        original.add("Clone");
        MyHashSet<String> clone = original.clone();
        assertTrue(clone.contains("Clone"));
        clone.remove("Clone");
        assertFalse(clone.contains("Clone"));
        // оригинал не должен измениться
        assertTrue(original.contains("Clone"));
    }

    // Краевые случаи

    @Test
    public void testAddNullElement() {
        MyHashSet<String> set = new MyHashSet<>();
        // Добавление null не проверяется поэтому оно должно работать без ошибок.
        boolean added = set.add(null);
        assertTrue(added);
        assertTrue(set.contains(null));
    }

    @Test
    public void testRemoveNullElement() {
        MyHashSet<String> set = new MyHashSet<>();
        set.add(null);
        assertTrue(set.remove(null));
        assertFalse(set.contains(null));
    }

    @Test
    public void testAddManyElementsToTriggerResize() {
        MyHashSet<Integer> set = new MyHashSet<>();

        for (int i = 0; i < 15; i++) { // Превышаем начальную емкость на 5
            assertTrue(set.add(i));
            assertTrue(set.contains(i));
        }

        // Проверка размера после добавления элементов
        assertEquals(15, set.size());

        // Проверка, что все элементы остались в множестве
        for (int i = 0; i < 15; i++) {
            assertTrue(set.contains(i));
        }
    }

    @Test
    public void testRemoveFromEmptySet() {
        MyHashSet<String> set = new MyHashSet<>();
        // Удаление из пустого множества должно возвращать false без ошибок
        assertFalse(set.remove("nonexistent"));
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }
}