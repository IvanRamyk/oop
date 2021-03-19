import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CustomThreadPool {
    private Queue<Runnable> taskQueue;
    private List<WorkThread> threads = new ArrayList<>();
    private boolean stopped = false;


    public CustomThreadPool(int numThreads) {
        taskQueue = new ConcurrentLinkedQueue<Runnable>();

        for (int i = 0; i < numThreads; ++i) {
            threads.add(new WorkThread(taskQueue));
        }
        for(WorkThread thread: threads) {
            thread.start();
        }
    }

    public synchronized void execute(Runnable task){
        if (!stopped) {
            this.taskQueue.add(task);
        }
    }

    public synchronized void stop() {
        stopped = true;
        /*for (WorkThread thr: threads) {
            thr.interrupt();//stopWorkThread();
        }*/
    }
    public void shutdownImmediately() {
        stopped = true;
        for (WorkThread thr: threads) {
            thr.interrupt();//stopWorkThread();
        }
    }

    private class WorkThread extends Thread{

        public WorkThread(Queue<Runnable> queue) {
            taskQueue = queue;
        }

        @Override
        public void run() {
            while (!stopped) {
                Runnable runnable = taskQueue.poll();
                if (runnable != null) runnable.run();
            }
        }

        public synchronized void stopWorkThread() {
            this.interrupt();
        }
    }
}