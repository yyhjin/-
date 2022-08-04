package com.jangbo.db.repository;

import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.dto.StoreDto;
import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {

//    @Query("select new com.jangbo.db.dto.OrdersDto(o)" +
//            "from Orders o where o.customer.customerNo = :customerno")
    List<OrdersDto> findOrdersByCustomer_CustomerNo(@Param("customerno") Integer customerno);


}
