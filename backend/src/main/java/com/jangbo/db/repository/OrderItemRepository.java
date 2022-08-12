package com.jangbo.db.repository;

import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.entity.OrderItem;
import com.jangbo.db.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {


}
