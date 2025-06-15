package ru.astondevs.week2.task1;

public class MyLinkedList<T> implements Cloneable{
    private T data;
    private MyLinkedList<T> next;

    public MyLinkedList(T data) {
        this.data = data;
        this.next = null;
    }

    private MyLinkedList<T> head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T element) {
        if (head == null) {
            head = new MyLinkedList<>(element);
        } else {
            MyLinkedList<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new MyLinkedList<>(element);
        }
        size++;
    }

    public boolean contains(T element) {
        MyLinkedList<T> current = head;
        while (current != null) {
            if ((current.data == null && element == null)
                || (current.data != null && current.data.equals(element))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean remove(T element) {
        if (head == null) return false;

        if ((head.data == null && element == null) || (head.data != null && head.data.equals(element))) {
            head = head.next;
            size--;
            return true;
        }

        MyLinkedList<T> current = head;
        while (current.next != null) {
            if ((current.next.data == null && element == null)
                || (current.next.data != null && current.next.data.equals(element))) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // не найдено
    }

    public int size() {
        return size;
    }

    public void clearAll() {
        head = null;
        size = 0;
    }

    @Override
    public MyLinkedList<T> clone() {
        try {
            return (MyLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}