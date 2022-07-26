package com.revature.services;

import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.PersistanceException;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.OrderRepository;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteProductService { // Added as separate service to avoid refactoring tests

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductReviewRepository reviewRepo;
    private final OrderRepository orderRepo;
    private final ProductService productService;

    @Autowired
    public DeleteProductService(ProductRepository productRepo, CategoryRepository categoryRepo, ProductReviewRepository reviewRepo, OrderRepository orderRepo, ProductService productService) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.reviewRepo = reviewRepo;
        this.orderRepo = orderRepo;
        this.productService = productService;
    }

    private List<Order> findOrdersByProductId(int productId) {
        List<Order> allOrders = orderRepo.findAll();
        List<Order> relevantOrders = new ArrayList<>();
        for (Order order: allOrders) {
            for (Product product: order.getItems()) {
                if (product.getProductId() == productId) {
                    relevantOrders.add(order);
                    break;
                }
            }
        }
        return relevantOrders;
    }

    public void deleteProduct(int productId) {
        Product product = productRepo.findById(productId) // ensures product exists
                .orElseThrow(BadRequestException::new);
        if (!findOrdersByProductId(productId).isEmpty() ) { // ensures no cascade drop is needed
            throw new PersistanceException("There are orders associated with this product.");
        }

        List<ProductReview> reviews = product.getRatings(); // removes reviews
        if (reviews != null && !reviews.isEmpty()) {
            for (ProductReview review: reviews) { // Review has no dependencies
                reviewRepo.delete(review);
            }
        }
        productRepo.deleteById(productId);
    }
}
