import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup master = new ThreadGroup("Master group");
        ThreadGroup group1 = new ThreadGroup(master, "Group 1");
        ThreadGroup group1_1 = new ThreadGroup(group1, "Subgroup 1.1");
        ThreadGroup group1_2 = new ThreadGroup(group1, "Subgroup 1.2");
        ThreadGroup group2 = new ThreadGroup(master, "Group 2");
        ThreadGroup group2_1 = new ThreadGroup(group2, "Subgroup 2.1");
        ThreadGroup group2_2 = new ThreadGroup(group2, "Subgroup 2.2");
        ThreadGroup group2_2_1 = new ThreadGroup(group2_2, "Subgroup 2.2.1");
        ThreadGroup group2_2_2 = new ThreadGroup(group2_2, "Subgroup 2.2.2");

        ArrayList<Thread> list = new ArrayList<>();
        Thread A = new Thread(group1_1, ThreadsFactory.getThread(3), "Thread A");
        list.add(A);
        Thread B = new Thread(group1_2, ThreadsFactory.getThread(6), "Thread B");
        list.add(B);
        Thread C = new Thread(group2_1, ThreadsFactory.getThread(5), "Thread C");
        list.add(C);
        Thread d = new Thread(group2_2_1, ThreadsFactory.getThread(3), "Thread d");
        list.add(d);
        Thread e = new Thread(group2_2_2, ThreadsFactory.getThread(8), "Thread e");
        list.add(e);
        Thread f = new Thread(group2_2_2, ThreadsFactory.getThread(4), "Thread f");
        list.add(f);

        for(var t : list)
            t.start();


        Thread mainThread;
        TreadManager manager = new TreadManager();
        mainThread = new Thread(manager.inspect(master));
        mainThread.start();

        for(var t : list)
            t.join();
        mainThread.join();
    }
}