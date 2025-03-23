package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();
        
        Double lastPrice = lastPrices.get(stockCode);
        
        // Always log the price update for real-time view
        Logger.logRealtime(stockCode, currentPrice);
        
        // Update the last known price
        lastPrices.put(stockCode, currentPrice);
    }
}
