package week4;

import org.junit.jupiter.api.Test;
import ru.astondevs.week4.task1.MyDeadlockPersonQueue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyDeadlockPersonQueueTest {
    @Test
    public void testPotentialDeadlock() throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        MyDeadlockPersonQueue task1 = new MyDeadlockPersonQueue(lockA, lockB);
        Thread thread1 = new Thread(task1, "T1");
        MyDeadlockPersonQueue task2 = new MyDeadlockPersonQueue(lockB, lockA);
        Thread thread2 = new Thread(task2, "T2");
        thread1.start();
        thread2.start();
        thread1.join(200);
        thread2.join(200);
        boolean deadlockDetected = thread1.isAlive() && thread2.isAlive();
        assertTrue(deadlockDetected, "Deadlock has happened");
    }

    @Test
    public void testNoDeadlockWithTimeout() throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        MyDeadlockPersonQueue task1 = new MyDeadlockPersonQueue(lockA, lockB);
        Thread thread1 = new Thread(task1, "T1");
        MyDeadlockPersonQueue task2 = new MyDeadlockPersonQueue(lockA, lockB);
        Thread thread2 = new Thread(task2, "T2");
        thread1.start();
        thread2.start();
        thread1.join(200);
        thread2.join(200);
        assertFalse(thread1.isAlive(), "T1 ended");
        assertFalse(thread2.isAlive(), "T2 ended");
    }

    @Test
    public void testSingleThreadNoDeadlock() throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        Thread thread = new Thread(new MyDeadlockPersonQueue(lockA, lockB), "T1");
        thread.start();
        thread.join(200);
        assertFalse(thread.isAlive(), "T1 ended");
    }

    @Test
    public void testMultipleThreadsNoDeadlock() throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        Thread t1 = new Thread(new MyDeadlockPersonQueue(lockA, lockB), "T1");
        Thread t2 = new Thread(new MyDeadlockPersonQueue(lockA, lockB), "T2");
        t1.start();
        t2.start();
        t1.join(300);
        t2.join(300);
        assertFalse(t1.isAlive(), "T1 ended");
        assertFalse(t2.isAlive(), "T2 ended");
    }
}
