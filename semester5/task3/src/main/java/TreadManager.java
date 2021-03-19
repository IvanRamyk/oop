public class TreadManager{
    ThreadTree tree;

    public TreadManager(){ }

    public void printRecursive(ThreadNode node, int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("\t");
        }
        System.out.println(node.name);
        for(var child : node.children){
            printRecursive(child, depth + 1);
        }
    }

    public void printRecursive(ThreadNode node){
        printRecursive(node, 0);
    }

    public Runnable inspect(ThreadGroup group, int seconds){
        return () -> {
            int counter = seconds;
            while(counter > 0) {
                tree = new ThreadTree(group);
                printRecursive(tree.root);
                System.out.println("----------------------------------------------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter--;
            }
        };
    }

    public Runnable inspect(ThreadGroup group){
        return inspect(group, 20);
    }
}

