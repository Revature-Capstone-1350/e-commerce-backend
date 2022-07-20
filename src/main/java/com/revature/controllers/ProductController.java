package com.revature.controllers;

import com.revature.annotations.AdminOnly;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductRequest;
import com.revature.exceptions.NotImplementedException;
import com.revature.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity getInventory() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rating/{id}")
    public ResponseEntity getProductReviewsById(@PathVariable("id") int id) {
        return productService.findReviewsByProductId(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") int id) {
        throw new NotImplementedException();
    }

    //@AdminOnly
    @PutMapping
    public ResponseEntity insert(@RequestBody ProductRequest product) {
        return productService.updateProduct(product);
    }

    @AdminOnly
    @PatchMapping
    public ResponseEntity<List<ProductInfo>> purchase(@RequestBody List<ProductInfo> metadata) {
        throw new NotImplementedException();
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductInfo> deleteProduct(@PathVariable("id") int id) {
        throw new NotImplementedException();
    }
}
