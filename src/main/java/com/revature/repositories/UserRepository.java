package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Be sure to use the in-build methods, and avoid native queries
     * List<T> findAll();
     * List<T> findAll(Sort sort);
     * List<T> findAllById(Iterable<ID> ids);
     * <S extends T> List<S> saveAll(Iterable<S> entities);
     * void deleteAllInBatch(Iterable<T> entities);
     * void deleteAllByIdInBatch(Iterable<ID> ids);
     * void deleteAllInBatch();
     * T getById(ID id);
     * <S extends T> List<S> findAll(Example<S> example);
     * <S extends T> List<S> findAll(Example<S> example, Sort sort);
     */

    @Query // Case-insensitive email and case-sensitive password
    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);

    @Query // Used to validate tokens
    Optional<User> findByUserIdAndEmailIgnoreCase(int userId, String email);

    @Query // Used in registration to see if email is registered
    boolean existsByEmailIgnoreCase(String email);

}
