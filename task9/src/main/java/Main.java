public class Main {
    public static void main(String[] args) throws InterruptedException {

        /*Creates a new phaser with 1 registered unArrived parties
         * and initial phase number is 0
         */
        CustomPhaser phaser=new CustomPhaser(1);
        System.out.println("new phaser with 1 registered unArrived parties"
                + " created and initial phase  number is 0 ");

        //Create 3 threads
        Thread thread1=new Thread(new MyRunnable(phaser,"first"),"Thread-1");
        Thread thread2=new Thread(new MyRunnable(phaser,"second"),"Thread-2");
        Thread thread3=new Thread(new MyRunnable(phaser,"third"),"Thread-3");

        System.out.println("\n--------Phaser has started---------------");
        //Start 3 threads
        thread1.start();
        thread2.start();
        thread3.start();

        //get current phase
        int currentPhase=phaser.getPhase();
        /*arriveAndAwaitAdvance() will cause thread to wait until current phase
         * has been completed i.e. until all registered threads
         * call arriveAndAwaitAdvance()
         */
        phaser.arriveAndAwait();
        System.out.println("------Phase-"+currentPhase+" has been COMPLETED----------");

        //------NEXT PHASE BEGINS------

        currentPhase=phaser.getPhase();
        phaser.arriveAndAwait();
        System.out.println("------Phase-"+currentPhase+" has been COMPLETED----------");

        /* current thread Arrives and deRegisters from phaser.
         * DeRegistration reduces the number of parties that may
         * be required to advance in future phases.
         */
        phaser.arriveAndDeregister();


    }
}





class MyRunnable implements Runnable{

    CustomPhaser phaser;

    MyRunnable(CustomPhaser phaser,String name){
        this.phaser=phaser;
        this.phaser.register(); //Registers/Add a new unArrived party to this phaser.
        System.out.println(name +" - New unarrived party has "
                + "been registered with phaser");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +
                " - party has arrived and is working in "
                + "Phase-"+phaser.getPhase());
        try {
            Thread.sleep(1000);
            phaser.arriveAndAwait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Sleep has been used for formatting output
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //------NEXT PHASE BEGINS------

        System.out.println(Thread.currentThread().getName() +
                " - party has arrived and is working in "
                + "Phase-"+phaser.getPhase());
        try {
            phaser.arriveAndAwait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        phaser.arriveAndDeregister();
    }

}