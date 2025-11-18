package com.example.stock_service.data;

public class StockSummary {
    private Long productId;
    private String productName;
    private Long availableQuantity;

    public StockSummary(final Long productId, final String productName, final Long availableQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(final Long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
