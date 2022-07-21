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
    public ProductInfo getProductById(@PathVariable("id") int id) {
        return productService.findById(id);
    }



    /**
     * put endpoint to update a product
     * @param product receives a product Json
     * @return org.springframework.http.ResponseEntity
     */
    @AdminOnly
    @PutMapping(consumes = "application/json")
    public ResponseEntity insert(@RequestBody ProductRequest product) {
        /*
        //example json
        {
            "id":1,
            "name":"limit 50 char",
            "description":"no limit",
            "price":123456.12,
            "imageUrlS":"url",
            "imageUrlM":"url",
            "category":1
        }
        */
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
