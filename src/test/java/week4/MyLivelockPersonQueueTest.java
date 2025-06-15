package week4;

import org.junit.jupiter.api.Test;
import ru.astondevs.week4.task1.MyLivelockPersonQueue;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyLivelockPersonQueueTest {
    @Test
    public void testBothPersonsPassSuccessfully() throws InterruptedException {
        MyLivelockPersonQueue.NarrowPassage passage = new MyLivelockPersonQueue.NarrowPassage();
        Thread firstPersonThread = new Thread(passage::passFirstPerson);
        Thread secondPersonThread = new Thread(passage::passSecondPerson);
        firstPersonThread.start();
        secondPersonThread.start();
        firstPersonThread.join();
        secondPersonThread.join();
        assertFalse(firstPersonThread.isAlive());
        assertFalse(secondPersonThread.isAlive());
    }

    @Test
    public void testNoLiveLockOccurs() throws InterruptedException {
        MyLivelockPersonQueue.NarrowPassage passage = new MyLivelockPersonQueue.NarrowPassage();
        Thread firstPersonThread = new Thread(passage::passFirstPerson);
        Thread secondPersonThread = new Thread(passage::passSecondPerson);
        firstPersonThread.start();
        secondPersonThread.start();
        firstPersonThread.join(2000);
        secondPersonThread.join(2000);
        assertFalse(firstPersonThread.isAlive());
        assertFalse(secondPersonThread.isAlive());
    }
}
