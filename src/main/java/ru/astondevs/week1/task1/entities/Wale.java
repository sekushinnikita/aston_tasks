package ru.astondevs.week1.task1.entities;

import ru.astondevs.week1.task1.interfaces.IArial;

public class Wale extends Mammal implements IArial {

    @Override
    public String liveIn() {
        return "Water";
    }
}
