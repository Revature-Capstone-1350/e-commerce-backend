package com.revature.services;

import com.revature.dtos.AuthResponse;
import com.revature.exceptions.NotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByIdAndEmailIgnoreCase(int id, String email) {
        return userRepository.findByUserIdAndEmailIgnoreCase(id, email);
    }

    public List<AuthResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(AuthResponse::new)
                .collect(Collectors.toList());
    }

    public AuthResponse findById(Integer id){
        // Find the user by ID and turn the returned entity into a response, if user is not found throw a NotFoundException
        return userRepository
                .findById(id)
                .map(AuthResponse::new)
                .orElseThrow(NotFoundException::new);
    }

}
