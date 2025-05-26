package ru.astondevs.week2.task2;

import com.github.javafaker.Faker;

import java.util.Scanner;

public class Book {
    private String title;
    private String author;
    private Integer year;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Faker faker = new Faker();

    private Book(){}

    public static Book createBookManually() {
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book author: ");
        String author = scanner.nextLine();
        System.out.println("Enter book author: ");
        Integer year = scanner.nextInt();
        return createBook(title, author, year);
    }

    public static Book createBookRandom() {
        String title = faker.book().title();
        String author = faker.book().author();
        Integer year = faker.number().numberBetween(1997,2005);
        return createBook(title, author, year);
    }

    private static Book createBook(String title, String author, Integer year) {
        Book book = new Book();
        book.title = title;
        book.author = author;
        book.year = year;
        return book;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book book)) return false;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return title.hashCode() + author.hashCode();
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
