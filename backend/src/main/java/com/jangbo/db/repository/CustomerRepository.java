package com.jangbo.db.repository;

import com.jangbo.db.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByCustomerId(String customerId);

    default Customer findOne(Integer customerNo) {
        return (Customer) findById(customerNo).orElse(null);
    }
}