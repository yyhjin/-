package com.jangbo.db.repository;

import com.jangbo.db.dto.CallOrderDto;
import com.jangbo.db.entity.CallOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface CallOrderRepository extends JpaRepository<CallOrder,Integer> {
    @Query("select count(c) from CallOrder c where c.storeNo = :storeno")
    Integer countByStoreNo(Integer storeno);

    List<CallOrderDto> findAllByStoreNoOrderByOrderNoAsc(@Param("storeno") Integer storeno);

    CallOrder findByStoreNoAndCustomerId(Integer storeNo, String customerId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update CallOrder c set c.orderNo = c.orderNo-1 where c.storeNo = :storeNo")
    Integer updateAll(Integer storeNo);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update CallOrder c set c.orderNo = c.orderNo-1 where c.storeNo = :storeNo and c.orderNo > :index")
    Integer updateByIndex(Integer storeNo, Integer index);


}
