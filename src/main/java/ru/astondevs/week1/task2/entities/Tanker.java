package ru.astondevs.week1.task2.entities;

import ru.astondevs.week1.task2.interfaces.Screwable;
import ru.astondevs.week1.task2.interfaces.Transporting;
import ru.astondevs.week1.task2.interfaces.Vehicles;
import ru.astondevs.week1.task2.utils.ConsolePrinter;

public class Tanker extends Vehicles implements Screwable, Transporting {
    @Override
    public void screw() {
        ConsolePrinter.print("screw");
    }

    @Override
    public void transport() {
        ConsolePrinter.print("transport");
    }
}
