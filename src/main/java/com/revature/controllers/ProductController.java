package com.revature.controllers;

import com.revature.annotations.AdminOnly;
import com.revature.annotations.Authorized;
import com.revature.dtos.ProductInfo;
import com.revature.exceptions.NotImplementedException;
import com.revature.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class ProductController implements com.revature.interfaces.ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @Authorized
    @GetMapping
    public ResponseEntity<List<ProductInfo>> getInventory() {
        throw new NotImplementedException();
//        return ResponseEntity.ok(productService.findAll());
    }

//    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<ProductInfo> getProductById(@PathVariable("id") int id) {
        throw new NotImplementedException();
//        return ResponseEntity.ok(optional.get());
    }

    @AdminOnly
    @PutMapping
    public void upsert(@RequestBody ProductInfo product) {
        throw new NotImplementedException();
//        return ResponseEntity.ok(productService.save(product));
    }

    @AdminOnly
    @PatchMapping
    public ResponseEntity<List<ProductInfo>> purchase(@RequestBody List<ProductInfo> metadata) {
        throw new NotImplementedException();
        //    	List<Product> productList = new ArrayList<Product>();
//
//    	for (int i = 0; i < metadata.size(); i++) {
//    		Optional<Product> optional = productService.findById(metadata.get(i).getProduct_id());
//
//    		if(!optional.isPresent()) {
//    			return ResponseEntity.notFound().build();
//    		}
//
//    		Product product = optional.get();
//
////    		if(product.getQuantity() - metadata.get(i).getQuantity() < 0) {
////    			return ResponseEntity.badRequest().build();
////    		}
//
////    		product.setQuantity(product.getQuantity() - metadata.get(i).getQuantity());
//    		productList.add(product);
//    	}
//
//        productService.saveAll(productList, metadata);
//
//        return ResponseEntity.ok(productList);
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductInfo> deleteProduct(@PathVariable("id") int id) {
        throw new NotImplementedException();
//        Optional<Product> optional = productService.findById(id);
//
//        if(!optional.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        productService.delete(id);
//
//        return ResponseEntity.ok(optional.get());
    }
}
