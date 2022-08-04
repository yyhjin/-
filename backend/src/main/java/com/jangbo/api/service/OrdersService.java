package com.jangbo.api.service;

import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public List<OrdersDto> findOrdersByCustomer_CustomerNo(Integer customerno) {
        return ordersRepository.findOrdersByCustomer_CustomerNo(customerno);
    }



}
