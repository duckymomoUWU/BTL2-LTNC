package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        alertThresholdHigh=highThreshold;
        alertThresholdLow=lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();
        Double lastPrice = lastAlertedPrices.get(stockCode);
        
        // If we have no previous price record for this stock, just store and return
        if (lastPrice == null) {
            lastAlertedPrices.put(stockCode, currentPrice);
            return;
        }
        
        // Check if current price exceeds the high threshold
        if (currentPrice > alertThresholdHigh && lastPrice <= alertThresholdHigh) {
            alertAbove(stockCode, currentPrice);
            lastAlertedPrices.put(stockCode, currentPrice);
        }
        
        // Check if current price falls below the low threshold
        else if (currentPrice < alertThresholdLow && lastPrice >= alertThresholdLow) {
            alertBelow(stockCode, currentPrice);
            lastAlertedPrices.put(stockCode, currentPrice);
        }
        
        // Update the last alerted price even if no alert was triggered
        else {
            lastAlertedPrices.put(stockCode, currentPrice);
        }
        
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }
}
