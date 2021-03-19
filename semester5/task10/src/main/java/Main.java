public class Main {
    public static void main(String[] args) throws Exception {
        final CustomThreadPool threadPool = new CustomThreadPool(21);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(finalI * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(5*1000);
        threadPool.stop();
    }
}
