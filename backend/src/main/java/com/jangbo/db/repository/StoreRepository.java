package com.jangbo.db.repository;

import com.jangbo.api.response.StoreInfoRes;
import com.jangbo.db.dto.StoreDto;
import com.jangbo.db.entity.Market;
import com.jangbo.db.entity.Seller;
import com.jangbo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface StoreRepository extends JpaRepository<Store, Integer> {

    List<Store>findByMarket(Market market);
    Store findBySeller(Seller seller);

    @Query("select m from Store m where m.storeName like %:storeName% and m.market.marketNo=:marketNo")
    List<Store> findAllByStoreNameSQL(@Param("storeName")String storeName,@Param("marketNo")int marketNo);

    Store findByStoreNo(Integer sellerNo);
}
