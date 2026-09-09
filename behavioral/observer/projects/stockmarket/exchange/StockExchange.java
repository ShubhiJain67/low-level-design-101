package behavioral.observer.projects.stockmarket.exchange;

import behavioral.observer.projects.stockmarket.subject.Stock;
import java.util.HashMap;
import java.util.Map;

public class StockExchange {

    private final Map<String, Stock> stocks;

    public StockExchange() {
        this.stocks = new HashMap<>();
    }

    public void addStock(Stock stock) {
        stocks.put(stock.getName(), stock);
    }

    public Stock getStock(String name) {
        return stocks.get(name);
    }
}