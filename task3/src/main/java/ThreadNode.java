import java.util.ArrayList;

public class ThreadNode{
    Thread thread;
    String name;
    ArrayList<ThreadNode> children = new ArrayList<>();

    public ThreadNode(Thread _thread){
        thread = _thread;
        name = thread.getName();
    }

    public ThreadNode(ThreadGroup group){
        if(group == null)
            return;
        name = group.getName();
        thread = null;
        ThreadGroup[] subgroups = new ThreadGroup[group.activeGroupCount()];
        Thread[] threads = new Thread[group.activeCount()];
        int groupsNum = group.enumerate(subgroups, false);
        int num = group.enumerate(threads, false);
        children = new ArrayList<>();

        for(int i = 0; i < groupsNum; i++){
            children.add(new ThreadNode(subgroups[i]));
        }

        for(int i = 0; i < num; i++){
            children.add(new ThreadNode(threads[i]));
        }
    }
}