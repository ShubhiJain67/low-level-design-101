package concurrency.correctness;

public class CoarseGrainedLocks {
    private static int counter = 0;

    private synchronized static void incremenet(){
        counter ++;
    }

    public static void main(String args[]) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                incremenet();
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                incremenet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Expected value 2000");
        System.out.println("Actual value "+ counter);
    }

}
