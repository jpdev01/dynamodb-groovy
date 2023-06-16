package com.jpdev.main.repository

import com.jpdev.main.domain.Product

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface ProductRepository extends CrudRepository<Product, String> {

    Optional<Product> findById(String id);
}
