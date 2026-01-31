import java.util.concurrent.Semaphore;

/*
===============================================================================
QUESTION: Print in Order (LeetCode 1114)

Suppose we have a class:

class Foo {
    public void first(Runnable printFirst) { printFirst.run(); }
    public void second(Runnable printSecond) { printSecond.run(); }
    public void third(Runnable printThird) { printThird.run(); }
}

Three different threads will call the methods first(), second(), and third().
You must guarantee that the output is always:

    "firstsecondthird"

REGARDLESS of the order in which the threads are started.

-------------------------------------------------------------------------------
KEY CHALLENGE:
- Threads are scheduled unpredictably by the OS
- second() or third() may start before first()
- We must enforce execution order using synchronization
===============================================================================
*/

public class PrintOrder {

    static class Foo {

        // Semaphore controlling first()
        // Initialized with 1 permit → first() can run immediately
        private Semaphore firstSemaphore = new Semaphore(1);

        // Semaphore controlling second()
        // Initialized with 0 permits → second() must wait
        private Semaphore secondSemaphore = new Semaphore(0);

        // Semaphore controlling third()
        // Initialized with 0 permits → third() must wait
        private Semaphore thirdSemaphore = new Semaphore(0);

        /*
        ===========================================================================
        first()

        EXECUTION TRACE:
        1. firstSemaphore.acquire()
           - Succeeds immediately because permit = 1
        2. printFirst.run() → prints "first"
        3. secondSemaphore.release()
           - Signals second() that it may now proceed
        ===========================================================================
        */
        public void first(Runnable printFirst) throws InterruptedException {
            firstSemaphore.acquire();
            printFirst.run();                 // OUTPUT: "first"
            secondSemaphore.release();
        }

        /*
        ===========================================================================
        second()

        EXECUTION TRACE:
        1. secondSemaphore.acquire()
           - Blocks until first() releases a permit
        2. printSecond.run() → prints "second"
        3. thirdSemaphore.release()
           - Signals third() that it may now proceed
        ===========================================================================
        */
        public void second(Runnable printSecond) throws InterruptedException {
            secondSemaphore.acquire();
            printSecond.run();                // OUTPUT: "second"
            thirdSemaphore.release();
        }

        /*
        ===========================================================================
        third()

        EXECUTION TRACE:
        1. thirdSemaphore.acquire()
           - Blocks until second() releases a permit
        2. printThird.run() → prints "third"
        3. firstSemaphore.release()
           - (Optional reset step; allows reuse if looped)
        ===========================================================================
        */
        public void third(Runnable printThird) throws InterruptedException {
            thirdSemaphore.acquire();
            printThird.run();                 // OUTPUT: "third"
            firstSemaphore.release();
        }
    }

    /*
    ===========================================================================
    MAIN METHOD EXECUTION TRACE

    1. Create Foo instance → semaphores initialized
       - firstSemaphore = 1
       - secondSemaphore = 0
       - thirdSemaphore = 0

    2. Threads are STARTED IN RANDOM ORDER:
       - third thread starts → BLOCKS on thirdSemaphore
       - second thread starts → BLOCKS on secondSemaphore
       - first thread starts → RUNS immediately

    3. first() executes:
       - prints "first"
       - releases secondSemaphore

    4. second() resumes:
       - prints "second"
       - releases thirdSemaphore

    5. third() resumes:
       - prints "third"

    FINAL OUTPUT (always):
       firstsecondthird
    ===========================================================================
    */
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

        // Threads intentionally started out of order
        t3.start();
        t2.start();
        t1.start();
    }
}
