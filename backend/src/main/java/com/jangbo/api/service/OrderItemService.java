package com.jangbo.api.service;

import com.jangbo.db.entity.OrderItem;
import com.jangbo.db.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem orderitemsave(OrderItem orderItem) { return orderItemRepository.save(orderItem); }


}
