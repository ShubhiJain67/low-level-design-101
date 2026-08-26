package async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationExecutor {

    private static final
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static ExecutorService getExecutorService() {
        return executorService;
    }
    public static void shutdown() {
        executorService.shutdown();
    }
}