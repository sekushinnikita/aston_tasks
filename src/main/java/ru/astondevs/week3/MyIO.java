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
    private static final MyIOValidation myIOValidation;

    static {
        fileName = "week3_task1.txt";
        path = Path.of(fileName);
        scanner = new Scanner(System.in);
        myIOValidation = new MyIOValidation();
    }

    public static void writeToFile(String data) throws MyIOException{
        Objects.requireNonNull(data);
        try {
            data = isDataValid(data);
            data += System.lineSeparator();
            Files.write(path, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Your input successfully write to: " + path);
        } catch (IOException e) {
            throw new MyIOException("Error writing file: " + e.getMessage(), e);
        }
    }

    public static void rewriteToFile(String data) throws MyIOException{
        try {
            data = isDataValid(data);
            data += System.lineSeparator();
            Files.write(path, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Your input successfully rewrite to: " + path);
        } catch (IOException e) {
            throw new MyIOException("Error rewriting file:" + e.getMessage(), e);
        }
    }

    public static String readFromFile() throws MyIOException{
        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes);
        } catch (IOException e) {
            throw new MyIOException("Error reading file:" + e.getMessage(), e);
        }
    }

    private static String isDataValid(String data) throws MyIOException {
        if (!myIOValidation.isInputTextValid(data)) {
            System.out.println("Your input is wrong format. Do you prefer auto reform? Y/N");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Y")) {
                data = myIOValidation.inputValidChanging(data);
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("Your wrong input is accepted");
            } else {
                throw new MyIOException("Only ignore case y or n!");
            }
        }
        return data;
    }

}
