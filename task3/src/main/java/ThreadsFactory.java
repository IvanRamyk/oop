import java.util.concurrent.TimeUnit;

public class ThreadsFactory {
    public static Runnable getThread(final int counter) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
