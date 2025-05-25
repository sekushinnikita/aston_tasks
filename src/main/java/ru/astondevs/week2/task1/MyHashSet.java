package ru.astondevs.week2.task1;

import java.util.Arrays;

public class MyHashSet<E> {
    private static final int INITIAL_CAPACITY = 100;
    private Object[] table;
    private int size;

    public MyHashSet() {
        this.table = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    private int hash(E element) {
        return (element == null) ? 0 : Math.abs(element.hashCode() % table.length);
    }

    public boolean add(E element) {
        if (contains(element)) {
            return false;
        }
        if (size >= table.length * 0.75) {
            resize();
        }
        int index = hash(element);
        while (table[index] != null) {
            index = (index + 1) % table.length;
        }
        table[index] = element;
        size++;
        return true;
    }

    public boolean remove(E element) {
        int index = hash(element);
        while (table[index] != null) {
            if (table[index].equals(element)) {
                table[index] = null;
                size--;
                return true;
            }
            index = (index + 1) % table.length;
        }
        return false;
    }

    public boolean contains(E element) {
        int index = hash(element);
        while (table[index] != null) {
            if (table[index].equals(element)) {
                return true;
            }
            index = (index + 1) % table.length;
        }
        return false;
    }

    private void resize() {
        Object[] oldTable = table;
        table = new Object[oldTable.length * 2];
        size = 0;

        for (Object element : oldTable) {
            if (element != null) {
                add((E) element);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }
}
