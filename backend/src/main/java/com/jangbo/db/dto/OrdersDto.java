package com.jangbo.db.dto;


import com.jangbo.db.entity.Orders;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class OrdersDto {
    private Integer orderNo ;
    private Date orderDate;
    private Integer marketNo;
    private Integer storeNo;
    private String status;
    //private Integer customerNo;
    private List<OrderItemDto> orderItems;


    public OrdersDto(Orders order) {
        this.orderNo = order.getOrderNo();
        this.orderDate = order.getOrderDate();
        this.marketNo = order.getMarketNo();
        this.storeNo = order.getStoreNo();
        this.status = order.getStatus();
//        //this.customerNo = customerNo;
//        order.getOrderItems().stream().forEach(o -> o.getOrders().getOrderNo());
//        this.orderItems = order.getOrderItems();
        this.orderItems = order.getOrderItems().stream()
                .map(o -> new OrderItemDto(o))
                .collect(Collectors.toList());
    }


}