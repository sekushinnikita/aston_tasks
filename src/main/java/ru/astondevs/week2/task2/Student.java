package ru.astondevs.week2.task2;

import ru.astondevs.week2.task1.MyHashSet;

import java.awt.print.Book;
import java.util.List;

public class Student {
    private MyHashSet<Book> books;

    public Student(List<Book> books) {
        if (books.size() < 5) {
            throw new IllegalArgumentException("Student must have at least 5 books.");
        }
        this.books = new MyHashSet<>();
        for (Book book : books) {
            this.books.add(book);
        }
    }

    public MyHashSet<Book> getBooks() {
        return books;
    }
}
