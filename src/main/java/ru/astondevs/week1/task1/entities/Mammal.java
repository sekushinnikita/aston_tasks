package ru.astondevs.week1.task1.entities;

import ru.astondevs.week1.task1.interfaces.Animal;

public class Mammal extends Animal {
    protected Spine spine;

    @Override
    protected void breath() {
        System.out.println("Breath with lungs");
    }
}
