package com.example.product_service.data;

import com.example.product_service.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetProductDetailResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private Integer stockQuantity;
    private String brand;
    private String sku;
    private String imageUrl;
    private BigDecimal discountPrice;
    private BigDecimal discountPercentage;
    private BigDecimal rating;
    private Integer reviewCount;
    private String weight;
    private String dimensions;
    private String tags;
    private Boolean isFeatured;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Category      category;

    public GetProductDetailResponse() {

    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
    public void setDiscountPrice(final BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
    public void setDiscountPercentage(final BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    public void setAvailable(final Boolean available) {
        this.available = available;
    }
    public void setStockQuantity(final Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public void setBrand(final String brand) {
        this.brand = brand;
    }
    public void setSku(final String sku) {
        this.sku = sku;
    }
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setRating(final BigDecimal rating) {
        this.rating = rating;
    }
    public void setReviewCount(final Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
    public void setWeight(final String weight) {
        this.weight = weight;
    }
    public void setDimensions(final String dimensions) {
        this.dimensions = dimensions;
    }
    public void setTags(final String tags) {
        this.tags = tags;
    }
    public void setCategory(final Category category) {
        this.category = category;
    }
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Boolean getAvailable() {
        return available;
    }
    public Integer getStockQuantity() {
        return stockQuantity;
    }
    public String getBrand() {
        return brand;
    }
    public String getSku() {
        return sku;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
    public BigDecimal getRating() {
        return rating;
    }
    public Integer getReviewCount() {
        return reviewCount;
    }
    public String getWeight() {
        return weight;
    }
    public String getDimensions() {
        return dimensions;
    }
    public String getTags() {
        return tags;
    }
    public Boolean getFeatured() {
        return isFeatured;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public Category getCategory() {
        return category;
    }
}
