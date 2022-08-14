package com.jangbo.db.repository;

import com.jangbo.api.response.StoreInfoRes;
import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.entity.Market;
import com.jangbo.db.entity.Seller;
import com.jangbo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface StoreRepository extends JpaRepository<Store, Integer> {

    List<Store>findByMarket(Market market);
    Store findBySeller(Seller seller);
}
