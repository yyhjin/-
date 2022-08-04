package com.jangbo.db.dto;


import com.jangbo.db.entity.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderItemDto {
    private Integer orderItemNo ;
    private Integer count;
    private Integer price;
    private String itemName;
    //private Integer OrderNo;

    public OrderItemDto(OrderItem orderItem) {
        this.orderItemNo = orderItem.getOrderItemNo();
        this.count = orderItem.getCount();
        this.price = orderItem.getPrice();
        this.itemName = orderItem.getItemName();
    }


}