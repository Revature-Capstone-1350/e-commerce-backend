package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query
    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);

    @Query
    Optional<User> findByIdAndEmailIgnoreCase(String email, int id);

    @Query
    boolean existsByEmailIgnoreCase(String email);

}
