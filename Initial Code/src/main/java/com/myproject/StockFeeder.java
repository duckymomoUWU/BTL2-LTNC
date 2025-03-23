package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if(instance==null){
            instance=new StockFeeder();
        }
        return instance;
    }
    private boolean containsStock(String code) {//! ham them vao
        for (Stock stock : stockList) {
            if (stock.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        if(!containsStock(stock.getCode())){
            stockList.add(stock);
            viewers.putIfAbsent(stock.getCode(), new ArrayList<>());
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        if (!containsStock(code)) {
            Logger.errorRegister(code);
            return;
        }
        
        List<StockViewer> stockViewers = viewers.get(code);
        if (stockViewers.contains(stockViewer)) {
            Logger.errorRegister(code);
            return;
        }
        
        stockViewers.add(stockViewer);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        if (!containsStock(code)) {
            Logger.errorUnregister(code);
            return;
        }
        
        List<StockViewer> stockViewers = viewers.get(code);
        if (!stockViewers.contains(stockViewer)) {
            Logger.errorUnregister(code);
            return;
        }
        
        stockViewers.remove(stockViewer);
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        String code = stockPrice.getCode();
        if (!viewers.containsKey(code)) {
            return;
        }
        
        List<StockViewer> stockViewers = viewers.get(code);
        for (StockViewer viewer : stockViewers) {
            viewer.onUpdate(stockPrice);
        }
    }
}
