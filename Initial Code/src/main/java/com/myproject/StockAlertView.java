package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        alertThresholdHigh = highThreshold;
        alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();
        Double lastPrice = lastAlertedPrices.get(stockCode);

        // If we have no previous price record for this stock, just store and return
        // if (lastPrice == null) {
        //     lastAlertedPrices.put(stockCode, currentPrice);
        //     return;
        // }
        //nếu lastPrice == currentPrice thì không làm gì cả
        if (lastPrice != null && lastPrice == currentPrice) {
            return;
        }
        // Kiểm tra giá cao ngay cả khi đây là lần đầu tiên
        if (currentPrice >= alertThresholdHigh) {
            alertAbove(stockCode, currentPrice);
        } else if (currentPrice <= alertThresholdLow) {
            alertBelow(stockCode, currentPrice);
        }

        // Lưu giá hiện tại cho lần sau
        lastAlertedPrices.put(stockCode, currentPrice);

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
