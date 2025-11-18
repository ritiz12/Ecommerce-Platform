package com.example.stock_service.controller;

import com.example.stock_service.data.GetStockResponse;
import com.example.stock_service.data.StockRequest;
import com.example.stock_service.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/addStock")
    public ResponseEntity<String> addStock(@RequestBody StockRequest stockRequest) {
        stockService.addStock(stockRequest);
        return ResponseEntity.ok("Stock added successfully");
    }

    @GetMapping("/stocks")
    public ResponseEntity<GetStockResponse> getStockList() {
        GetStockResponse response = stockService.getStockList();
        return ResponseEntity.ok(response);
    }
}
