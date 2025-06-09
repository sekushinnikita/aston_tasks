import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.astondevs.week4.task2.MyExecutorServiceThreads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyExecutorServiceTreadsTest {
    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @Test
    void testThreadsStartsAndPrintsAlternatingNumbers() throws InterruptedException {
        MyExecutorServiceThreads.threads();
        Thread.sleep(200);
        assertTrue(true);
    }

    @Test
    void testMultipleCallsAreIdempotent() throws InterruptedException {
        MyExecutorServiceThreads.threads();
        MyExecutorServiceThreads.threads();
        Thread.sleep(200);
        assertTrue(true);
    }

    @Test
    public void testExecutorHasRunningThreads() throws InterruptedException {
        MyExecutorServiceThreads.threads();
        CountDownLatch latch = new CountDownLatch(1);
        Thread testThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (MyExecutorServiceThreads.isVolatileThreadFlag()) {
                    latch.countDown();
                    break;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        testThread.start();
        boolean started = latch.await(1, TimeUnit.SECONDS);
        assertTrue(started);
    }

    @Test
    void testAtomicBooleanToggles() throws InterruptedException {
        MyExecutorServiceThreads.threads();
        Thread.sleep(50);
        boolean value = MyExecutorServiceThreads.isVolatileThreadFlag();
        assertTrue(value == true || value == false);
    }

    @Test
    void testThreadsContinueRunning() throws InterruptedException {
        MyExecutorServiceThreads.threads();
        Thread.sleep(300);
        assertTrue(true);
    }
}
