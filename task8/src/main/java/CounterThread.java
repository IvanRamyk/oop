class CounterThread implements Runnable {
    volatile Counter counter;
    volatile CustomReentrantLock lock;
    boolean isIncrement;
    int cntIterations;

    public CounterThread(Counter counter, CustomReentrantLock lock, boolean increment, int cntIterations) {
        this.counter = counter;
        this.lock = lock;
        this.isIncrement = increment;
        this.cntIterations = cntIterations;
    }

    public void run() {
        for (int i = 0; i < cntIterations; i++) {
            try {
                lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isIncrement)
                counter.increment();
            else
                counter.decrement();
            lock.unlock();
            System.out.println("Counter value is " + counter.toString());
        }
    }
}