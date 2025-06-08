package ru.astondevs.week4.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyExecutorServiceThreads {
    private static final ExecutorService executor;
    private static final AtomicBoolean volatileThreadFlag;

    static {
        executor = Executors.newFixedThreadPool(2);
        volatileThreadFlag = new AtomicBoolean(true);
    }

    public static void threads (){
        Runnable task1 = () -> {
            while (true) {
                if (volatileThreadFlag.get()) {
                    System.out.println("1");
                    volatileThreadFlag.set(false);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable task2 = () -> {
            while (true) {
                if (!volatileThreadFlag.get()) {
                    System.out.println("2");
                    volatileThreadFlag.set(true);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        executor.submit(task1);
        executor.submit(task2);
    }
}
