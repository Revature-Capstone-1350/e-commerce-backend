package com.revature.services;

import com.revature.dtos.CreateProductRequest;
import com.revature.dtos.CreationResponse;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ReviewResponse;
import com.revature.exceptions.NotFoundException;
import com.revature.exceptions.NotImplementedException;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductInfo> findAll() {
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
        return prodInfo;
    }

    public List<ReviewResponse> findReviewsByProductId(int id) {
        return productRepo.findById(id)
                .orElseThrow(NotFoundException::new)
                .getRatings()
                .stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());     // Add the JSON response body
    }


    public ProductInfo findById(int id){
        return productRepo.findById(id).map(ProductInfo::new).orElseThrow(NotFoundException::new);

    }

    /**
     * This method is used to persist a new product to the database
     * @param createProductRequest takes a CreateProductRequest DTO
     * @return a new CreationResponse DTO
     */
    public CreationResponse save(CreateProductRequest createProductRequest) {
        Product product = new Product(createProductRequest);
        product = productRepo.save(product);
        return new CreationResponse(product.getProductId());
    }
    
    public void saveAll(List<Product> productList, List<ProductInfo> metadata) {
        throw new NotImplementedException();
    }

    public void delete(int id) {
        throw new NotImplementedException();
    }
}
