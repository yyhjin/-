package com.jangbo.db.dto;


import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.Market;
import com.jangbo.db.entity.Orders;
import com.jangbo.db.repository.MarketRepository;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

//class repo {
//    static MarketRepository marketRepository;
//}

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

    private String marketName;

    private Integer customerNo;
    private String customerId;
    //private Customer customer;
    private String storeName;


    public OrdersDto(Orders order) {
        this.orderNo = order.getOrderNo();
        this.orderDate = order.getOrderDate();
        this.marketNo = order.getMarketNo();
        this.storeNo = order.getStoreNo();
        this.status = order.getStatus();

        this.orderItems = order.getOrderItems().stream()
                .map(o -> new OrderItemDto(o))
                .collect(Collectors.toList());

        this.customerNo = order.getCustomer().getCustomerNo();
        this.customerId = order.getCustomer().getCustomerId();


//        MarketRepository marketRepository = null;
//        this.marketName = marketRepository.findMarketByMarketNo(order.getMarketNo()).getMarketName();
    }

    public OrdersDto(Orders order, String marketName, String storeName) {
        this.orderNo = order.getOrderNo();
        this.orderDate = order.getOrderDate();
        this.marketNo = order.getMarketNo();
        this.storeNo = order.getStoreNo();
        this.status = order.getStatus();

        this.orderItems = order.getOrderItems().stream()
                .map(o -> new OrderItemDto(o))
                .collect(Collectors.toList());

        this.customerNo = order.getCustomer().getCustomerNo();
        this.customerId = order.getCustomer().getCustomerId();

        this.marketName = marketName;
        this.storeName = storeName;

    }




}