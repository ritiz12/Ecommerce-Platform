package com.example.product_service.data;

import jakarta.persistence.criteria.CriteriaBuilder;

public class PaginatedRequest {
    private Integer pageNumber ;

    private Integer pageSize ;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize ;
    }
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
}
