package com.revature.controllers;

import com.revature.annotations.AdminOnly;
import com.revature.dtos.AuthResponse;
import com.revature.dtos.UserResponse;
import com.revature.exceptions.NotImplementedException;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") @Valid int id) {
        return userService.findById(id);
    }

}
