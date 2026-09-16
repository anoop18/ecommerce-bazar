package com.ecommerce_bazar.services;

import com.ecommerce_bazar.dto.ProductRequest;
import com.ecommerce_bazar.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest product);
    List<ProductResponse> getAllProducts();
}
