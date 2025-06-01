package ru.astondevs.week3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Scanner;

public class MyIO {
    private static final String fileName;
    private static final Path path;
    private static final Scanner scanner;

    static {
        fileName = "week3_task1.txt";
        path = Path.of(fileName);
        scanner = new Scanner(System.in);
    }

    public static void writeToFile(String data) {
        Objects.requireNonNull(data);
        try {
            if (!data.endsWith(".")) {
                data += ".";
            }
            data += System.lineSeparator();
            Files.write(path, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Данные успешно записаны в файл: " + path);
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    public static void rewriteToFile(String data) {
        try {
            if (!data.endsWith(".")) {
                data += ".";
            }
            data += System.lineSeparator();
            Files.write(path, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Данные успешно записаны в файл: " + path);
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    public static String readFromFile() {
        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
    }

}
