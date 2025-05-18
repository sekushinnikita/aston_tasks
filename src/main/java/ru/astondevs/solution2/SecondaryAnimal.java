package ru.astondevs.solution2;

import java.util.Arrays;
import java.util.Scanner;

public class SecondaryAnimal extends Animal {
    private static final Scanner scanner = new Scanner(System.in);

    public static SecondaryAnimal createSecondaryAnimal() {
        SecondaryAnimal secondaryAnimal = new SecondaryAnimal();
        System.out.println("Enter animal`s name:");
        secondaryAnimal.name = scanner.nextLine();
        System.out.println("Enter count of animal`s body parts:");
        secondaryAnimal.countOfBodyParts = scanner.nextInt();
        for (int i = 0; i < secondaryAnimal.countOfBodyParts; i++){
            secondaryAnimal.bodyPart[i] = BodyPart.createBodyPart();
        }
        secondaryAnimal.arial = Arial.createArial();
        System.out.println("Enter animal`s special:");
        String input = scanner.nextLine();
        try {
            secondaryAnimal.special = Special.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect special value. Try again.");
        }
        return secondaryAnimal;
    }

    @Override
    public String toString() {
        return "SecondaryAnimal{" +
                "countOfBodyParts=" + countOfBodyParts +
                ", name='" + name + '\'' +
                ", bodyPart=" + Arrays.toString(bodyPart) +
                ", arial=" + arial +
                ", special=" + special +
                '}';
    }
}
