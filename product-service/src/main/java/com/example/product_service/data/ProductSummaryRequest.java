package com.example.product_service.data;

public class ProductSummaryRequest extends PaginatedRequest {
    private String searchKeyword;

    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(final String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

}
