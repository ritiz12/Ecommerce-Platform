package com.example.product_service.data;

import com.example.product_service.entity.Category;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    private String name;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have maximum 10 digits and 2 decimal places")
    private BigDecimal price;

    private Boolean available = true;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity = 0;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;


    @Size(max = 100, message = "SKU must not exceed 100 characters")
    private String sku;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    private String imageUrl;

    @DecimalMin(value = "0.0", message = "Discount price must be greater than or equal to 0")
    @Digits(integer = 10, fraction = 2, message = "Discount price must have maximum 10 digits and 2 decimal places")
    private BigDecimal discountPrice;

    @DecimalMin(value = "0.0", message = "Discount percentage must be between 0 and 100")
    @DecimalMax(value = "100.0", message = "Discount percentage must be between 0 and 100")
    @Digits(integer = 3, fraction = 2, message = "Discount percentage must have maximum 3 digits and 2 decimal places")
    private BigDecimal discountPercentage;

    @DecimalMin(value = "0.0", message = "Rating must be between 0 and 5")
    @DecimalMax(value = "5.0", message = "Rating must be between 0 and 5")
    @Digits(integer = 1, fraction = 2, message = "Rating must have maximum 1 digit and 2 decimal places")
    private BigDecimal rating;

    @Min(value = 0, message = "Review count cannot be negative")
    private Integer reviewCount = 0;

    @Size(max = 50, message = "Weight must not exceed 50 characters")
    private String weight;

    @Size(max = 100, message = "Dimensions must not exceed 100 characters")
    private String dimensions;

    @Size(max = 500, message = "Tags must not exceed 500 characters")
    private String tags;

    private Boolean isFeatured = false;

    private Category category;

    // Getters only
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

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public Category getCategory() {
        return category;
    }
}