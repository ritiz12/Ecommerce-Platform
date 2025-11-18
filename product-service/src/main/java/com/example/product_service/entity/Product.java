package com.example.product_service.entity;

  // ← Import karo
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Cacheable
@Table(name = "products", indexes = {
    @Index(name = "idx_product_name", columnList = "name"),
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "available", nullable = false)
    private Boolean available = true;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Column(length = 100)
    private String brand;

    @Column(length = 100)
    private String sku;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "discount_percentage", precision = 5, scale = 2)
    private BigDecimal discountPercentage;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @Column(length = 50)
    private String weight;

    @Column(length = 100)
    private String dimensions;

    @Column(length = 500)
    private String tags;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 50)
    private Category category;  // ← Category type use karo, Enum nahi

    public Product() {
    }

    public Product(final String name, final String description, final BigDecimal price,
                   final String brand, final Category category, final String sku,
                   final String imageUrl, final BigDecimal rating, final Integer reviewCount,
                   final String weight, final String dimensions, final String tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.sku = sku;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.weight = weight;
        this.dimensions = dimensions;
        this.tags = tags;
    }

    // Helper methods
    public boolean isInStock() {
        return this.stockQuantity != null && this.stockQuantity > 0;
    }

    public boolean hasDiscount() {
        return this.discountPrice != null && this.discountPrice.compareTo(this.price) < 0;
    }

    public BigDecimal getEffectivePrice() {
        return hasDiscount() ? this.discountPrice : this.price;
    }

    @PrePersist
    protected void onCreate() {
        if (this.available == null) {
            this.available = true;
        }
        if (this.stockQuantity == null) {
            this.stockQuantity = 0;
        }
        if (this.reviewCount == null) {
            this.reviewCount = 0;
        }
        if (this.isFeatured == null) {
            this.isFeatured = false;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(final Boolean available) {
        this.available = available;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(final Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public Category getCategory() {  // ← Category return type
        return category;
    }

    public void setCategory(final Category category) {  // ← Category parameter
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(final String sku) {
        this.sku = sku;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(final BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(final BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(final BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(final Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(final String weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(final String dimensions) {
        this.dimensions = dimensions;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(final String tags) {
        this.tags = tags;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(final Boolean featured) {
        isFeatured = featured;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }
}