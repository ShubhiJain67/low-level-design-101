package service;

import java.util.Random;
import subject.Stock;

public class MarketDataService implements Runnable {

    private final Stock stock;

    public MarketDataService(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void run() {
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            double newPrice =stock.getPrice() + random.nextInt(20);
            stock.setPrice(newPrice);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}