package com.jangbo.db.repository;

import com.jangbo.db.entity.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SellerRepository {

    private final EntityManager em;

    public void save(Seller seller) {
        em.persist(seller);
    }

    public Seller findById(String sellerId)  {
        return em.find(Seller.class, sellerId);
    }
}
