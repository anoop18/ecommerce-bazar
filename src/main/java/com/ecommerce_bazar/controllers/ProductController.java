package com.ecommerce_bazar.controllers;

import com.ecommerce_bazar.dto.ProductRequest;
import com.ecommerce_bazar.dto.ProductResponse;
import com.ecommerce_bazar.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest product){
        productService.createProduct(product);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }



}
