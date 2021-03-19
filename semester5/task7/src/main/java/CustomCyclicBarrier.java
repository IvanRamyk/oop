public class CustomCyclicBarrier {
    private final int parties;
    private final Runnable barrierAction;
    private int expectedParties;

    public CustomCyclicBarrier(int parties, Runnable barrierAction) {
        this.barrierAction = barrierAction;
        this.parties = parties;
        this.expectedParties = parties;
    }

    public CustomCyclicBarrier(int parties) {
        this.parties = parties;
        this.barrierAction = null;
    }

    public synchronized void await() throws InterruptedException {
        --expectedParties;
        System.out.println("Thread with id:" + Thread.currentThread().getId() + " is waiting");
        printStatus();
        if (expectedParties == 0) {
            expectedParties = parties;
            notifyAll();
            if (barrierAction != null)
                barrierAction.run();
        } else {
            this.wait();
        }
    }

    public void printStatus() {
        System.out.println((parties - expectedParties) + " are waiting. " + expectedParties + " left.\n");
    }
}
