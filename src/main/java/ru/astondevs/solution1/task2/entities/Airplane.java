package ru.astondevs.solution1.task2.entities;

import ru.astondevs.solution1.task2.interfaces.*;
import ru.astondevs.solution1.task2.utils.ConsolePrinter;

public class Airplane extends Vehicles implements Screwable, Transporting, Wheelable, Wingable {
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

    @Override
    public void wing() {
        ConsolePrinter.print("wing)");
    }

    private Airplane(){}

}
