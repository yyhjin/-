package com.jangbo.db.repository;

import com.jangbo.db.dto.CallOrderDto;
import com.jangbo.db.entity.CallOrder;
import com.jangbo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CallOrderRepository extends JpaRepository<CallOrder,Integer> {
    @Query("select count(c) from CallOrder c where c.storeNo = :storeno")
    Integer countByStoreNo(Integer storeno);

    List<CallOrderDto> findAllByStoreNoOrderByCallOrderNoAsc(@Param("storeno") Integer storeno);

    CallOrder findByStoreNoAndCustomerId(Integer storeNo, String customerId);

    //Long deleteCallOrderByStoreNoAndCustomerId(@Param("storeno") Integer storeno, @Param("customerid") String customerId);


//    @Modifying(clearAutomatically = true)
//    @Query("update Orders o set o.status = :status where o.orderNo = :orderno")
//    Integer updateOrderState(Orders order, Integer orderno, String status);
}
