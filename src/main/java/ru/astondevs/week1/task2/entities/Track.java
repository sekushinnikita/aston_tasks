package ru.astondevs.week1.task2.entities;

import ru.astondevs.week1.task2.interfaces.Transporting;
import ru.astondevs.week1.task2.interfaces.Wheelable;
import ru.astondevs.week1.task2.interfaces.Vehicles;
import ru.astondevs.week1.task2.utils.ConsolePrinter;

public class Track extends Vehicles implements Transporting, Wheelable {
    @Override
    public void transport() {
        ConsolePrinter.print("transport");
    }

    @Override
    public void wheel() {
        ConsolePrinter.print("wheel");
    }
}
