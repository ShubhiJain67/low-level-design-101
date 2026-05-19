package exchange;

import java.util.HashMap;
import java.util.Map;
import subject.Stock;

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