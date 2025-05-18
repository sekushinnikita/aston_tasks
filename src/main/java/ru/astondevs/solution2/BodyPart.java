package ru.astondevs.solution2;

import java.util.Objects;
import java.util.Scanner;

public class BodyPart implements Comparable<BodyPart>{
    private String name;
    private Double size;
    private String function;
    private static final Scanner scanner = new Scanner(System.in);

    private BodyPart() {}

    public static BodyPart createBodyPart() {

        BodyPart bodyPart = new BodyPart();
        System.out.println("Write body part name:");
        bodyPart.name = scanner.nextLine();
        System.out.println("Write body part size:");
        bodyPart.size = scanner.nextDouble();
        BodyPartFunctional bodyPartFunctional = new BodyPartFunctional() {
            @Override
            public String bodyPartFunction() {//имеет ли смысл потому что я выношу требования к функциональности обьекта за пределы конструктора
                System.out.println("Write body part function:");
                return scanner.nextLine();
            }
        };
        bodyPart.function = bodyPartFunctional.bodyPartFunction();
        return bodyPart;
    }

    @Override
    public int compareTo(BodyPart o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BodyPart bodyPart)) return false;
        return Objects.equals(name, bodyPart.name) && Objects.equals(size, bodyPart.size) && Objects.equals(function, bodyPart.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, function);
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public Double getSize() {
        return size;
    }
}
