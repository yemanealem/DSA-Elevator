import java.util.concurrent.Semaphore;
import java.util.concurrent.CyclicBarrier;

public class BuildH2O {

    static class H2O {

        // Allow up to 2 Hydrogen threads
        private Semaphore hydrogenSemaphore = new Semaphore(2);

        // Allow only 1 Oxygen thread
        private Semaphore oxygenSemaphore = new Semaphore(1);

        // Barrier ensures exactly 3 threads (2H + 1O) form one molecule
        private CyclicBarrier barrier = new CyclicBarrier(3);

        public H2O() {}

        /*
        ===========================================================================
        hydrogen()

        EXECUTION TRACE:
        1. hydrogenSemaphore.acquire()
           - Allows only 2 hydrogen threads
        2. releaseHydrogen.run() → prints "H"
        3. barrier.await()
           - Waits until 2H + 1O arrive
        4. After barrier trips → permits reset for next molecule
        ===========================================================================
        */
        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            try {
                hydrogenSemaphore.acquire();
                releaseHydrogen.run();   // prints "H"
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                hydrogenSemaphore.release();
            }
        }

        /*
        ===========================================================================
        oxygen()

        EXECUTION TRACE:
        1. oxygenSemaphore.acquire()
           - Allows only 1 oxygen thread
        2. releaseOxygen.run() → prints "O"
        3. barrier.await()
           - Waits until 2H + 1O arrive
        ===========================================================================
        */
        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            try {
                oxygenSemaphore.acquire();
                releaseOxygen.run();     // prints "O"
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                oxygenSemaphore.release();
            }
        }
    }

    /*
    ===========================================================================
    MAIN METHOD TRACE

    Input threads (random order):
        H H O H O H ...

    Execution:
    - First 2 H acquire hydrogenSemaphore
    - 1 O acquires oxygenSemaphore
    - All 3 wait at barrier
    - Barrier releases → molecule formed
    - Permits reset → next molecule starts

    Valid outputs:
        HHO, HOH, OHH (repeated)
    ===========================================================================
    */
    public static void main(String[] args) {

        H2O h2o = new H2O();

        Runnable h = () -> System.out.print("H");
        Runnable o = () -> System.out.print("O");

        String input = "HHOHHO";

        for (char c : input.toCharArray()) {
            if (c == 'H') {
                new Thread(() -> {
                    try {
                        h2o.hydrogen(h);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        h2o.oxygen(o);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
