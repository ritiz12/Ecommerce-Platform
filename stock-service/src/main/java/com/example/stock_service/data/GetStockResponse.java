package com.example.stock_service.data;

import java.util.List;

public class GetStockResponse {
    private List<StockSummary> stocks;

    public void addStock(final StockSummary stock)
    {
        if (stocks == null) {
            stocks = new java.util.ArrayList<>();
        }

        stocks.add(stock);
    }

    public List<StockSummary> getStocks() {
        return stocks;
    }
}
