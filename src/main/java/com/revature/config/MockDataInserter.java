package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.RegisterRequest;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MockDataInserter implements CommandLineRunner {
    private final UserRepository userRepo;
    private final AuthService authService;
    private final ProductRepository productRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public MockDataInserter(UserRepository userRepo, AuthService authService, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.authService = authService;
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest regReq = new RegisterRequest("a@gmail.com","12345","a",null);
        User user = new User(regReq);
        User created = userRepo.save(user);
        user.setPassword("testingu");
        authService.getToken(created); // verifies function

        String baseURL = "https://raw.githubusercontent.com/jsparks9/pics/main/images/@.jpg";
        for (int i=1; i<=20; i++) {
            Product.Category cat;
            Double price;
            switch(i%3) {
                case 0:
                    cat = Product.Category.CATEGORY1;
                    price = 0.0; // free tier
                    break;
                case 1:
                    cat = Product.Category.CATEGORY2;
                    price = 1.95;
                    break;
                case 2:
                    cat = Product.Category.CATEGORY3;
                    price = 1.50;
                    break;
                default:
                    cat = Product.Category.CATEGORY4;
                    price = 0.0;
            }
            Product product = new Product(cat, "Product "+i, "Desc for product "+i,
                    price, baseURL.replace("@",""+i));
            productRepo.save(product);
        }
        System.out.println("Printing Products");
        stars();
        System.out.println(mapper.writeValueAsString(productRepo.findAll().stream().map(ProductInfo::new).collect(Collectors.toList())));
        stars();


    }

    private static void stars() {
        System.out.println("*****************************************************************************************");
    }
}
