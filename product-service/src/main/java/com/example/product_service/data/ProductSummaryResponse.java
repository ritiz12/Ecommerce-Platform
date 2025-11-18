package com.example.product_service.data;

import java.util.ArrayList;

public class ProductSummaryResponse extends PaginatedResponse {

    private ArrayList<ProductSummary> products;

    public void addProducts(final ProductSummary product) {
        if (products == null) {
            products = new ArrayList<>();
        }

        products.add(product);
    }

    public ArrayList<ProductSummary> getProducts() {
        return products;
    }
}
