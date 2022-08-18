package com.jangbo.api.service;

import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.entity.Orders;
import com.jangbo.db.repository.MarketRepository;
import com.jangbo.db.repository.OrdersRepository;
import com.jangbo.db.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    MarketRepository marketRepository;
    @Autowired
    StoreRepository storeRepository;

    /** 소비자번호로 주문 목록 날짜 내림차순 조회 */
    public List<OrdersDto> findAllByCustomer_CustomerNoOrderByOrderDateDesc(Integer customerno) {
        // 전체 주문 내역 조회
        List<Orders> orders = ordersRepository.findAllByCustomer_CustomerNoOrderByOrderDateDesc(customerno);

        List<OrdersDto> result = new LinkedList<OrdersDto>();

        // 주문 내역 전체 탐색하며 OrderItem 리스트를 OrderDto에 같이 넣어줌
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

    /** 상점번호로 주문 목록 날짜 내림차순 조회 */
    public List<OrdersDto> findAllByStoreNoOrderByOrderDateDesc(Integer storeno) {
        return ordersRepository.findAllByStoreNoOrderByOrderDateDesc(storeno);
    }

    /** 주문번호로 해당 주문 정보 조회 */
    public Orders findOrdersByOrderNo(Integer orderno) {
        return ordersRepository.findOrdersByOrderNo(orderno);
    }

    /** 주문상태 변경 */
    public Integer updateOrderState(Orders order, Integer orderno, String status) {
        return ordersRepository.updateOrderState(order, orderno, status);
    }

    /** 주문 생성 */
    public Orders ordersave(Orders order) {
        return ordersRepository.save(order);
    }


}
