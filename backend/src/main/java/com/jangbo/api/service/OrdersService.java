package com.jangbo.api.service;

import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.entity.Orders;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.MarketRepository;
import com.jangbo.db.repository.OrdersRepository;
import com.jangbo.db.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    StoreRepository storeRepository;

    public List<OrdersDto> findAllByCustomer_CustomerNoOrderByOrderDateDesc(Integer customerno) {
        //return ordersRepository.findAllByCustomer_CustomerNo(customerno);
        //return orders.stream().map(OrdersDto::new).collect(Collectors.toList());
        //return orders.stream().map(o -> new OrdersDto(o)).collect(Collectors.toList());

        List<Orders> orders = ordersRepository.findAllByCustomer_CustomerNoOrderByOrderDateDesc(customerno);

        List<OrdersDto> result = new LinkedList<OrdersDto>();

        for(int i = 0; i < orders.size(); i++) {
            Orders order = orders.get(i);
            String marketname = marketRepository.findMarketByMarketNo(order.getMarketNo()).getMarketName();
            String storename = storeRepository.findById(order.getStoreNo())
                    .orElseThrow(() -> new IllegalAccessError("[storeNo=" + order.getStoreNo() + "] 해당 상점이 존재하지 않습니다."))
                    .getStoreName();
            result.add(new OrdersDto(order, marketname, storename));
        }

        return result;
    }

    public List<OrdersDto> findAllByStoreNoOrderByOrderDateDesc(Integer storeno) {
        return ordersRepository.findAllByStoreNoOrderByOrderDateDesc(storeno);
    }

    public Orders findOrdersByOrderNo(Integer orderno) {
        return ordersRepository.findOrdersByOrderNo(orderno);
    }

    public Integer updateOrderState(Orders order, Integer orderno, String status) {
        return ordersRepository.updateOrderState(order, orderno, status);
    }

    public Orders ordersave(Orders order) {
        return ordersRepository.save(order);
    }


}
