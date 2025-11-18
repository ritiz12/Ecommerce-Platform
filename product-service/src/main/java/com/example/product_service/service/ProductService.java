package com.example.product_service.service;

import com.example.product_service.Error.ResourceNotFoundException;
import com.example.product_service.Repository.ProductInterface;
import com.example.product_service.data.*;
import com.example.product_service.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductInterface productRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${stock.service.url}")
    private String stockServiceUrl;

    /**
     * Create a new product
     *
     * @param request containing product details
     *
     * @return response with created product info
     */
    @Transactional
    public CreateProductResponse createProduct(final CreateProductRequest request) {

        // Create product entity
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setAvailable(request.getAvailable());
        product.setStockQuantity(request.getStockQuantity());
        product.setBrand(request.getBrand());
        product.setCategory(request.getCategory());
        product.setSku(request.getSku());
        product.setImageUrl(request.getImageUrl());
        product.setDiscountPrice(request.getDiscountPrice());
        product.setDiscountPercentage(request.getDiscountPercentage());
        product.setRating(request.getRating());
        product.setReviewCount(request.getReviewCount());
        product.setWeight(request.getWeight());
        product.setDimensions(request.getDimensions());
        product.setTags(request.getTags());
        product.setFeatured(request.getIsFeatured());

        // Save product
        Product savedProduct = productRepository.save(product);

        // Call Stock service to create a new stock for product
        createStock(savedProduct);

        // Update category product count

        // Map to response
        return mapToCreateProductResponse(savedProduct);
    }

    /**
     * Create stock entry in Stock Service for the newly created product
     *
     * @param savedProduct the newly created product
     */
    private void createStock(final Product savedProduct) {

        Map<String, Object> stockRequest = new HashMap<>();
        stockRequest.put("productId", savedProduct.getId());
        stockRequest.put("productName", savedProduct.getName());
        stockRequest.put("availableQuantity", savedProduct.getStockQuantity());

        webClientBuilder.build()
                        .post()
                        .uri("http://stock-service/api/addStock")
                        .bodyValue(stockRequest)
                        .retrieve()
                        .onStatus(status -> status.isError(), clientResponse ->
                                      clientResponse.bodyToMono(String.class)
                                                    .flatMap(errorBody -> {
                                                        System.err.println("❌ Stock API Error Status: " + clientResponse.statusCode());
                                                        System.err.println("❌ Stock API Error Body: " + errorBody);
                                                        return Mono.error(new RuntimeException("Stock API failed: " + errorBody));
                                                    })
                                 )

                        .bodyToMono(String.class) // response body bhi print karenge
                        .doOnSuccess(body -> {
                            System.out.println("✅ Stock Service Response: " + body);
                        })

                        .doOnError(error -> {
                            System.err.println("❌ Stock creation failed");
                            error.printStackTrace(); // ⭐ full stacktrace print karega
                        })

                        .subscribe();
    }

    /**
     * Get product details by ID
     *
     * @param id of the product
     *
     * @return response with product details
     */
    @Cacheable(value = "products"
        + ""
        + "", key = "#id")
    public GetProductDetailResponse getProductDetail(final Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        Product productDetail = productRepository.findById(id).orElse(null);

        if (productDetail == null) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }

        var response = new GetProductDetailResponse();
        response.setId(productDetail.getId());
        response.setAvailable(productDetail.getAvailable());
        response.setBrand(productDetail.getBrand());
        response.setCategory(productDetail.getCategory());
        response.setCreatedAt(productDetail.getCreatedAt());
        response.setDescription(productDetail.getDescription());
        response.setDimensions(productDetail.getDimensions());
        response.setDiscountPercentage(productDetail.getDiscountPercentage());
        response.setDiscountPrice(productDetail.getDiscountPrice());
        response.setImageUrl(productDetail.getImageUrl());
        response.setName(productDetail.getName());
        response.setPrice(productDetail.getPrice());
        response.setRating(productDetail.getRating());
        response.setReviewCount(productDetail.getReviewCount());
        response.setSku(productDetail.getSku());
        response.setStockQuantity(productDetail.getStockQuantity());
        response.setTags(productDetail.getTags());
        response.setWeight(productDetail.getWeight());
        return response;

    }

    /**
     * Get paginated list of products
     *
     * @param request containing pagination details
     *
     * @return paginated response with product summaries
     */
    public ProductSummaryResponse getProductList(final ProductSummaryRequest request) {
        final var response = new ProductSummaryResponse();

        // Create pageable object
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPageNumber());

        // Create specification for filtering
        Specification<Product> specification = productRepository.getSearchSpecification(request.getSearchKeyword());

        Page<Product> page = productRepository.findAll(specification, pageable);

        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalRecords(page.getTotalElements());

        page.getContent().forEach(product -> {
            ProductSummary summary = new ProductSummary();
            summary.setId(product.getId());
            summary.setName(product.getName());
            summary.setDescription(product.getDescription());
            summary.setImageUrl(product.getImageUrl());
            summary.setAvailable(product.getAvailable());
            summary.setPrice(product.getPrice());
            summary.setDiscountPrice(product.getDiscountPrice());
            summary.setDiscountPercentage(product.getDiscountPercentage());
            summary.setRating(product.getRating());
            summary.setReviewCount(product.getReviewCount());

            response.addProducts(summary);
        });
        return response;
    }

    private CreateProductResponse mapToCreateProductResponse(Product product) {
        CreateProductResponse response = new CreateProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setAvailable(product.getAvailable());
        response.setStockQuantity(product.getStockQuantity());
        response.setBrand(product.getBrand());
        response.setSku(product.getSku());
        response.setImageUrl(product.getImageUrl());
        response.setDiscountPrice(product.getDiscountPrice());
        response.setDiscountPercentage(product.getDiscountPercentage());
        response.setRating(product.getRating());
        response.setReviewCount(product.getReviewCount());
        response.setWeight(product.getWeight());
        response.setDimensions(product.getDimensions());
        response.setTags(product.getTags());
        response.setIsFeatured(product.getFeatured());
        response.setCreatedAt(product.getCreatedAt());

        return response;
    }
}
