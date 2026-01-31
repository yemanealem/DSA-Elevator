import java.util.concurrent.Semaphore;


class H2O {

    // Allow at most 2 hydrogen threads per molecule
    private Semaphore h = new Semaphore(2);

    // Oxygen waits until 2 hydrogens are printed
    private Semaphore o = new Semaphore(0);

    public H2O() {}

   
    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();

        // prints "H"
        releaseHydrogen.run();

        // signal hydrogen is ready
        o.release();
    }

  
    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);

        // prints "O"
        releaseOxygen.run();

        // allow next molecule's hydrogens
        h.release(2);
    }
}
