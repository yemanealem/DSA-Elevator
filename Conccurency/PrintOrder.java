import java.util.concurrent.Semaphore;

public class PrintOrder {

    static class Foo {
        // Semaphore for controlling execution of first() method
        private Semaphore firstSemaphore = new Semaphore(1);

        // Semaphore for controlling execution of second() method
        private Semaphore secondSemaphore = new Semaphore(0);

        // Semaphore for controlling execution of third() method
        private Semaphore thirdSemaphore = new Semaphore(0);

        public Foo() {
            // Semaphores initialized above
        }

        public void first(Runnable printFirst) throws InterruptedException {
            firstSemaphore.acquire();
            printFirst.run();          // prints "first"
            secondSemaphore.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            secondSemaphore.acquire();
            printSecond.run();         // prints "second"
            thirdSemaphore.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            thirdSemaphore.acquire();
            printThird.run();          // prints "third"
            firstSemaphore.release();
        }
    }

    public static void main(String[] args) {

        Foo foo = new Foo();

        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");

        Thread t1 = new Thread(() -> {
            try {
                foo.first(printFirst);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                foo.second(printSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                foo.third(printThird);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start threads in RANDOM order (to prove correctness)
        t3.start();
        t2.start();
        t1.start();
    }
}
