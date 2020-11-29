import java.util.ArrayList;
import java.util.Random;

public class DemoCustomCyclicBarrier {
    private CustomCyclicBarrier cyclicBarrier;
    private final int parties;
    private final Random random = new Random();


    DemoCustomCyclicBarrier(int parties) {
        this.parties = parties;
    }

    public void runDemo() {
        cyclicBarrier = new CustomCyclicBarrier(this.parties, createBarrierAction());
        ArrayList<Thread> partiesThreads = new ArrayList<>();
        for (int i = 0; i < parties; ++i)
            partiesThreads.add(new Thread(createThreadForTest(Math.abs(random.nextInt() % 10) + 1)));
        for (int i = 0;i < parties; ++i)
            partiesThreads.get(i).start();

        partiesThreads.clear();

        for (int i = 0; i < parties; ++i)
            partiesThreads.add(new Thread(createThreadForTest(Math.abs(random.nextInt() % 10) + 1)));
        for (int i = 0;i < parties; ++i)
            partiesThreads.get(i).start();

        partiesThreads.clear();

        for (int i = 0; i < parties; ++i)
            partiesThreads.add(new Thread(createThreadForTest(Math.abs(random.nextInt() % 10) + 1)));
        for (int i = 0;i < parties; ++i)
            partiesThreads.get(i).start();
    }

    private Runnable createThreadForTest(int seconds) {
        return () -> {
            //System.out.println(seconds);
            try {
                Thread.sleep(seconds *  1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    private Runnable createBarrierAction() {
        return () -> System.out.println("Barrier is passed!\n");
    }

}

