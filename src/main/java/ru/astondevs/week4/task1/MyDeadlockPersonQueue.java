package ru.astondevs.week4.task1;

public class MyDeadlockPersonQueue extends Thread {
    private final Object lock1;
    private final Object lock2;

    public MyDeadlockPersonQueue(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void run() {
        try {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " capture " + lock1);
                Thread.sleep(50);
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " capture " + lock2);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deadlock(){
        Object firstPersonLock1 = new Object();
        Object firstPersonLock2 = new Object();
        Object secondPersonLock1 = firstPersonLock1;
        Object secondPersonLock2 = firstPersonLock2;
        Thread personA = new MyDeadlockPersonQueue(firstPersonLock1, firstPersonLock2);
        personA.setName("First person");
        Thread personB = new MyDeadlockPersonQueue(secondPersonLock2, secondPersonLock1);
        personB.setName("Second person");
        personA.start();
        personB.start();
    }
}
