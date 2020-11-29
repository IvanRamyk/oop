public class ThreadTree{
    public ThreadNode root;
    public ThreadTree(ThreadGroup group){
        root = new ThreadNode(group);
    }
}
