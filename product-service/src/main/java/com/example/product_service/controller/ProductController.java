package com.example.product_service.controller;

import com.example.product_service.data.*;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Endpoint to create a new product.
     *
     * @param request The request body containing product details.
     *
     * @return The response containing the created product information.
     */
    @PostMapping("/create-product")
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest request) {
        var response = productService.createProduct(request);

        return ResponseEntity.ok(response).getBody();
    }

    /**
     * Endpoint to get a list of products with pagination and optional search.
     *
     * @param pageNumber The page number for pagination (default is 0).
     * @param pageSize The number of items per page (default is 10).
     * @param searchKeyword An optional search keyword to filter products.
     *
     * @return The response containing the list of products.
     */
    @GetMapping("/product-list")
    public ResponseEntity<ProductSummaryResponse> getProductList(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                                 @RequestParam(required = false) String searchKeyword) {
        ProductSummaryRequest request = new ProductSummaryRequest();
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        request.setSearchKeyword(searchKeyword);
        var response = productService.getProductList(request);

        return ResponseEntity.ok(response);

    }

    @GetMapping("{id}")
    public ResponseEntity<GetProductDetailResponse> getProductDetail(@PathVariable Long id) {
        var response = productService.getProductDetail(id);

        return ResponseEntity.ok(response);
    }


}
