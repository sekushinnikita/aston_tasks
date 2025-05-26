package ru.astondevs.week2.task2;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private List<Book> books;
    private static final Faker faker = new Faker();

    public Student() {
        books = new ArrayList<>();
        for (int i = 0; i<5; i++){
            books.add(Objects.requireNonNull(Book.createBookRandom()));
            this.name = faker.book().author();
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    public List<Book> getBooks() {
        return books;
    }
}
