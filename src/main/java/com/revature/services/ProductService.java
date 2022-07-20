package com.revature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ReviewResponse;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public ResponseEntity findAll() {
        List<Product> products = productRepo.findAll();
        List<ProductInfo> prodInfo = products.stream().map(ProductInfo::new).collect(Collectors.toList());
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int sum = 0;
            for (ProductReview rating: product.getRatings()) {
                sum += rating.getRating();
            }
            prodInfo.get(i).setSumOfRating(sum);
        }
        String resp = "";
        try {
            resp = mapper.writeValueAsString(prodInfo); // prepare JSON response
        } catch (JsonProcessingException e) {
            throw new BadRequestException();
        } // This throw is only anticipated to happen upon a bad request

        return ResponseEntity
                .status(HttpStatus.OK.value()) // Set response status
                .body(resp);        // Add the JSON response body
    }

    public ResponseEntity findReviewsByProductId(int id) {
        Product product = productRepo.findById(id)
                .orElseThrow(NotFoundException::new);

        List<ReviewResponse> reviews;
        if (product.getRatings() == null || product.getRatings().size() == 0) {
            reviews = new ArrayList<>();
        }
        else {
            reviews = product.getRatings().stream().map(ReviewResponse::new).collect(Collectors.toList());
        }
        String resp = "";
        try {
            resp = mapper.writeValueAsString(reviews); // prepare JSON response
        } catch (JsonProcessingException e) {
            throw new BadRequestException();
        } // This throw is only anticipated to happen upon a bad request

        return ResponseEntity
                .status(HttpStatus.OK.value()) // Set response status
                .body(resp);        // Add the JSON response body

    }


    public ProductInfo findById(int id){
        return productRepo.findById(id).map(ProductInfo::new).orElseThrow(NotFoundException::new);

    }

    public ResponseEntity save(Product product) {
        return null;
    }
    
    public ResponseEntity saveAll(List<Product> productList, List<ProductInfo> metadata) {
        return null;
    }

    public void delete(int id) {
    }
}
