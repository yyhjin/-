
package com.jangbo.db.repository;

import com.jangbo.db.entity.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SellerRepository {


    private final EntityManager em;

    public void save(Seller seller) {
        em.persist(seller);
    }

    public Seller findOne(Integer sellerNo)  {
        return em.find(Seller.class, sellerNo);
    }
    public List<Seller> findBySellerId(String sellerId) {
        //parameter name binding .setParameter("name1", name) -> :name1
        return em.createQuery("select s from Seller s where s.sellerId = :sellerId", Seller.class)
                .setParameter("sellerId", sellerId)
                .getResultList();
    }


}

