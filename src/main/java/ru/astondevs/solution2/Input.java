package ru.astondevs.solution2;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public int readInt(String prompt) throws InvalidInputException {
        System.out.print(prompt);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            String invalidInput = scanner.next();
            throw new InvalidInputException("Incorrect input. Need INT not " + invalidInput);
        }
    }

    public double readDouble(String prompt) throws InvalidInputException {
        System.out.print(prompt);
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else {
            String invalidInput = scanner.next(); // читаем неправильный ввод
            throw new InvalidInputException("Incorrect input. Need DOUBLE not " + invalidInput);
        }
    }

 String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}


