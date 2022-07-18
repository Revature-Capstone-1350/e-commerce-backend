package com.revature.repositories;

import com.revature.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
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
}
