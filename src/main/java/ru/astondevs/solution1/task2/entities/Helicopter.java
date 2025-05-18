package ru.astondevs.solution1.task2.entities;

import ru.astondevs.solution1.task2.interfaces.Screwable;
import ru.astondevs.solution1.task2.interfaces.Transporting;
import ru.astondevs.solution1.task2.interfaces.Wheelable;
import ru.astondevs.solution1.task2.interfaces.Vehicles;
import ru.astondevs.solution1.task2.utils.ConsolePrinter;

public class Helicopter extends Vehicles implements Screwable, Transporting, Wheelable {
    @Override
    public void screw() {
        ConsolePrinter.print("screw)");
    }

    @Override
    public void transpot() {
        ConsolePrinter.print("transport)");
    }

    @Override
    public void wheel() {
        ConsolePrinter.print("wheel)");
    }
}
