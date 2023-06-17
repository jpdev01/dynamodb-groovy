package com.jpdev.main.repository

import com.jpdev.main.domain.Idempotency

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface IdempotencyRepository extends CrudRepository<Idempotency, String> {

    Optional<Idempotency> findById(String id);
}
