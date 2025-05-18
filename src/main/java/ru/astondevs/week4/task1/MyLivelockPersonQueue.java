package ru.astondevs.week4.task1;

public class MyLivelockPersonQueue {

    static class NarrowPassage {
        private volatile boolean firstPersonPriority = true;
        private volatile boolean secondPersonPriority = true;

        public void passFirstPerson() {
            while (true) {
                if (firstPersonPriority) {
                    System.out.println("First person try to pass...");
                    if (secondPersonPriority) {
                        System.out.println("First person step back and change the priority");
                        firstPersonPriority = false;
                        sleep();
                    } else {
                        System.out.println("First person successfully passed");
                        break;
                    }
                } else {
                    sleep();
                }
            }
        }

        public void passSecondPerson() {
            while (true) {
                if (secondPersonPriority) {
                    System.out.println("Second person try to pass...");
                    if (firstPersonPriority) {
                        System.out.println("Second person step back and change the priority");
                        secondPersonPriority = false;
                        sleep();
                    } else {
                        System.out.println("Second person successfully passed");
                        break;
                    }
                } else {
                    sleep();
                }
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void liveLock() {
        NarrowPassage passage = new NarrowPassage();
        Thread person1 = new Thread(() -> {
            passage.passFirstPerson();
            System.out.println("First person passed");
        });

        Thread person2 = new Thread(() -> {
            passage.passSecondPerson();
            System.out.println("Second person passed");
        });
        person1.start();
        person2.start();
        try {
            person1.join();
            person2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Both persons pass");
    }
}