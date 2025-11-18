package com.example.stock_service.service;

import com.example.stock_service.data.GetStockResponse;
import com.example.stock_service.data.StockRequest;
import com.example.stock_service.data.StockSummary;
import com.example.stock_service.entity.Stock;
import com.example.stock_service.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void addStock(final StockRequest request) {
        Stock stock = new Stock();
        stock.setProductId(request.getProductId());
        stock.setAvailableQuantity(request.getAvailableQuantity());
        stock.setProductName(request.getProductName());

        stockRepository.save(stock);

        System.out.println("Stock created for Product ID: " + request.getProductId());

    }
    public GetStockResponse getStockList() {
        GetStockResponse response = new GetStockResponse();

       List<Stock> stocks =  stockRepository.findAll() ;

      stocks.forEach(stock -> {
          response.addStock(new StockSummary(
              stock.getProductId(),
                stock.getProductName(),
                stock.getAvailableQuantity()
          ));
      });

      return response;

    }
}
