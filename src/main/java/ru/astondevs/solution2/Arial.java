package ru.astondevs.solution2;

import java.util.Scanner;

public class Arial {
    private String location;
    private Habitat habitat;
    private static final Scanner scanner = new Scanner(System.in);

    private Arial(){}

    public static Arial createArial() {
        Arial arial = new Arial();
        System.out.println("Write body part name:");
        arial.location = scanner.nextLine();
        System.out.println("Arial is in water,land or air?");
        String input = scanner.nextLine();
        try {
           arial.habitat = Habitat.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect habitat value. Try again.");
        }
        return arial;
    }

    public String getLocation() {
        return location;
    }

    public Habitat getHabitat() {
        return habitat;
    }
}
