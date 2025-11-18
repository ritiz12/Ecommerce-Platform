package com.example.product_service.data;

import com.example.product_service.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private Integer stockQuantity;
    private String brand;
    private Category category;
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
    private LocalDateTime createdAt;

    // Getters - JSON serialize karne ke liye zaroori!
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

    public Category getCategory() {
        return category;
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

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}