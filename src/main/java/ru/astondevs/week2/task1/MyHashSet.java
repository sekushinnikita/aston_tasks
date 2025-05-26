package ru.astondevs.week2.task1;

import java.util.Arrays;

public class MyHashSet<T> implements Cloneable{
    private static final int INITIAL_CAPACITY = 10;
    private Object[] table;
    private int size;

    public MyHashSet() {
        this.table = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public boolean contains(T element) {
        for (int i=0;i<INITIAL_CAPACITY;i++){
            if (table[i] == element)
                return true;
        }
        return false;
    }

    public boolean add(T element) {
        if (contains(element)) {
            return false;
        }
        if (size >= table.length) {
            resize();
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                table[i] = element;
                size++;
                return true;
            }
        }
        return false;
    }

    public boolean remove(T element) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].equals(element)) {
                table[i] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    private void resize() {
        Object[] newTable = new Object[table.length * 2];
        for (Object obj : table) {
            if (obj != null) {
                for (int i = 0; i < newTable.length; i++) {
                    if (newTable[i] == null) {
                        newTable[i] = obj;
                        break;
                    }
                }
            }
        }
        table = newTable;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clearAll() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public MyHashSet<T> clone() {
        try {
            return (MyHashSet<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
