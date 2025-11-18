package com.example.product_service.data;

public class PaginatedResponse {
    private int pageNumber;

    private int pageSize;

    private long totalRecords;


    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageNumber() {
        return pageNumber;
    }
    public int getPageSize() {
        return pageSize;
    }
    public long getTotalRecords() {
        return totalRecords;
    }
}
