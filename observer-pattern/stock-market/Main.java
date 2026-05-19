import exchange.StockExchange;
import observer.Trader;
import service.MarketDataService;
import subject.Stock;

public class Main {

    public static void main(String[] args) {

        StockExchange exchange = new StockExchange();

        Stock apple = new Stock(
            "AAPL",
            200
        );

        Stock tesla = new Stock(
            "TSLA",
            300
        );

        exchange.addStock(apple);
        exchange.addStock(tesla);

        // Create traders
        Trader shubhi = new Trader("Shubhi");

        Trader prateek = new Trader("Prateek");

        apple.subscribe(shubhi);
        apple.subscribe(prateek);
        tesla.subscribe(prateek);

        Thread appleThread = new Thread(
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
        apple.subscribe(prateek);
        appleThread.start();
    }
}