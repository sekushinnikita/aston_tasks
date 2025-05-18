package ru.astondevs.solution1.task1.entities;

import ru.astondevs.solution1.task1.interfaces.Animal;
import ru.astondevs.solution1.task1.interfaces.IArial;

public class Fish extends Animal implements IArial {
    @Override
    public String liveIn() {
        return "Water";
    }
}
