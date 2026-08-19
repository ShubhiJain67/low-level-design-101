import manager.ConfigurationManager;
import printer.Printer;

public class Main {

    public static void main(String[] args) {

        ConfigurationManager manager = ConfigurationManager.getInstance();

        Runnable task = () -> {
            String dbUrl = manager.get("DB_URL");
            Printer.print("DB_URL -> " + dbUrl);
        };

        for(int i = 10; i < 15; i++) {
            new Thread(task).start();
        }
    }
}