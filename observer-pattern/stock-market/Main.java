import async.NotificationExecutor;
import exchange.StockExchange;
import observer.Trader;
import service.MarketDataService;
import subject.Stock;

public class Main {

    public static void main(String[] args) {

        // Create stock exchange
        StockExchange exchange = new StockExchange();

        // Create stocks
        Stock apple = new Stock(
                "AAPL",
                200
        );

        Stock tesla = new Stock(
                "TSLA",
                300
        );

        // Add stocks to exchange
        exchange.addStock(apple);
        exchange.addStock(tesla);

        Trader shubhi = new Trader("Shubhi");

        Trader prateek = new Trader("Prateek");

        // Subscribe traders
        apple.subscribe(shubhi);

        apple.subscribe(prateek);

        tesla.subscribe(prateek);

        // Create stock update threads
        Thread appleThread  = new Thread(
                new MarketDataService(
                        apple
                )
        );

        Thread teslaThread = new Thread(
                new MarketDataService(
                        tesla
                )
        );

        appleThread.start();

        teslaThread.start();

        try {
            appleThread.join();
            teslaThread.join();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Shutdown executor service
        NotificationExecutor.shutdown();

        System.out.println(
                "\nStock Market System Stopped"
        );
    }
}
