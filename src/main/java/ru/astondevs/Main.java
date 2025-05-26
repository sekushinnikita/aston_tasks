package ru.astondevs;

import ru.astondevs.week2.task2.Book;
import ru.astondevs.week2.task2.Student;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i<5; i++){
            students.add(new Student());
        }
        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted()
                .distinct()
                .filter(book -> {
                    try {
                        return book.getYear() > 2001;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Year of chosen book: " + year),
                        () -> System.out.println("Book's been found.")
                );
    }
}