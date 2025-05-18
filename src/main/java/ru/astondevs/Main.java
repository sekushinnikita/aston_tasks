package ru.astondevs;

import ru.astondevs.week4.task1.MyDeadlockPersonQueue;
import ru.astondevs.week4.task1.MyLivelockPersonQueue;
import ru.astondevs.week4.task2.MyExecutorServiceThreads;

public class Main {
    public static void main(String[] args) {
        MyLivelockPersonQueue.liveLock();
        MyDeadlockPersonQueue.deadlock();
        MyExecutorServiceThreads.threads();
    }
}