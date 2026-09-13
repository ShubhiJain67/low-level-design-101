package concurrency.correctness;

import java.util.concurrent.*;

public class ThreadConfinment {
    public static void main(String args[]) throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> result1 = executor.submit(() -> {
            int counter = 0;
            for(int i = 0; i < 1000; i++){
                counter ++;
            }
            return counter;
        });

        Future<Integer> result2 = executor.submit(() -> {
            int counter = 0;
            for(int i = 0; i < 1000; i++){
                counter ++;
            }
            return counter;
        });

        int mainCounter = result1.get() + result2.get();

        System.out.println("Expected value 2000");
        System.out.println("Actual value "+ mainCounter);

        executor.shutdown();
    }

}
