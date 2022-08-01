package com.jangbo.db.repository;

import com.jangbo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StoreRepository extends JpaRepository<Store,Integer> {

}
