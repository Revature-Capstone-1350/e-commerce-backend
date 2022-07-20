package com.revature.services;

import com.revature.dtos.AuthResponse;
import com.revature.dtos.UserResponse;
import com.revature.exceptions.NotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
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

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailIgnoreCaseAndPassword(email, password);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByIdAndEmailIgnoreCase(int id, String email) {
        return userRepository.findByUserIdAndEmailIgnoreCase(id, email);
    }

    public List<UserResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Integer id){
        return userRepository.findById(id).map(UserResponse::new).orElseThrow(NotFoundException::new);
    }

}
