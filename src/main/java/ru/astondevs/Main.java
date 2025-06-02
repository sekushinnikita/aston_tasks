package ru.astondevs;

import ru.astondevs.week3.MyIO;
import ru.astondevs.week3.MyIOException;

public class Main {
    public static void main(String[] args) {
        try {
            MyIO.rewriteToFile("first string");
            MyIO.writeToFile("second STRING");
        } catch (MyIOException e) {
            throw new RuntimeException(e);
        }
    }
}