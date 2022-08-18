package com.jangbo.db.repository;

import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.dto.StoreDto;
import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {

    List<Orders> findAllByCustomer_CustomerNoOrderByOrderDateDesc(@Param("customerno") Integer customerno);

    List<OrdersDto> findAllByStoreNoOrderByOrderDateDesc(@Param("storeno") Integer storeno);

    Orders findOrdersByOrderNo(@Param("orderno") Integer orderno);

    @Modifying(clearAutomatically = true)
    @Query("update Orders o set o.status = :status where o.orderNo = :orderno")
    Integer updateOrderState(Orders order, Integer orderno, String status);
}
