package com.uts.microservice.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uts.microservice.customer_service.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
