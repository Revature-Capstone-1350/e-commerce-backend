package com.revature.config;

import com.revature.dtos.RegisterRequest;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MockDataInserter implements CommandLineRunner {
    private final UserRepository userRepo;
    private final AuthService authService;

    public MockDataInserter(UserRepository userRepo, AuthService authService) {
        this.userRepo = userRepo;
        this.authService = authService;
    }

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest regReq = new RegisterRequest("a@gmail.com","12345","a",null);
        User user = new User(regReq);
        User created = userRepo.save(user);

        authService.getToken(created); // verifies function

        Product product = new Product(Product.Category.CATEGORY1, "first product", "indescribable",
                1.95, "someURL.jpg");
    }
}
