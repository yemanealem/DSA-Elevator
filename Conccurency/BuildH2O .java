import java.util.concurrent.Semaphore;

/*
===============================================================================
QUESTION: Building H2O (LeetCode 1117)

Rules:
- Each water molecule requires exactly 2 Hydrogen (H) and 1 Oxygen (O)
- Hydrogen threads must wait if two H are already used
- Oxygen must wait until two H are ready
- Order inside a molecule does NOT matter (HHO, HOH, OHH are valid)

Goal:
Guarantee correct formation of H2O molecules regardless of thread order.
===============================================================================
*/

class BuildH2O  {

    // Allows at most 2 hydrogen threads per molecule
    private Semaphore h = new Semaphore(2);

    // Oxygen waits until two hydrogens are printed
    private Semaphore o = new Semaphore(0);

    public H2O() {}

    /*
    ===========================================================================
    hydrogen()

    TRACE:
    1. h.acquire() → allows only 2 H threads
    2. releaseHydrogen.run() → prints "H"
    3. o.release() → signal one H is ready
    ===========================================================================
    */
    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();   // prints "H"
        o.release();
    }

    /*
    ===========================================================================
    oxygen()

    TRACE:
    1. o.acquire(2) → wait until TWO hydrogens are ready
    2. releaseOxygen.run() → prints "O"
    3. h.release(2) → reset hydrogen permits for next molecule
    ===========================================================================
    */
    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        releaseOxygen.run();    // prints "O"
        h.release(2);
    }
}

/*
===============================================================================
MAIN CLASS TO TEST THE SOLUTION
===============================================================================
*/
public class Main {

    public static void main(String[] args) {

        H2O h2o = new H2O();

        Runnable printH = () -> System.out.print("H");
        Runnable printO = () -> System.out.print("O");

        // Random input sequence (thread start order is unpredictable)
        String input = "OOHHHHHOOHHH";

        for (char c : input.toCharArray()) {
            if (c == 'H') {
                new Thread(() -> {
                    try {
                        h2o.hydrogen(printH);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        h2o.oxygen(printO);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
