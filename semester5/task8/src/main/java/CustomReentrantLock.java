public class CustomReentrantLock {
    boolean isLocked;
    long threadId;
    private int lockCount;

    private final Object sync_obj = new Object();

    public CustomReentrantLock() {
        isLocked = false;
        threadId = 0;
        lockCount = 0;
    }

    synchronized public void lock() throws InterruptedException {
        while (isLocked && Thread.currentThread().getId() != threadId) {
            wait();
        }
        isLocked = true;
        threadId = Thread.currentThread().getId();
        ++lockCount;
    }

    synchronized public void unlock() {
        if (!isLocked || this.threadId != Thread.currentThread().getId()) {
            return;
        }
        -- lockCount;
        if (lockCount == 0) {
            isLocked = false;
            notify();
        }
    }
}
