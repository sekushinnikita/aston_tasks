package ru.astondevs.week1.task2.entities;

import ru.astondevs.week1.task2.interfaces.Wheelable;
import ru.astondevs.week1.task2.interfaces.Vehicles;
import ru.astondevs.week1.task2.utils.ConsolePrinter;

public class Taxi extends Vehicles implements Wheelable {
    @Override
    public void wheel() {
        ConsolePrinter.print("wheel");
    }
}
