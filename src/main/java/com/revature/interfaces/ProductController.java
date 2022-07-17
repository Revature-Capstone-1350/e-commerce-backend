package com.revature.interfaces;

import com.revature.annotations.Authorized;
import com.revature.dtos.ProductInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductController {

    public ResponseEntity<List<ProductInfo>> getInventory();
    public ResponseEntity<ProductInfo> getProductById(@PathVariable("id") int id);
    public void insert(@RequestBody ProductInfo product);
    public ResponseEntity<List<ProductInfo>> purchase(@RequestBody List<ProductInfo> metadata);
    public ResponseEntity<ProductInfo> deleteProduct(@PathVariable("id") int id);
}
