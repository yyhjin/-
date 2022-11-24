package com.jangbo.db.repository;

import com.jangbo.db.entity.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller, Integer> {

    Seller findBySellerId(String sellerId);

    Seller findByBusinessNumber(String businessNumber);

    default Seller findOne(Integer sellerNo) {
        return (Seller) findById(sellerNo).orElse(null);
    }
}