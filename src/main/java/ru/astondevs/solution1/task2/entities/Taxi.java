package ru.astondevs.solution1.task2.entities;

import ru.astondevs.solution1.task2.interfaces.Wheelable;
import ru.astondevs.solution1.task2.interfaces.Vehicles;
import ru.astondevs.solution1.task2.utils.ConsolePrinter;

public class Taxi extends Vehicles implements Wheelable {
    @Override
    public void wheel() {
        ConsolePrinter.print("wheel)");
    }
}
