package com.jangbo.db.repository;

import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.InterStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterStoreRepository extends JpaRepository<InterStore,Integer> {
    List<InterStore> findAllByCustomer(Customer customer);
}
