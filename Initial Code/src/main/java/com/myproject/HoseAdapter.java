package com.myproject;

import java.util.List;
import java.util.ArrayList;//!import thư viện ArrayList
public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
        this.hoseLib=hoseLib;
        this.stockCodes=stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list
        //lấy dữ liệu thô từ HosePriceFetchLib
        List<HoseData> hoseDataList=hoseLib.getPrices(stockCodes);
        //chuyển dữ liệu HoseData sang StockPrice
        List<StockPrice> stockPrices = new ArrayList<>();
        for(HoseData hoseData:hoseDataList){
            stockPrices.add(convertToStockPrice(hoseData));
        }
        return stockPrices;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TODO: Convert HoseData to StockPrice
        return new StockPrice(hoseData.getStockCode(), 
        hoseData.getPrice(), hoseData.getVolume(), hoseData.getTimestamp());
    }
}
