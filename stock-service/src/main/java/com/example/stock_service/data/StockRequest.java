package com.example.stock_service.data;

public class StockRequest {
    private Long productId;
    private String productName;
    private Long availableQuantity;

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
