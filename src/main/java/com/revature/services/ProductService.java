package com.revature.services;

import com.revature.dtos.CreateProductRequest;
import com.revature.dtos.CreationResponse;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductRequest;
import com.revature.dtos.ReviewResponse;
import com.revature.exceptions.NotFoundException;
import com.revature.exceptions.PersistanceException;
import com.revature.exceptions.UnprocessableEntityException;
import com.revature.models.Category;
import com.revature.exceptions.NotImplementedException;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<ProductInfo> findAll() {
        List<Product> products = productRepo.findAll();
        List<ProductInfo> prodInfo = products.stream().map(ProductInfo::new).collect(Collectors.toList());
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int sum = 0;
            for (ProductReview rating : product.getRatings()) {
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


    public ProductInfo findById(int id) {
        return productRepo.findById(id).map(ProductInfo::new).orElseThrow(NotFoundException::new);

    }

    /**
     * This method is used by the ProductController to persist a new product to the database using the ProductRepo
     * @param createProduct This is a DTO that is passed from the ProductController to this method that contains all the information from the user to be persisted
     * @return Returns a CreationResponse DTO that contains the new product ID
     */
    public CreationResponse save(CreateProductRequest createProduct) {
        Category category = categoryRepo.getById(createProduct.getCategory()); //This fetches the category by its Id sent in the CreateProduct DTO
        Product product = new Product(createProduct, category); //This creates a new product object with the fetched category and fields of the CreateProducts DTO
        StringBuilder errorMessage = new StringBuilder("Issue(s) with this request:");
        boolean passed = true;

        if (!categoryRepo.findById(createProduct.getCategory()).isPresent()) {
            errorMessage.append(" - No category found");
            passed = false;
        }

        if (BigDecimal.valueOf(product.getPrice()).scale() > 2) {
            errorMessage.append(" - Price too long of a decimal number");
            passed = false;
        }

        if (BigDecimal.valueOf(product.getPrice()).precision() > 8) {
            errorMessage.append(" - Price length is too long");
            passed = false;
        }

        if (product.getName().length() > 50) {
            errorMessage.append(" - Name is more then 50 characters");
            passed = false;
        }

        if (passed) {
            productRepo.save(product);
            return new CreationResponse(product.getProductId());

        } else {
            throw new PersistanceException(errorMessage.toString());
        }
    }

    /**
     * attempts to update based off a product
     * @param product product to be updated
     */
    public void updateProduct(ProductRequest product){

        StringBuilder errorMessage = new StringBuilder("Issue(s) with this request:");
        boolean passed = true;

        if (!productRepo.findById(product.getId()).isPresent()) {
            errorMessage.append(" - No product found for this id");
            passed = false;
        }

        if (!categoryRepo.findById(product.getCategory()).isPresent()) {
            errorMessage.append(" - No category found");
            passed = false;
        }

        if (BigDecimal.valueOf(product.getPrice()).scale() > 2) {
            errorMessage.append(" - Price too long of a decimal number");
            passed = false;
        }

        if (BigDecimal.valueOf(product.getPrice()).precision() > 8) {
            errorMessage.append(" - Price length is too long");
            passed = false;
        }

        if (product.getName().length() > 50) {
            errorMessage.append(" - Name is more then 50 characters");
            passed = false;
        }

        if (passed) {
            Category updateCategory = categoryRepo.getById(product.getCategory());
            Product updateProduct = new Product(product, updateCategory);
            productRepo.save(updateProduct);
        } else {
            throw new UnprocessableEntityException(errorMessage.toString());
        }
    }

    public void saveAll(List<Product> productList, List<ProductInfo> metadata){
            throw new NotImplementedException();
    }

    public void delete(int id){
            throw new NotImplementedException();
    }
}

